package com.example.demo.dao;

import com.example.demo.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void deleteUser(Long id);

    void createUser(User user);

    User verifyUser (User user);

}
