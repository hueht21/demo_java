package com.cudev.appdemo.controllers.role;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles")
public class RoleController {


    @Autowired
    private RoleService roleService;


    @GetMapping("/get-all")
    public ResponseEntity<ReponseObject> getAllRoles() {
        ReponseObject reponseObject = new ReponseObject(true, "Thành công", roleService.getAllRoles());
        return ResponseEntity.ok(reponseObject);
    }


}
