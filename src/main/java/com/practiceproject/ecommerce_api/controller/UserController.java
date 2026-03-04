package com.practiceproject.ecommerce_api.controller;

import com.practiceproject.ecommerce_api.entity.User;
import com.practiceproject.ecommerce_api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

//    Get all users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
