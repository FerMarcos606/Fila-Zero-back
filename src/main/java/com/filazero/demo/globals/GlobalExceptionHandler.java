package com.filazero.demo.globals;

import java.time.LocalDateTime;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.filazero.demo.role.exceptions.RoleException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<GlobalExceptionResponseDTO> handleRoleNotFound(RoleNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RoleException.class)
    public ResponseEntity<GlobalExceptionResponseDTO> handleRoleException(RoleException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalExceptionResponseDTO> handleGeneral(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + ex.getMessage());
    }

    // @ExceptionHandler(ProfileNotFoundException.class)
    // public ResponseEntity<GlobalExceptionResponseDTO> handleProfileNotFound(ProfileNotFoundException ex) {
    //     return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    // }


    private ResponseEntity<GlobalExceptionResponseDTO> buildResponse(HttpStatus status, String message) {
        GlobalExceptionResponseDTO error = new GlobalExceptionResponseDTO(
                status.value(),
                status.getReasonPhrase(),
                message,
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(error);
    }
}
