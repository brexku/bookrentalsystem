package com.brs.bookrentalsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brs.bookrentalsystem.dto.RoleMenu;
import com.brs.bookrentalsystem.mapper.RoleMenuMapper;

@Service
public class RoleMenuService {

    private final RoleMenuMapper roleMenuMapper;

    public RoleMenuService(RoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    public ResponseEntity<RoleMenu> createRoleMenu(RoleMenu roleMenu) {
        roleMenu.setCreatedAt(LocalDateTime.now());
        roleMenu.setUpdatedAt(LocalDateTime.now());
        roleMenuMapper.insertRoleMenu(roleMenu);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleMenu);
    }

    public ResponseEntity<RoleMenu> getRoleMenuById(long roleMenuId) {
        RoleMenu roleMenu = roleMenuMapper.selectRoleMenuById(roleMenuId);
        if (roleMenu == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleMenu);
    }

    public ResponseEntity<List<RoleMenu>> getAllRoleMenus() {
        List<RoleMenu> roleMenus = roleMenuMapper.selectAllRoleMenus();
        return ResponseEntity.ok(roleMenus);
    }

    @Transactional
    public ResponseEntity<RoleMenu> updateRoleMenu(RoleMenu roleMenu) {
        RoleMenu existingRoleMenu = roleMenuMapper.selectRoleMenuById(roleMenu.getRoleMenuId());
        if (existingRoleMenu == null) {
            return ResponseEntity.notFound().build();
        }
        
        roleMenu.setUpdatedAt(LocalDateTime.now());
        roleMenuMapper.updateRoleMenu(roleMenu);
        return ResponseEntity.ok(roleMenu);
    }

    @Transactional
    public ResponseEntity<Void> deleteRoleMenu(long roleMenuId) {
        RoleMenu roleMenu = roleMenuMapper.selectRoleMenuById(roleMenuId);
        if (roleMenu == null) {
            return ResponseEntity.notFound().build();
        }
        
        roleMenuMapper.deleteRoleMenu(roleMenuId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<RoleMenu>> getRoleMenusByRoleId(long roleId) {
        List<RoleMenu> roleMenus = roleMenuMapper.selectRoleMenusByRoleId(roleId);
        if (roleMenus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roleMenus);
    }

    public ResponseEntity<RoleMenu> findByRoleIdAndMenuId(long roleId, long menuId) {
        RoleMenu roleMenu = roleMenuMapper.findByRoleIdAndMenuId(roleId, menuId);
        if (roleMenu == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleMenu);
    }

    @Transactional
    public ResponseEntity<RoleMenu> assignMenuToRole(RoleMenu roleMenu) {
        // Check if the assignment already exists
        RoleMenu existing = roleMenuMapper.findByRoleIdAndMenuId(
            roleMenu.getRoleId(), 
            roleMenu.getMenuId()
        );
        
        if (existing != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(existing);
        }
        
        roleMenu.setCreatedAt(LocalDateTime.now());
        roleMenu.setUpdatedAt(LocalDateTime.now());
        roleMenuMapper.insertRoleMenu(roleMenu);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleMenu);
    }

    @Transactional
    public ResponseEntity<RoleMenu> updateRoleMenuPermissions(RoleMenu roleMenu) {
        RoleMenu existing = roleMenuMapper.selectRoleMenuById(roleMenu.getRoleMenuId());
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        
        roleMenu.setUpdatedAt(LocalDateTime.now());
        roleMenuMapper.updateRoleMenu(roleMenu);
        return ResponseEntity.ok(roleMenu);
    }

    @Transactional
    public ResponseEntity<Void> removeMenuFromRole(long roleMenuId) {
        RoleMenu existing = roleMenuMapper.selectRoleMenuById(roleMenuId);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        
        roleMenuMapper.deleteRoleMenu(roleMenuId);
        return ResponseEntity.noContent().build();
    }


}
