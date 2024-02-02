package org.pet.shop.service;

import org.pet.shop.model.Customer;
import org.pet.shop.model.Pet;
import org.pet.shop.repo.CustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private SecurityService securityServices;

    public CustomerService(CustomerRepo customerRepo, SecurityService securityServices) {
        this.customerRepo = customerRepo;
        this.securityServices = securityServices;
    }

    public ResponseEntity<Customer> getCustomerById(UUID id) {
        Optional<Customer> customerData = customerRepo.findById(id);

        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<Customer> createCustomer(Customer customer) {
        customer.setEmail(securityServices.encrypt(customer.getEmail()));
        Customer customerObj = customerRepo.save(customer);

        return new ResponseEntity<>(customerObj, HttpStatus.OK);
    }
}
