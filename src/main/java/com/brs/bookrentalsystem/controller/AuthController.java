package com.brs.bookrentalsystem.controller;

import com.brs.bookrentalsystem.dto.LoginResponse;
import com.brs.bookrentalsystem.dto.User;
import com.brs.bookrentalsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest, HttpSession session) {
        User user = userService.login(loginRequest.getUserName(), loginRequest.getPassword());
        if (user != null) {
            session.setAttribute("username", user.getUserName());
            session.setAttribute("role", user.getRole());
            String token = user.getUserName();
            return ResponseEntity.ok(new LoginResponse(token, user.getRole()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        session.invalidate();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logged out successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (username != null && role != null) {
            return ResponseEntity.ok(new LoginResponse(username, role));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session invalid");
    }
}