package com.cudev.appdemo.service;

import com.cudev.appdemo.entity.Menu;
import com.cudev.appdemo.model.response.MenuDto;
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
        return menus.stream()
                .map(menu -> new MenuDto(menu.getId(), menu.getMenuName(), menu.getLinkUri()))
                .collect(Collectors.toList());
    }

}
