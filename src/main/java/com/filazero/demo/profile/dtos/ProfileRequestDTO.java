package com.filazero.demo.profile.dtos;

public record ProfileRequestDTO(
    String name,
    String firstSurname,
    String secondSurname,
    String dni,
    String phoneNumber,
    String avatar // ¿URL or base64?
) {}

