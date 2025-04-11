package com.cudev.appdemo.service;

import com.cudev.appdemo.entity.Role;
import com.cudev.appdemo.model.response.RoleDTO;
import com.cudev.appdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> new RoleDTO(
                        role.getIdRole(),
                        role.getNameRole(),
                        role.getDescribe()
                ) )
                .collect(Collectors.toList());
    }
}
