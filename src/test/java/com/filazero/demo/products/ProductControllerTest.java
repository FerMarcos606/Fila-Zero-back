package com.filazero.demo.products;

import com.filazero.demo.products.dtos.ProductsResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


// @SpringBootTest
// @AutoConfigureMockMvc
// class ProductControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @WithMockUser(username = "fer@example.com", roles = "ADMIN")
//     @Test
//     void getAllProducts_ShouldReturnOk() throws Exception {
//         mockMvc.perform(get("/products/available"))
//                .andExpect(status().isOk());
//     }
// }



@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductsResponseDTO dto;

    @BeforeEach
    void setUp() {
        dto = new ProductsResponseDTO(
            1L, "Pollo", "Producto fresco", BigDecimal.TEN, true, "pollo.jpg"
        );
    }

    // @Test
    // void testGetAvailableProducts_returnsOk() throws Exception {
    //     when(productService.getAvailableProducts()).thenReturn(List.of(dto));

    //     mockMvc.perform(get("/products/available"))
    //         .andDo(print())
    //         .andExpect(status().isOk())
    //         .andExpect(jsonPath("$[0].name").value("Pollo"));
    // }

    @Test
    void testGetProductsByName_returnsOk() throws Exception {
        when(productService.getProductsByName("pollo")).thenReturn(List.of(dto));

        mockMvc.perform(get("/products/search").param("name", "pollo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Pollo"));
    }
}
//     @Test
//     void testDeleteProduct_returnsOk() throws Exception {
//         doNothing().when(productService).deleteEntity(1L);

//         mockMvc.perform(delete("/products/1"))
//             .andDo(print())
//             .andExpect(status().isOk());
//     }
// }

