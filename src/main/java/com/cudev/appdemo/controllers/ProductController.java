package com.cudev.appdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "Hello, Spring Boot!";
    }
}
