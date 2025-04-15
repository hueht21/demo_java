package com.cudev.appdemo.controllers.role;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.model.request.RoleMenuUpdateDTO;
import com.cudev.appdemo.model.request.UpdateUserRoleDto;
import com.cudev.appdemo.model.response.MenuDto;
import com.cudev.appdemo.service.RoleService;
import com.cudev.appdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


    @GetMapping("/get-all")
    public ResponseEntity<ReponseObject> getAllRoles() {
        ReponseObject reponseObject = new ReponseObject(true, "Thành công", roleService.getAllRoles());
        return ResponseEntity.ok(reponseObject);
    }

    @GetMapping("/menus")
    public ResponseEntity<ReponseObject> getMenusByRole(@RequestParam("roleId") Long roleId) {
        ReponseObject reponseObject = new ReponseObject(true, "Thành công", roleService.getMenusByRoleId(roleId));
        return new ResponseEntity<ReponseObject>(reponseObject, HttpStatus.OK);
    }

    @GetMapping("/all-menus")
    public ResponseEntity<ReponseObject> getAllMenus() {
        List<MenuDto> menus = roleService.getAllMenu();
        ReponseObject reponseObject = new ReponseObject(true, "Thành công", menus);
        return new ResponseEntity<ReponseObject>(reponseObject, HttpStatus.OK);
    }

    @PutMapping("/update-role-menus")
    public ResponseEntity<String> updateRoleMenus(@RequestBody RoleMenuUpdateDTO roleMenuUpdateDTO) {
        try {
            roleService.updateRoleMenus(roleMenuUpdateDTO.getRoleId(), roleMenuUpdateDTO.getMenuIds());
            return ResponseEntity.ok("Menus updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating menus: " + e.getMessage());
        }
    }

    @GetMapping("/get-role-by-user-id")
    public ResponseEntity<ReponseObject> getRoleByUserId(@RequestParam("userId") Long userId) {
        ReponseObject reponseObject = new ReponseObject(true, "Thành công", roleService.getRoleByUserId(userId));
        return new ResponseEntity<ReponseObject>(reponseObject, HttpStatus.OK);
    }

    @PutMapping("/updateUserRole")
    ResponseEntity<ReponseObject> updateUserRole(@RequestBody UpdateUserRoleDto updateUserRoleDto) {

        try {
            userService.updateUserRole(updateUserRoleDto.getUserId(), updateUserRoleDto.getListRole());
            return new ResponseEntity<ReponseObject>(new ReponseObject(true, "Thành công", ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ReponseObject>(new ReponseObject(false, "Thất bại", ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/lock-account")
    ResponseEntity<ReponseObject> lockAccount(@RequestParam("userId") Long userId, @RequestParam("status") int status) {
        return new ResponseEntity<>(userService.accountLock(userId, status), HttpStatus.OK);
    }

}
