package com.brs.bookrentalsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brs.bookrentalsystem.dto.Menu;
import com.brs.bookrentalsystem.mapper.MenuMapper;

@Service
public class MenuService {

  @Autowired
  private MenuMapper menuMapper;

  public ResponseEntity<Menu> addMenu(Menu menu) {
    menuMapper.insertMenu(menu);
    return ResponseEntity.status(HttpStatus.CREATED).body(menu);
  }

  public ResponseEntity<Menu> updateMenu(Menu menu) {
    Menu existingMenu = menuMapper.findMenuById(menu.getMenuId());
    if (existingMenu == null) {
      return ResponseEntity.notFound().build();
    }
    menuMapper.updateMenu(menu);
    return ResponseEntity.ok(menu);
  }

  public ResponseEntity<Void> deleteMenu(long menuId) {
    Menu menu = menuMapper.findMenuById(menuId);
    if (menu == null) {
      return ResponseEntity.notFound().build();
    }

    List<Menu> childMenus = menuMapper.findChildMenus(menuId);
    if (!childMenus.isEmpty()) {
      throw new IllegalStateException("Cannot delete menu with child menus");
    }
    return null;

    // roleMenuMapper.removeSpecificMenuFromRole(null, menuId);
    // menuMapper.deleteMenu(menuId);
    // return ResponseEntity.noContent().build();
  }

  public ResponseEntity<List<Menu>> getAllMenus() {
    List<Menu> menus = menuMapper.findAllMenus();
    return ResponseEntity.ok(menus);
  }

  public ResponseEntity<List<Menu>> getMenuHierarchy() {
    List<Menu> rootMenus = menuMapper.findRootMenus();
    rootMenus.forEach(menu -> {
      List<Menu> childMenus = menuMapper.findChildMenus(menu.getMenuId());
      menu.setChildMenus(childMenus);
    });
    return ResponseEntity.ok(rootMenus);
  }

  public ResponseEntity<Menu> getMenuById(long menuId) {
    Menu menu = menuMapper.findMenuById(menuId);
    if (menu == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(menu);
  }

  // public ResponseEntity<RoleMenu> assignMenuToRole(RoleMenu roleMenu) {
  // roleMenuMapper.assignMenuToRole(roleMenu);
  // return ResponseEntity.status(HttpStatus.CREATED).body(roleMenu);
  // }

  // public ResponseEntity<RoleMenu> updateRoleMenuPermissions(RoleMenu roleMenu)
  // {
  // roleMenuMapper.updateRoleMenuPermissions(roleMenu);
  // return ResponseEntity.ok(roleMenu);
  // }

  // public ResponseEntity<Void> removeMenuFromRole(long roleMenuId) {
  // roleMenuMapper.removeMenuFromRole(roleMenuId);
  // return ResponseEntity.noContent().build();
  // }

  // public ResponseEntity<List<RoleMenu>> getMenusByRoleId(long roleId) {
  // List<RoleMenu> roleMenus = roleMenuMapper.findMenuDetailsByRoleId(roleId);
  // return ResponseEntity.ok(roleMenus);
  // }

}
