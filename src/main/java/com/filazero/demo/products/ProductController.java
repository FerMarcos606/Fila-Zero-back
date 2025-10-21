package com.filazero.demo.products;

import com.filazero.demo.products.dtos.ProductsResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/available")
    public List<ProductsResponseDTO> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

    @GetMapping("/search")
    public List<ProductsResponseDTO> getProductsByName(@RequestParam String name) {
        return productService.getProductsByName(name);
    }
}
