package com.filazero.demo.customer;

import org.springframework.stereotype.Component;
import com.filazero.demo.customer.dtos.CustomerResponseDTO; 
import com.filazero.demo.customer.dtos.CustomerRequestDTO;
import com.filazero.demo.profile.ProfileMapper;
import com.filazero.demo.profile.dtos.ProfileResponseDTO;
import com.filazero.demo.role.RoleEntity;
import com.filazero.demo.role.dtos.RoleResponseDTO;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomerMapper {

    private final ProfileMapper profileMapper;

    public CustomerMapper(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }

    // Convertir CustomerEntity → CustomerResponseDTO
    public CustomerResponseDTO toResponseDTO(CustomerEntity entity) {
        if (entity == null) return null;

        Set<RoleResponseDTO> roles = Stream.of(entity.getRole())
            .filter(Objects::nonNull)
            .map(role -> new RoleResponseDTO(role.getId_role(), role.getName()))
            .collect(Collectors.toSet());

        ProfileResponseDTO profileDTO = profileMapper.toResponseDTO(entity.getProfile());

        return new CustomerResponseDTO(
            entity.getId(),
            entity.getUsername(),
            profileDTO,
            roles
        );
    }

    // Convertir CustomerRequestDTO → CustomerEntity (requiere RoleEntity)
    public CustomerEntity toEntity(CustomerRequestDTO dto, RoleEntity role) {
        if (dto == null) return null;

        CustomerEntity entity = new CustomerEntity();
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setRole(role);
        return entity;
    }

    // Actualizar CustomerEntity con CustomerRequestDTO
    public void updateEntityFromDTO(CustomerRequestDTO dto, CustomerEntity entity, RoleEntity role) {
        if (dto.username() != null) entity.setUsername(dto.username());
        if (dto.email() != null) entity.setEmail(dto.email());
        if (dto.password() != null) entity.setPassword(dto.password());
        if (role != null) entity.setRole(role);
    }

    // Convertir CustomerEntity → CustomerRequestDTO
    public CustomerRequestDTO toRequestDTO(CustomerEntity entity) {
        if (entity == null) return null;

        return new CustomerRequestDTO(
            entity.getUsername(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getRole().getId_role()
        );
    }
}