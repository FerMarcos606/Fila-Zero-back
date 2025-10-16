package com.filazero.demo.customer;

import org.springframework.stereotype.Component;
import com.filazero.demo.customer.dtos.CustomerResponseDTO; 
import com.filazero.demo.customer.dtos.CustomerRequestDTO; 
import com.filazero.demo.profile.dtos.ProfileResponseDTO;
import com.filazero.demo.role.dtos.RoleDTO;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    // Convertir CustomerEntity → CustomerResponseDTO
    public CustomerResponseDTO toResponseDTO(CustomerEntity entity) {
        if (entity == null) return null;

        // 1. Mapear roles
        Set<RoleDTO> roles = entity.getRoles().stream()
                .map(role -> new RoleDTO(role.getId(), role.getName()))
                .collect(Collectors.toSet());

        // 2. Mapear el Perfil (¡CORRECCIÓN CLAVE AQUÍ!)
        // Problema: ProfileEntity tiene byte[], pero el DTO necesita String (Base64)
        // String base64Avatar = null;
        // if (entity.getProfile().getAvatar() != null) {
        //     // Conversión del array de bytes a Base64 String
        //     base64Avatar = Base64.getEncoder().encodeToString(entity.getProfile().getAvatar());
        // }
        
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

    // Convertir CustomerEntity → CustomerRequestDTO (para login)
    public CustomerRequestDTO toRequestDTO(CustomerEntity entity) {
        if (entity == null) return null;
        // ASUMO que getUsername() es en realidad getEmail()
        return new CustomerRequestDTO(entity.getEmail(), entity.getPassword()); 
    }

    // Convertir CustomerRequestDTO → CustomerEntity
    public CustomerEntity toEntity(CustomerRequestDTO dto) {
        if (dto == null) return null;
        CustomerEntity entity = new CustomerEntity();
        // ASUMO que dto.name() es en realidad dto.email() para crear el usuario
        entity.setEmail(dto.name()); 
        entity.setPassword(dto.password());
        return entity;
    }

    // Actualizar CustomerEntity con CustomerRequestDTO
    public void updateEntityFromDTO(CustomerRequestDTO dto, CustomerEntity entity) {
        // ASUMO que dto.name() es en realidad dto.email()
        if (dto.name() != null) entity.setEmail(dto.name()); 
        if (dto.password() != null) entity.setPassword(dto.password());
    }
}