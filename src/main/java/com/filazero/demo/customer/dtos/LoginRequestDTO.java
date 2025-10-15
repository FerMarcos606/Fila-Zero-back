package com.filazero.demo.customer.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO (
    
    
    @NotBlank(message = "El email es obligatorio para iniciar sesión.")
    @Email(message = "El formato del email es inválido.")
    String email, 


    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.") 
    String password
    )
{}
