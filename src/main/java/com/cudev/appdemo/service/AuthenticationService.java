package com.cudev.appdemo.service;

import com.cudev.appdemo.entity.User;
import com.cudev.appdemo.ex.NotFoundException;
import com.cudev.appdemo.model.request.LoginRequest;
import com.cudev.appdemo.model.response.LoginResponse;
import com.cudev.appdemo.model.response.UserForLogin;
import com.cudev.appdemo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;


    public LoginResponse verify(LoginRequest request) {

        try {

            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (authentication.isAuthenticated()) {
                User userRepo = repo.findByUserName(request.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));

                return new LoginResponse(jwtService.generateToken(request.getUsername()), toUserForLogin(userRepo));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public UserForLogin toUserForLogin(User user) {
        UserForLogin res = new UserForLogin();
        BeanUtils.copyProperties(user, res);
        return res;
    }

}
