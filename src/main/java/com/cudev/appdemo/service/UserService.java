package com.cudev.appdemo.service;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.entity.Customer;
import com.cudev.appdemo.entity.Role;
import com.cudev.appdemo.entity.User;
import com.cudev.appdemo.model.request.CustomerRegisterRequest;
import com.cudev.appdemo.model.response.UserRoleDTO;
import com.cudev.appdemo.repository.CustomerRepository;
import com.cudev.appdemo.repository.RoleRepository;
import com.cudev.appdemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Iterable<User> getAllUsers() {
        Iterable<User> listUser = userRepository.findAll();
        return listUser;
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    /// để đảm bảo dữ liệu được lưu đồng bộ.
    public ReponseObject createUser(CustomerRegisterRequest userRequestDTO) {


        // Kiểm tra userName đã tồn tại hay chưa
        Optional<User> existingUser = userRepository.findByUserName(userRequestDTO.getUserName());
        if (existingUser.isPresent()) {
            ReponseObject reponseObject = new ReponseObject(false, "UserName đã tồn tại! Vui lòng chọn username khác.", null);
//            throw new RuntimeException("UserName đã tồn tại! Vui lòng chọn username khác.");
            return reponseObject;
        }

        // Tạo đối tượng User
        User user = new User();
        user.setUserName(userRequestDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        // Gán danh sách Role
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userRequestDTO.getRoleIds()));
        user.setRoles(roles);

        // Lưu User
        User savedUser = userRepository.save(user);

        // Tạo đối tượng Customer
        Customer customer = new Customer();
        customer.setNameCustomer(userRequestDTO.getNameCustomer());
        customer.setNumberPhone(userRequestDTO.getNumberPhone());
        customer.setAddress(userRequestDTO.getAddress());
        customer.setUser(savedUser);

        // Lưu Customer
        customerRepository.save(customer);
        ReponseObject reponseObject = new ReponseObject(true, "Đăng ký thành công", savedUser);
        return reponseObject;
    }

    @Transactional
    public ReponseObject getRoleUserById(Long id) {
        // Kiểm tra user đã tồn tại hay chưa
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            ReponseObject reponseObject = new ReponseObject(false, "UserName không tồn tại! Vui lòng chọn username khác.", null);
            return reponseObject;
        }

        // Lấy danh sách Role của User
        Set<Role> roles = existingUser.getRoles();

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setNameUser(existingUser.getUserName());
        userRoleDTO.setIdUser(existingUser.getId());

        for (Role role : roles) {
            userRoleDTO.getListRoles().add(role.getNameRole());
        }
        return null;
    }

    public ReponseObject updateUserRole(Long userId, List<Long> roleIds) {
        // Kiểm tra user đã tồn tại hay chưa
        User user = userRepository.findById(userId).orElse(null);
        ReponseObject reponseObject;

        if (user == null) {
            reponseObject = new ReponseObject(false, "User không tồn tại! Vui lòng chọn username khác.", null);
            return reponseObject;
        }

        // Lấy danh sách Role của User
        List<Role> roles = roleRepository.findAllById(roleIds);

        Set<Role> roleSet = new HashSet<>(roles);
        user.setRoles(roleSet);


        // Cập nhật User
        userRepository.save(user);

        reponseObject = new ReponseObject(true, "Cập nhật thành công", user);
        return reponseObject;
    }
}
