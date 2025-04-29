package com.cudev.appdemo.controllers;

import com.cudev.appdemo.model.response.ReponseObject;
import com.cudev.appdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home-page")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/dashboard")
    public ResponseEntity<ReponseObject> getOderByUser(@RequestParam("userName") String userName) {

        ReponseObject reponseObject = userService.getMenusByUserName(userName);

        return new ResponseEntity<ReponseObject>(reponseObject, HttpStatus.OK);
    }
}
