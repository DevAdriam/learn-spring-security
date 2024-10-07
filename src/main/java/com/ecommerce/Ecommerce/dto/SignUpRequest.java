package com.ecommerce.Ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @NotBlank(message = "username cannot be empty")
        String username,

        @Email(message = "Invalid email")
        @NotBlank(message = "email cannot be empty")
        String email,

        @Size(min = 6 , max = 20 ,message = "Password must be between 6 and 20")
        String password
){}