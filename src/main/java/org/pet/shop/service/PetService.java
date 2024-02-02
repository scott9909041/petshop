package org.pet.shop.service;

import org.pet.shop.model.Pet;
import org.pet.shop.repo.PetRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {
    private final PetRepo petRepo;

    public PetService(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    public ResponseEntity<List<Pet>> getPets() {
        try {
            List<Pet> petList = new ArrayList<>();
            petRepo.findAll().forEach(petList::add);

            if (petList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(petList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<Pet>> getPetsByName(String name) {
        try {
            List<Pet> petList = new ArrayList<>();

            petRepo.findAllByName(name).forEach(petList::add);

            if (petList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(petList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Pet> getPetById(UUID id) {
        Optional<Pet> petData = petRepo.findById(id);

        if (petData.isPresent()) {
            return new ResponseEntity<>(petData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Pet> createPet(Pet pet) {
        Pet petObj = petRepo.save(pet);

        return new ResponseEntity<>(petObj, HttpStatus.OK);
    }

    public ResponseEntity<Pet> updatePet(UUID id, Pet newPet) {

        Optional<Pet> oldPetData = petRepo.findById(id);

        if (oldPetData.isPresent()) {
            Pet updatePetData = getPet(newPet, oldPetData);

            Pet petObj = petRepo.save(updatePetData);

            return new ResponseEntity<>(petObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    private static Pet getPet(Pet newPet, Optional<Pet> oldPetData) {
        Pet updatePetData = oldPetData.get();
        String name = newPet.getName();
        if (name == null) {
            updatePetData.setName(updatePetData.getName());
        } else {
            updatePetData.setName(name);
        }

        String type = newPet.getType();
        if (type == null) {
            updatePetData.setType(updatePetData.getType());
        } else {
            updatePetData.setType(type);
        }

        String color = newPet.getColor();
        if (color == null) {
            updatePetData.setColor(updatePetData.getColor());
        } else {
            updatePetData.setColor(color);
        }

        Pet father = newPet.getFather();
        if (color == null) {
            updatePetData.setColor(updatePetData.getColor());
        } else {
            updatePetData.setColor(color);
        }

        return updatePetData;
    }

    public ResponseEntity<Pet> deletePet(UUID id) {
        petRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
