package com.filazero.demo.customer;

import java.util.List;

import com.filazero.demo.customer.dtos.CustomerRequestDTO;
import com.filazero.demo.customer.dtos.CustomerResponseDTO;
import com.filazero.demo.implementations.IService;

public interface ICustomerService extends IService<CustomerResponseDTO, CustomerRequestDTO> {

    CustomerResponseDTO getByEmail(String email);
    List<CustomerResponseDTO> searchByName(String name);
}
