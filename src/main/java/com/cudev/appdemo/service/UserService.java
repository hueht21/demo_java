package com.cudev.appdemo.service;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.entity.Customer;
import com.cudev.appdemo.entity.Role;
import com.cudev.appdemo.entity.User;
import com.cudev.appdemo.model.request.CustomerRegisterRequest;
import com.cudev.appdemo.model.response.MenuDto;
import com.cudev.appdemo.model.response.UserForLogin;
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
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private MenuService menuService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
            return reponseObject;
        }

        // Tạo đối tượng User
        User user = new User();
        user.setUserName(userRequestDTO.getUserName());
//        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

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

    public User getUser(String userName, String nameAcc) {
        // Kiểm tra userName đã tồn tại hay chưa
        Optional<User> existingUser = userRepository.findByUserName(userName);
        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            // Tạo đối tượng User
            User user = new User();
            user.setUserName(userName);
            user.setNameUser(nameAcc);
            user.setStatusUser(1);

            // Gán danh sách Role
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByNameRole("ROLE_CUS").get());
            user.setRoles(roles);

            // Lưu User
            User savedUser = userRepository.save(user);
            return savedUser;

        }

    }

    public ReponseObject accountLock(Long userId, int status) {
        // Kiểm tra userName đã tồn tại hay chưa
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            return new ReponseObject(false, "User không tồn tại", null);
        } else {
            if (status < 0 || status > 3) {
                return new ReponseObject(false, "Trạng thái khoong hợp lệ", null);
            }
            try {
                existingUser.get().setStatusUser(status);
                userRepository.save(existingUser.get());
                return new ReponseObject(true, "Cập nhập thành công", null);
            } catch (Exception e) {
                return new ReponseObject(false, "Có lỗi xảy ra", null);
            }

        }
    }


    public ReponseObject getMenusByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with userName: " + userName));
        List<MenuDto> menus = menuService.getMenusByUserId(user.getId());
        UserForLogin userForLogin = new UserForLogin();
        userForLogin.setUserName(user.getUserName());
        userForLogin.setNameUser(user.getNameUser());
        userForLogin.setId(user.getId());
        userForLogin.setListMenu(menus);

        return new ReponseObject(true, "Lấy danh sách menu thành công", userForLogin);
    }

}
