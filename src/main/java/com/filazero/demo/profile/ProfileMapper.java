package com.filazero.demo.profile;

import org.springframework.stereotype.Component;

import com.filazero.demo.profile.dtos.ProfileRequestDTO;
import com.filazero.demo.profile.dtos.ProfileResponseDTO;
import java.util.Base64;


@Component
public class ProfileMapper {

    public ProfileEntity toEntity(ProfileRequestDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.name());
        entity.setFirstSurname(dto.firstSurname());
        entity.setSecondSurname(dto.secondSurname());
        entity.setDni(dto.dni());
        entity.setPhoneNumber(dto.phoneNumber());
       
        // Convertir String base64 a byte[]
        if (dto.avatar() != null) {
            Base64.getDecoder().decode(dto.avatar());

        }
        return entity;
    }

    public ProfileResponseDTO toDTO(ProfileEntity entity) {
    return new ProfileResponseDTO(
        entity.getId_profile(),
        entity.getName(),
        entity.getFirstSurname(),
        entity.getSecondSurname(),
        entity.getDni(),
        entity.getPhoneNumber(),
        entity.getAvatar() 
    );
}


    public void updateEntity(ProfileEntity entity, ProfileRequestDTO dto) {
        entity.setName(dto.name());
        entity.setFirstSurname(dto.firstSurname());
        entity.setSecondSurname(dto.secondSurname());
        entity.setDni(dto.dni());
        entity.setPhoneNumber(dto.phoneNumber());
        entity.setAvatar(dto.avatar());
    }

    public ProfileResponseDTO toResponseDTO(ProfileEntity profile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toResponseDTO'");
    }
}
