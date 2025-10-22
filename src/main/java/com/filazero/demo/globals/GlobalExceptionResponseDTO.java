package com.filazero.demo.globals;

import java.time.LocalDateTime;

public record GlobalExceptionResponseDTO(
       int status,
        String error,
        String message,
        LocalDateTime timestamp
) {}