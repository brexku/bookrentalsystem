package com.brs.bookrentalsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.brs.bookrentalsystem.dto.User;

@Mapper
public interface UserMapper {

        @Select("SELECT * FROM users")
        List<User> getAllUsers();

        @Select("SELECT * FROM users WHERE user_id = #{userId}")
        User findByUserId(int userId);

        @Select("SELECT * FROM users WHERE user_name = #{userName}")
        User findByUsername(String userName);

        @Insert("INSERT INTO users (first_name, last_name, user_phone, user_name, password, role) " +
                        "VALUES (#{firstName}, #{lastName}, #{userPhone}, #{userName}, #{password}, #{role})")
        void registerUser(User user);

        @Update("UPDATE users SET first_name = #{firstName}, last_name = #{lastName}, user_phone = #{userPhone}, " +
                        "user_name = #{userName}, password = #{password}, role = #{role} WHERE user_id = #{userId}")
        void updateUser(User user);

        @Delete("DELETE FROM users WHERE user_id = #{userId}")
        void deleteUser(int userId);
}