package com.filazero.demo.customer.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequestDTO(

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 3, max = 50, message = "Debe tener entre 3 y 50 caracteres")
    String username,

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Formato de email inválido")
    String email,

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "Debe tener al menos 8 caracteres")
    String password,

    Long roleId // ID del rol / customer
) {}
