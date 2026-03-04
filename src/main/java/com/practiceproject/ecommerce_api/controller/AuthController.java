package com.practiceproject.ecommerce_api.controller;

import com.practiceproject.ecommerce_api.dto.LoginRequest;
import com.practiceproject.ecommerce_api.entity.User;
import com.practiceproject.ecommerce_api.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    //    Register
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return authService.register(user);
    }

    //    Login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    //    Test
    @GetMapping("/test")
    public String test() {
        return "Protected route working";
    }
}
