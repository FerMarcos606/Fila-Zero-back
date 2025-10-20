package com.filazero.demo.products;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByAvailableTrue();

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
