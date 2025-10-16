package com.filazero.demo.customer;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

    // GET → todos los clientes
    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        return customerService.findAll();
    }

    // GET → cliente por id
    @GetMapping("/{id}")
    public CustomerResponseDTO getById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    // POST → crear cliente
    @PostMapping
    public CustomerResponseDTO create(@RequestBody CustomerRequestDTO dto) {
        return customerService.create(dto);
    }

    // PUT → actualizar cliente
    @PutMapping("/{id}")
    public CustomerResponseDTO update(@PathVariable Long id, @RequestBody CustomerRequestDTO dto) {
        return customerService.update(id, dto);
    }

    // DELETE → eliminar cliente
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
