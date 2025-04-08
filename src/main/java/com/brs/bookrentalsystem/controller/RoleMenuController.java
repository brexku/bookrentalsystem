package com.brs.bookrentalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brs.bookrentalsystem.dto.RoleMenu;
import com.brs.bookrentalsystem.service.RoleMenuService;

@RestController
@RequestMapping("/api/role-menus")
public class RoleMenuController {

    @Autowired
    private final RoleMenuService roleMenuService;
    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @PostMapping
    public ResponseEntity<RoleMenu> createRoleMenu(@RequestBody RoleMenu roleMenu) {
        return roleMenuService.createRoleMenu(roleMenu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleMenu> getRoleMenuById(@PathVariable long id) {
        return roleMenuService.getRoleMenuById(id);
    }

}
