package com.filazero.demo.profile;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filazero.demo.implementations.IService;
import com.filazero.demo.profile.dtos.ProfileRequestDTO;
import com.filazero.demo.profile.dtos.ProfileResponseDTO;

@Service
public class ProfileServiceImpl implements IService<ProfileResponseDTO, ProfileRequestDTO> {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public ProfileResponseDTO createEntity(ProfileRequestDTO dto) {
        ProfileEntity entity = profileMapper.toEntity(dto);
        ProfileEntity saved = profileRepository.save(entity);
        return profileMapper.toDTO(saved);
    }

    @Override
    public ProfileResponseDTO getByID(Long id) {
        ProfileEntity entity = profileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));
        return profileMapper.toDTO(entity);
    }

    @Override
    public ProfileResponseDTO updateEntity(Long id, ProfileRequestDTO dto) {
        ProfileEntity entity = profileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));
        profileMapper.updateEntity(entity, dto);
        ProfileEntity updated = profileRepository.save(entity);
        return profileMapper.toDTO(updated);
    }

    @Override
    public void deleteEntity(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<ProfileResponseDTO> getEntities() {
        return profileRepository.findAll().stream()
            .map(profileMapper::toDTO)
            .collect(Collectors.toList());
    }
}
