package com.dropbox.controller;

import com.dropbox.entity.User;
import com.dropbox.service.UserService;
import com.dropbox.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user/login")
    public ResponseVO login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/user/register")
    public ResponseVO register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/user/check")
    public ResponseVO check(@RequestBody User user){
        String username = user.getUsername();
        return userService.check(user);
    }

    @PostMapping("/user/token")
    public ResponseVO checkToken(){
        return userService.checkToken();
    }

}
