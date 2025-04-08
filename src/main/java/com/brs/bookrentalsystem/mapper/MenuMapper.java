package com.brs.bookrentalsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.brs.bookrentalsystem.dto.Menu;

@Mapper
public interface MenuMapper {

    
    @Insert("INSERT INTO menus(menu_name, menu_url, parent_menu_id, display_order, is_active) " +
            "VALUES(#{menuName}, #{menuUrl}, #{parentMenuId}, #{displayOrder}, #{isActive})")
    @Options(useGeneratedKeys = true, keyProperty = "menuId")
    int insertMenu(Menu menu);

    @Update("UPDATE menus SET menu_name=#{menuName}, menu_url=#{menuUrl}, " +
            "parent_menu_id=#{parentMenuId}, display_order=#{displayOrder}, is_active=#{isActive} " +
            "WHERE menu_id=#{menuId}")
    int updateMenu(Menu menu);

    @Delete("DELETE FROM menus WHERE menu_id=#{menuId}")
    int deleteMenu(long menuId);

    @Select("SELECT * FROM menus WHERE menu_id=#{menuId}")
    Menu findMenuById(long menuId);

    @Select("SELECT * FROM menus ORDER BY display_order")
    List<Menu> findAllMenus();

    @Select("SELECT * FROM menus WHERE parent_menu_id IS NULL ORDER BY display_order")
    List<Menu> findRootMenus();

    @Select("SELECT * FROM menus WHERE parent_menu_id=#{parentMenuId} ORDER BY display_order")
    List<Menu> findChildMenus(long parentMenuId);
}
