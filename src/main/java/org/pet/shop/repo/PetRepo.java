package org.pet.shop.repo;

import org.pet.shop.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PetRepo extends JpaRepository<Pet, Long> {
    Optional<Pet> findById(UUID id);

    void deleteById(UUID id);

    Iterable<Pet> findAllByNameLike(String s);
}
