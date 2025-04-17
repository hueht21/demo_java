package com.cudev.appdemo.service;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.entity.Role;
import com.cudev.appdemo.entity.User;
import com.cudev.appdemo.ex.NotFoundException;
import com.cudev.appdemo.model.request.LoginRequest;
import com.cudev.appdemo.model.response.LoginResponse;
import com.cudev.appdemo.model.response.MenuDto;
import com.cudev.appdemo.model.response.UserForLogin;
import com.cudev.appdemo.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private MenuService menuService;


    public ReponseObject verify(LoginRequest request) {

        try {

            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (authentication.isAuthenticated()) {
                User userRepo = repo.findByUserName(request.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));

                if(userRepo.getStatusUser() == 1) {
                    LoginResponse loginResponse = new LoginResponse(jwtService.generateToken(request.getUsername()), toUserForLogin(userRepo));
                    return new ReponseObject(true, "Login thành công", loginResponse);
                }else {
                    return new ReponseObject(false, "Tài khoản đã bị khoá", null);
                }

            }

        } catch (BadCredentialsException e) {
            // Xử lý khi thông tin đăng nhập sai
            return new ReponseObject(false, "Sai tên đăng nhập hoặc mật khẩu", null);
        } catch (NotFoundException e) {
            // Xử lý khi không tìm thấy người dùng
            return new ReponseObject(false, e.getMessage(), null);
        } catch (Exception e) {
            // Xử lý các lỗi chung khác
            return new ReponseObject(false, "Đăng nhập thất bại", null);
        }
        return null;

    }

    public Set<String> getRolesByUserName(Long userId) {
        return repo.findById(userId)
                .map(user -> user.getRoles()
                        .stream()
                        .map(Role::getNameRole) // chỉ lấy name
                        .collect(Collectors.toSet()))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public List<MenuDto> getMenusByUserId(Long userId) {
        return menuService.getMenusByUserId(userId);
    }

    public UserForLogin toUserForLogin(User user) {
        UserForLogin res = new UserForLogin();
        BeanUtils.copyProperties(user, res);
        res.setListRoles(getRolesByUserName(user.getId()));
        res.setListMenu(getMenusByUserId(user.getId()));
        return res;
    }
}
