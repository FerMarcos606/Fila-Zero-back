package com.filazero.demo.customer;

import com.filazero.demo.customer.CustomerController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)

@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAllowPublicAccessToRegisterEndpoint() throws Exception {
        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Fer\", \"email\": \"fer@example.com\"}"))
                .andExpect(status().isOk()); // o isCreated(), según tu lógica
    }
}