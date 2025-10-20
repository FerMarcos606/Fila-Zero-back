package com.filazero.demo.products.dtos;

import java.math.BigDecimal;

public  record ProductsResponseDTO(

    Long id,
    String name,
    String description,
    BigDecimal price, 
    Boolean available,
    String image
   
) {}
    

