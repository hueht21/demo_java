package com.cudev.appdemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForLogin {

    private Long id;
    private String userName;
//    private String phone;
//    private String email;
//    private String role;
}
