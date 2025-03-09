package com.cudev.appdemo.service;

import com.cudev.appdemo.entity.User;
import com.cudev.appdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        Iterable<User> listUser = userRepository.findAll();
        return listUser;
    }


    public User getUserById(Long id) {
        System.out.println("getUserById" + id);
        return userRepository.findById(id).get();
    }
}
