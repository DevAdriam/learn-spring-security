package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.LogIn;
import com.ecommerce.Ecommerce.dto.SignUpRequest;
import com.ecommerce.Ecommerce.model.User;
import com.ecommerce.Ecommerce.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
public class authController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public String login(@Valid @RequestBody LogIn dto) {
        System.out.println("reach here");
        User user = new User();

        user.setUsername(dto.username());
        user.setPassword(dto.password());


        return authService.verify(user);
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody SignUpRequest dto) {
        return authService.registerUser(dto);
    }

    @GetMapping("/me")
    public User me(){
        return null;
    }

}
