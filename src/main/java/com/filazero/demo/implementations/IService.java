package com.filazero.demo.implementations;

import java.util.List;

public interface IService<T, U> {
    
    T createEntity(U requestDTO);
    T getByID(Long id);
    T updateEntity(Long id, U requestDTO);
    void deleteEntity(Long id);
    List<T> getEntities();
}

// getByUsername(String username)?? 