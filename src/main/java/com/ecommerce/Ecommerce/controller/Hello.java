package com.ecommerce.Ecommerce.controller;


import com.ecommerce.Ecommerce.model.User;
import com.ecommerce.Ecommerce.services.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Hello {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public List<User> testHelloWorld(HttpSession session){
         String sessionId = session.getId();
         return userService.fetchUsersList();
    }

    @GetMapping("/csrf")
    public CsrfToken getCSRFToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("increase-salary")
    public  String increaseSalary(){
            // Database operation
        return "salary increased successfully";
    }


}
