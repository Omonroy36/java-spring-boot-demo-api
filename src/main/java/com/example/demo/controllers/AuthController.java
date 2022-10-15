package com.example.demo.controllers;

import com.example.demo.dao.UserDao;
import com.example.demo.models.User;
import com.example.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        User verifiedUser = userDao.verifyUser(user);
        if(verifiedUser != null){
          return jwtUtil.create(String.valueOf(verifiedUser.getId()), verifiedUser.getEmail());
        }
        return "FAIL";
    }
}
