package com.junenatte.consumer.controller;

import com.junenatte.consumer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("test")
    public String test(){
        return "this is consumer test: "+userService.userTest();
    }
}
