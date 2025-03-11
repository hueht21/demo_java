package com.cudev.appdemo.controllers.authentication;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.model.request.LoginRequest;
import com.cudev.appdemo.model.response.LoginResponse;
import com.cudev.appdemo.service.AuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthenticationController {


    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<ReponseObject> login(@RequestBody @Valid LoginRequest user) {
        try {

            LoginResponse res = service.verify(user);
            return new ResponseEntity<ReponseObject>(
                    new ReponseObject(true, "Login Successful", res),
                    HttpStatus.OK
            );
        } catch (ValidationException ex) {
            return new ResponseEntity<ReponseObject>(new ReponseObject(false, "Thất bại", ""), HttpStatus.BAD_REQUEST);
        }
    }
}
