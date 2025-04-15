package com.cudev.appdemo.model.request;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UpdateUserRoleDto {

    private Long userId;
    private List<Long> listRole;
}
