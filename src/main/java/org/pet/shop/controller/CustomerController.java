package org.pet.shop.controller;

import org.pet.shop.model.Customer;
import org.pet.shop.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer Customer) {
        return customerService.createCustomer(Customer);
    }

}
