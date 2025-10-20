package com.filazero.demo.products;

import java.util.List;

import com.filazero.demo.implementations.IService;
import com.filazero.demo.products.dtos.ProductsRequestDTO;
import com.filazero.demo.products.dtos.ProductsResponseDTO;



public interface ProductService extends IService<ProductsResponseDTO, ProductsRequestDTO> {

    List<ProductsResponseDTO> getAvailableProducts();

    List<ProductsResponseDTO> getProductsByName(String name);
}


