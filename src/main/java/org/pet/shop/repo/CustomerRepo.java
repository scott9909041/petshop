package org.pet.shop.repo;

import org.pet.shop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo  extends JpaRepository<Customer, Long> {
}
