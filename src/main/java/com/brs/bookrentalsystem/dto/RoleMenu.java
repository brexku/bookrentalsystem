package com.brs.bookrentalsystem.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoleMenu {

    private long roleMenuId;
    private long roleId;
    private long menuId;
    private Boolean canView;
    private Boolean canEdit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
