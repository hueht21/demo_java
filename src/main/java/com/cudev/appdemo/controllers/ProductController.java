package com.cudev.appdemo.controllers;

import com.cudev.appdemo.entity.User;
import com.cudev.appdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public Iterable<User> hello() {
        System.out.println("hello");
        Iterable<User> listUser = userService.getAllUsers();
// In ra log để kiểm tra dữ liệu
        listUser.forEach(user -> System.out.println(user.getId() + " - " + user.getUserName()));
        return listUser;
    }
}
