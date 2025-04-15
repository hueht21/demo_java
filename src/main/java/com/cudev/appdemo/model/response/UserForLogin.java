package com.cudev.appdemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForLogin {

    private Long id;
    private String userName;
    private String nameUser;
    private Set<String> listRoles;
    private List<MenuDto> listMenu;
//    private String phone;
//    private String email;
//    private String role;
}
