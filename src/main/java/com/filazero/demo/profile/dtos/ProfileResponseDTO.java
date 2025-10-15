package com.filazero.demo.profile.dtos;

public record ProfileResponseDTO (
    
    String dni,
    String name,
    String firstSurname,
    String secondSurname,
    String phoneNumber,
    String avatarUrl 
)

{}
