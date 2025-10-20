package com.filazero.demo.products;

import org.springframework.stereotype.Component;
import com.filazero.demo.products.dtos.ProductsRequestDTO;
import com.filazero.demo.products.dtos.ProductsResponseDTO;

@Component
public class ProductsMapper {

    public ProductEntity toEntity(ProductsRequestDTO dto) {
        ProductEntity product = new ProductEntity();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setImageUrl(dto.image());
        product.setAvailable(dto.available());
        return product;
    }

     public ProductsResponseDTO toDTO(ProductEntity entity) {
        return new ProductsResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            entity.getAvailable(),
            entity.getImageUrl() 
        );
    }

    public void updateProductsFromDTO(ProductEntity entity, ProductsRequestDTO dto) {
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setImageUrl(dto.image());
        entity.setAvailable(dto.available());
    }
}
