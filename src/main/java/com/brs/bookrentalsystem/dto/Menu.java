package com.brs.bookrentalsystem.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Menu {
    private long menuId;
    private String menuName;
    private String menuUrl;
    private long parentMenuId;
    private long displayOrder;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Menu> childMenus;

}
