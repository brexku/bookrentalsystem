package com.brs.bookrentalsystem.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
public class AdminOwnerController {

    @GetMapping("/admin-resource")
    public String accessAdminResource(HttpSession session) {
        String role = (String) session.getAttribute("role");

        if ("ADMIN".equals(role)) {
            return "Access granted to Admin resource.";
        }

        return "Access denied. Admin role required.";
    }

    @GetMapping("/owner-resource")
    public String accessOwnerResource(HttpSession session) {
        String role = (String) session.getAttribute("role");

        if ("OWNER".equals(role)) {
            return "Access granted to Owner resource.";
        }

        return "Access denied. Owner role required.";
    }

    @GetMapping("/common-resource")
    public String accessCommonResource(HttpSession session) {
        String role = (String) session.getAttribute("role");

        if ("ADMIN".equals(role) || "OWNER".equals(role)) {
            return "Access granted to Admin or Owner resource.";
        }

        return "Access denied. Admin or Owner role required.";
    }
}
