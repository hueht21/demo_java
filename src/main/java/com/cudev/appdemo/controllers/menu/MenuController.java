package com.cudev.appdemo.controllers.menu;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.model.request.MenuSaveModel;
import com.cudev.appdemo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/get-all-menus")
    public ResponseEntity<ReponseObject> getAllMenus() {
        ReponseObject reponseObject = new ReponseObject(true, "Thành công", menuService.getAllMenus());
        return new ResponseEntity<>(reponseObject, HttpStatus.OK);
    }

    @PostMapping("/save-menu")
    public ResponseEntity<ReponseObject> saveMenu(@RequestBody MenuSaveModel menuSaveModel) {
        ReponseObject reponseObject = menuService.saveMenu(menuSaveModel);
        return new ResponseEntity<>(reponseObject, HttpStatus.OK);
    }


}
