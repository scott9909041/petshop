package org.pet.shop.repo;

import org.pet.shop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepo  extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(UUID id);
}
