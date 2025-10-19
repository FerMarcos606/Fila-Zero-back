package com.filazero.demo.customer;

import org.springframework.stereotype.Service;
import com.filazero.demo.customer.dtos.*;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RoleRepository roleRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerResponseDTO> getEntities() {
        return customerRepository.findAll()
                .stream()
                .map(c -> customerMapper.toResponseDTO(c))
                .toList();
    }

    @Override
    public CustomerResponseDTO createEntity(CustomerRequestDTO dto) {
        CustomerEntity entity = customerMapper.toEntity(dto);
        CustomerEntity saved = customerRepository.save(entity);
        return customerMapper.toResponseDTO(saved);
    }

    @Override
    public CustomerResponseDTO getByID(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    @Override
    public CustomerResponseDTO updateEntity(Long id, CustomerRequestDTO dto) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        customerMapper.updateEntityFromDTO(dto, entity);
        return customerMapper.toResponseDTO(customerRepository.save(entity));
    }

    @Override
    public void deleteEntity(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerResponseDTO> searchByName(String name) {
        return customerRepository.findByUsernameContainingIgnoreCase(name)
                .stream()
                .map(c -> customerMapper.toResponseDTO(c))
                .toList();
    }

    @Override
    public CustomerResponseDTO getByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customerMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }
}
