package com.filazero.demo.customer;

import org.springframework.stereotype.Component;
import com.filazero.demo.customer.dtos.CustomerResponseDTO; 
import com.filazero.demo.customer.dtos.CustomerRequestDTO; 
import com.filazero.demo.profile.dtos.ProfileResponseDTO;
import com.filazero.demo.role.dtos.RoleResponseDTO;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomerMapper {

    // Convertir CustomerEntity → CustomerResponseDTO
    public CustomerResponseDTO toResponseDTO(CustomerEntity entity) {
        if (entity == null) return null;

        Set<RoleResponseDTO> roles = Stream.of(entity.getRole())
            .filter(Objects::nonNull)
            .map(role -> new RoleResponseDTO(role.getId_role(), role.getName()))
            .collect(Collectors.toSet());


       // Asumiendo que ProfileResponseDTO tiene 7 campos: (Long, S, S, S, S, S, String)
            ProfileResponseDTO profileDTO = new ProfileResponseDTO(
            entity.getProfile().getId_profile(),  // 1. Long
            entity.getProfile().getName(),        // 2. String
            entity.getProfile().getFirstSurname(),// 3. String
            entity.getProfile().getSecondSurname(),// 4. String
            entity.getProfile().getDni(),         // 5. String
            entity.getProfile().getPhoneNumber()  // 6. String
        );

             
        return new CustomerResponseDTO(
                entity.getId(),
                entity.getEmail(), 
                profileDTO,        
                roles
        );
    }

    public CustomerRequestDTO toRequestDTO(CustomerEntity entity) {
    if (entity == null) return null;

    return new CustomerRequestDTO(
        entity.getUsername(),
        entity.getEmail(),
        entity.getPassword(),
        entity.getRole().getId_role() // ← extraemos el ID del rol asignado
    );
}

    // Convertir CustomerRequestDTO → CustomerEntity
    public CustomerEntity toEntity(CustomerRequestDTO dto) {
        if (dto == null) return null;
        CustomerEntity entity = new CustomerEntity();
        // ASUMO que dto.name() es en realidad dto.email() para crear el usuario
        entity.setEmail(dto.username()); 
        entity.setPassword(dto.password());
        return entity;
    }

    // Actualizar CustomerEntity con CustomerRequestDTO
    public void updateEntityFromDTO(CustomerRequestDTO dto, CustomerEntity entity) {
        // ASUMO que dto.name() es en realidad dto.email()
        if (dto.username() != null) entity.setEmail(dto.username()); 
        if (dto.password() != null) entity.setPassword(dto.password());
    }
}