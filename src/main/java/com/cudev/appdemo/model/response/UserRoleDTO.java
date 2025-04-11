package com.cudev.appdemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    String nameUser;
    Long idUser;
    Set<String> listRoles;
    Set<MenuDto> listMenu;


}
