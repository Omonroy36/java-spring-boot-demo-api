package com.example.demo.controllers;

import com.example.demo.dao.UserDao;
import com.example.demo.models.User;
import com.example.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/user/{id}")
    public User getUser(@PathVariable  Long id){
        User user = new User();
        return user;
    }

    @RequestMapping(value = "api/user", method = RequestMethod.POST)
    public void createUser(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.createUser(user);
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id){
        userDao.deleteUser(id);
    }

    @RequestMapping(value = "api/user")
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){
        String userId = jwtUtil.getKey(token);
        if(userId != null){
            return userDao.getUsers();
        }
        return null;
    }

}
