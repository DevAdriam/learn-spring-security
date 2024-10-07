package com.ecommerce.Ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LogIn
        (
                @NotBlank(message = "required username")
                @NotNull
                String username,

                @NotNull
                @NotBlank()
                @Size(min = 6,max = 12 , message = "password must be between 6 and 12")
                String password
        )
{


}
