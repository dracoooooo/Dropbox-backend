package com.dropbox.controller;

import com.dropbox.entity.User;
import com.dropbox.service.UserService;
import com.dropbox.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Response login(@RequestBody User user){

        return userService.login(user);
    }

    @PostMapping("/register")
    public Response register(@RequestBody User user){

        return userService.register(user);
    }

    @PostMapping("/check")
    public Response check(@RequestBody User user){
        String username = user.getUsername();
        return userService.check(user);
    }

}
