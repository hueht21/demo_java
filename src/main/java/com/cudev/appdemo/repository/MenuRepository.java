package com.cudev.appdemo.repository;

import com.cudev.appdemo.entity.Menu;
import com.cudev.appdemo.model.response.MenuUserDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = """
        SELECT DISTINCT m.* 
        FROM userrole ur 
        INNER JOIN role r ON ur.ID_ROLE = r.ID_ROLE 
        INNER JOIN role_menu rm ON r.ID_ROLE = rm.ID_ROLE 
        INNER JOIN menu m ON rm.ID_MENU = m.ID_MENU 
        WHERE ur.ID_USER = :userId
    """, nativeQuery = true)
    List<Menu> findMenusByUserId(@Param("userId") Long userId);


    @Query(value = """
        SELECT m.* FROM role_menu rm
        INNER JOIN menu m ON rm.id_menu = m.id_menu
        WHERE rm.id_role = :roleId
        """, nativeQuery = true)
    List<Menu> findMenusByRoleId(@Param("roleId") Long roleId);


    @Query(value = """
        SELECT 
            m.id_menu AS idMenu, 
            m.menu_name AS menuName, 
            m.link_uri AS linkUri, 
            u.user_name AS userCreate, 
            uu.user_name AS userUpdate 
        FROM menu m
        LEFT JOIN user u ON m.user_id_create = u.id_user
        LEFT JOIN user uu ON m.user_id_update = uu.id_user
    """, nativeQuery = true)
    List<MenuUserDTO> getAllMenusWithCreatorAndUpdater();
}
