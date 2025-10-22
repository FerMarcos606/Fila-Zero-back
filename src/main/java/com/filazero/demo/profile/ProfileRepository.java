package com.filazero.demo.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    // Si querés buscar por DNI, por ejemplo:
    Optional<ProfileEntity> findByDni(String dni);

    // Si querés buscar por nombre completo:
    List<ProfileEntity> findByNameContainingIgnoreCase(String name);
}
