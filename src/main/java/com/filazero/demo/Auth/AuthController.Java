package com.filazero.demo.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.email(), request.password()
        );

        try {
            Authentication authResult = authenticationManager.authenticate(authentication);
            String token = tokenService.generateToken(authResult);

            // Retornar username y roles si lo necesitás
            String username = authResult.getName();
            String role = authResult.getAuthorities().stream()
                    .map(a -> a.getAuthority())
                    .findFirst()
                    .orElse("CUSTOMER");

            return ResponseEntity.ok(new LoginResponseDTO(token, username, role));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
