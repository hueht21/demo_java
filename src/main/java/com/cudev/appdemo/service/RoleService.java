package com.cudev.appdemo.service;

import com.cudev.appdemo.entity.Menu;
import com.cudev.appdemo.entity.Role;
import com.cudev.appdemo.model.response.MenuDto;
import com.cudev.appdemo.model.response.RoleDTO;
import com.cudev.appdemo.repository.MenuRepository;
import com.cudev.appdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MenuRepository menuRepository;


    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> new RoleDTO(role.getIdRole(), role.getNameRole(), role.getDescribe())).collect(Collectors.toList());
    }

    public List<MenuDto> getMenusByRoleId(Long roleId) {

        List<Menu> menus = menuRepository.findMenusByRoleId(roleId);

        return menus.stream().map(menu ->
//                new MenuDto(menu.getId(), menu.getMenuName(), menu.getLinkUri())
                MenuDto.builder().id(menu.getId()).menuName(menu.getMenuName()).menuUrl(menu.getLinkUri()).build()).collect(Collectors.toList());
    }

    public List<MenuDto> getAllMenu() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().map(menu

                -> MenuDto.builder().id(menu.getId()).menuName(menu.getMenuName()).build()

        ).collect(Collectors.toList());
    }

    public void updateRoleMenus(Long roleId, List<Long> menuIds) {
        // Kiểm tra xem role có tồn tại không
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

        // Lấy danh sách menu từ menuIds
        List<Menu> menus = menuRepository.findAllById(menuIds);

        // Cập nhật mối quan hệ giữa role và menu
        role.setMenus(menus);

        // Lưu role với các menu mới
        roleRepository.save(role);
    }

    public List<RoleDTO> getRoleByUserId(Long userId) {
        List<Role> roles = roleRepository.findRolesByUserId(userId);
        return roles.stream().map(role -> new RoleDTO(role.getIdRole(), role.getNameRole(), role.getDescribe())).collect(Collectors.toList());
    }
}
