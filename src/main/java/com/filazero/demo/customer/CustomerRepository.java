package com.filazero.demo.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    // Usaremos 'email' como identificador único???
    Optional<CustomerEntity> findByEmail(String email);   

    List<CustomerEntity> findByProfileName(String name);

    Optional<CustomerEntity> findByProfileDni(String dni);

    List<CustomerEntity> findByProfileNameContainingIgnoreCase(String nameSearchTerm);

     List<CustomerEntity> findByUsernameContainingIgnoreCase(String username);
}
