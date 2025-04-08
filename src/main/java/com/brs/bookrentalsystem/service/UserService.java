package com.brs.bookrentalsystem.service;

import com.brs.bookrentalsystem.dto.User;
import com.brs.bookrentalsystem.mapper.UserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public boolean usernameExists(String username) {
        return userMapper.findByUsername(username) != null;
    }

    public void registerUser(User user) {
        userMapper.registerUser(user);
    }

    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findByUserId(int userId) {
        return userMapper.findByUserId(userId);
    }

    public void updateUser(int userId, User user) {
        user.setUserId(userId);
        userMapper.updateUser(user);
    }

    public void deleteUser(int userId) {
        User existingUser = findByUserId(userId);
        if (existingUser == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
        userMapper.deleteUser(userId);
    }

}