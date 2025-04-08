package com.brs.bookrentalsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brs.bookrentalsystem.dto.Menu;
import com.brs.bookrentalsystem.service.MenuService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/menus")
public class MenuController {

    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Menu> addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable long menuId, @RequestBody Menu menu) {
        menu.setMenuId(menuId);
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable long menuId) {
        return menuService.deleteMenu(menuId);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/hierarchy")
    public ResponseEntity<List<Menu>> getMenuHierarchy() {
        return menuService.getMenuHierarchy();
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable long menuId) {
        return menuService.getMenuById(menuId);
    }

    // @PostMapping("/role-assignments")
    // public ResponseEntity<RoleMenu> assignMenuToRole(@RequestBody RoleMenu
    // roleMenu) {
    // return menuService.assignMenuToRole(roleMenu);
    // }

    // @PutMapping("/role-assignments/{roleMenuId}")
    // public ResponseEntity<RoleMenu> updateRoleMenuPermissions(
    // @PathVariable long roleMenuId, @RequestBody RoleMenu roleMenu) {
    // roleMenu.setRoleMenuId(roleMenuId);
    // return menuService.updateRoleMenuPermissions(roleMenu);
    // }

    // @DeleteMapping("/role-assignments/{roleMenuId}")
    // public ResponseEntity<Void> removeMenuFromRole(@PathVariable long roleMenuId)
    // {
    // return menuService.removeMenuFromRole(roleMenuId);
    // }

    // @GetMapping("/roles/{roleId}")
    // public ResponseEntity<List<RoleMenu>> getMenusByRoleId(@PathVariable long
    // roleId) {
    // return menuService.getMenuById(roleId);
    // }

}
