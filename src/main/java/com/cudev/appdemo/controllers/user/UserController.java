package com.cudev.appdemo.controllers.user;


import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.entity.User;
import com.cudev.appdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")

public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    ResponseEntity<ReponseObject> getAllUsers() {
        Iterable<User> listUser = userService.getAllUsers();
        return new ResponseEntity<ReponseObject>(new ReponseObject(true, "Thành công", listUser), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    ResponseEntity<ReponseObject> getUserById(@PathVariable Long id) {

        System.out.println("getUserById" + id);
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<ReponseObject>(new ReponseObject(true, "Thành công", user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ReponseObject>(new ReponseObject(false, "Không tìm thaasy", ""), HttpStatus.BAD_REQUEST);
        }
    }

}
