package com.filazero.demo.customer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filazero.demo.customer.dtos.CustomerResponseDTO;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    // Usaremos 'email' como identificador único???
    Optional<CustomerEntity> findByEmail(String email);   

    List<CustomerEntity> findByProfileName(String name);

    Optional<CustomerEntity> findByProfileDni(String dni);

    Collection<CustomerResponseDTO> findByNameContainingIgnoreCase(String name);

     List<CustomerEntity> findByUsernameContainingIgnoreCase(String username);
}
