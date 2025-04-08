package com.brs.bookrentalsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.brs.bookrentalsystem.dto.RoleMenu;

@Mapper
public interface RoleMenuMapper {

@Insert("INSERT INTO role_menu(role_id, menu_id, can_view, can_edit, created_at, updated_at) " +
            "VALUES(#{roleId}, #{menuId}, #{canView}, #{canEdit}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "roleMenuId")
    void insertRoleMenu(RoleMenu roleMenu);

    @Update("UPDATE role_menu SET role_id=#{roleId}, menu_id=#{menuId}, can_view=#{canView}, " +
            "can_edit=#{canEdit}, updated_at=#{updatedAt} WHERE role_menu_id=#{roleMenuId}")
            void updateRoleMenu(RoleMenu roleMenu);

    @Delete("DELETE FROM role_menu WHERE role_menu_id=#{roleMenuId}")
    void deleteRoleMenu(long roleMenuId);

    @Select("SELECT * FROM role_menu WHERE role_menu_id=#{roleMenuId}")
    RoleMenu selectRoleMenuById(long roleMenuId);

    @Select("SELECT * FROM role_menu WHERE role_id=#{roleId}")
    RoleMenu findByRoleIdAndMenuId(long roleId, long menuId);

    @Select("SELECT * FROM role_menu WHERE menu_id=#{menuId}")
    List<RoleMenu> findByMenuId(long menuId);

    @Select("SELECT * FROM role_menu")
    List<RoleMenu> selectAllRoleMenus();

    List<RoleMenu> selectRoleMenusByRoleId(long roleId);

}