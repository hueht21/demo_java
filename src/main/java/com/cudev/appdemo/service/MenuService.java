package com.cudev.appdemo.service;

import com.cudev.appdemo.base.ReponseObject;
import com.cudev.appdemo.entity.Menu;
import com.cudev.appdemo.model.request.MenuSaveModel;
import com.cudev.appdemo.model.response.MenuDto;
import com.cudev.appdemo.model.response.MenuUserDTO;
import com.cudev.appdemo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuDto> getMenusByUserId(Long userId) {
        List<Menu> menus = menuRepository.findMenusByUserId(userId);
        return menus.stream().map(menu -> MenuDto.builder().id(menu.getId()).menuName(menu.getMenuName()).menuUrl(menu.getLinkUri()).build()).collect(Collectors.toList());
    }

    public List<MenuUserDTO> getAllMenus() {

        try {
            List<MenuUserDTO> getList = menuRepository.getAllMenusWithCreatorAndUpdater();
            return getList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ReponseObject saveMenu(MenuSaveModel menuSaveModel) {
        try {

            if(menuSaveModel.getLinkUri() == null || menuSaveModel.getLinkUri().isEmpty() || menuSaveModel.getUserCreate() == null){
                return new ReponseObject(false, "Thất bại đầu vào không hợp lý", null);
            }

            Menu menu = new Menu();
            menu.setMenuName(menuSaveModel.getNameMenu());
            menu.setLinkUri(menuSaveModel.getLinkUri());
            menu.setIdUserCreate(menuSaveModel.getUserCreate());
            menuRepository.save(menu);
            return new ReponseObject(true, "Thành công", menu);
        } catch (Exception e) {
            return new ReponseObject(false, "Thất bại, " + e,  null);
        }

    }

}
