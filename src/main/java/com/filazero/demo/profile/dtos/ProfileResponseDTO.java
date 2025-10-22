package com.filazero.demo.profile.dtos;


public record ProfileResponseDTO (
    Long id,
    String name,
    String firstSurname,
    String secondSurname,
    String dni,
    String phoneNumber 
) {}
