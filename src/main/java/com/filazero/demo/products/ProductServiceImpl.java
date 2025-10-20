package com.filazero.demo.products;


import com.filazero.demo.products.dtos.ProductsRequestDTO;
import com.filazero.demo.products.dtos.ProductsResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductsMapper productsMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductsMapper productsMapper) {
        this.productRepository = productRepository;
        this.productsMapper = productsMapper;
    }

    @Override
    public ProductsResponseDTO createEntity(ProductsRequestDTO dto) {
        ProductEntity entity = productsMapper.toEntity(dto);
        ProductEntity saved = productRepository.save(entity);
        return productsMapper.toDTO(saved);
    }

    @Override
    public ProductsResponseDTO getByID(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productsMapper.toDTO(entity);
    }

    @Override
    public ProductsResponseDTO updateEntity(Long id, ProductsRequestDTO dto) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setAvailable(dto.available());
        entity.setImageUrl(dto.image());

        ProductEntity updated = productRepository.save(entity);
        return productsMapper.toDTO(updated);
    }

    @Override
    public void deleteEntity(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductsResponseDTO> getEntities() {
        return productRepository.findAll()
                .stream()
                .map(productsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsResponseDTO> getAvailableProducts() {
        return productRepository.findByAvailableTrue()
                .stream()
                .map(productsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsResponseDTO> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(productsMapper::toDTO)
                .collect(Collectors.toList());
    }
}
