package com.filazero.demo.products;

import com.filazero.demo.products.dtos.ProductsRequestDTO;
import com.filazero.demo.products.dtos.ProductsResponseDTO;
import com.filazero.demo.products.ProductsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductsMapper productsMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductEntity entity;
    private ProductsResponseDTO dto;

    @BeforeEach
    void setUp() {
        entity = new ProductEntity();
        entity.setId(1L);
        entity.setName("Pollo");
        entity.setDescription("Producto fresco");
        entity.setPrice(BigDecimal.TEN);
        entity.setAvailable(true);
        entity.setImageUrl("pollo.jpg");

        dto = new ProductsResponseDTO(
            1L, "Pollo", "Producto fresco", BigDecimal.TEN, true, "pollo.jpg"
        );
    }

    @Test
    void testGetAvailableProducts_returnsAvailableList() {
        when(productRepository.findByAvailableTrue()).thenReturn(List.of(entity));
        when(productsMapper.toDTO(entity)).thenReturn(dto);

        List<ProductsResponseDTO> result = productService.getAvailableProducts();

        assertEquals(1, result.size());
        assertEquals("Pollo", result.get(0).name());
    }

    @Test
    void testGetProductsByName_returnsMatchingList() {
        when(productRepository.findByNameContainingIgnoreCase("pollo")).thenReturn(List.of(entity));
        when(productsMapper.toDTO(entity)).thenReturn(dto);

        List<ProductsResponseDTO> result = productService.getProductsByName("pollo");

        assertEquals(1, result.size());
        assertEquals("Pollo", result.get(0).name());
    }

    @Test
    void testGetProductsByName_returnsEmptyList() {
        when(productRepository.findByNameContainingIgnoreCase("noexiste")).thenReturn(Collections.emptyList());

        List<ProductsResponseDTO> result = productService.getProductsByName("noexiste");

        assertTrue(result.isEmpty());
    }
}