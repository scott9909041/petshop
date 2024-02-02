package org.pet.shop.service;

import org.pet.shop.model.Pet;
import org.pet.shop.repo.PetRepo;
import org.pet.shop.vo.PetCreatePayload;
import org.pet.shop.vo.PetDetail;
import org.pet.shop.vo.PetUpdatePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {
    private static PetRepo petRepo = null;

    public PetService(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    public ResponseEntity<List<PetDetail>> getPets() {
        try {
            List<PetDetail> petList = new ArrayList<>();
            petRepo.findAll().forEach(pet -> petList.add(mappingPetDet(pet)));

            if (petList.isEmpty()) {
                return new ResponseEntity<>(petList, HttpStatus.OK);
            }

            return new ResponseEntity<>(petList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<PetDetail>> getPetsByName(String name) {
        try {
            List<PetDetail> petList = new ArrayList<>();

            petRepo.findAllByName(name).forEach(pet -> petList.add(mappingPetDet(pet)));

            if (petList.isEmpty()) {
                return new ResponseEntity<>(petList, HttpStatus.OK);
            }

            return new ResponseEntity<>(petList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PetDetail> getPetById(UUID id) {
        Optional<Pet> petData = petRepo.findById(id);
        Pet pet = petData.orElse(null);

        if (pet != null) {
            return new ResponseEntity<>(mappingPetDet(pet), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Pet> createPet(PetCreatePayload PetCreatePayload) {
        Optional<Pet> fatherData = petRepo.findById(PetCreatePayload.getFather());
        Pet father = fatherData.orElse(null);
        Optional<Pet> motherData = petRepo.findById(PetCreatePayload.getMother());
        Pet  mother =  motherData.orElse(null);

        Pet petObj = Pet.builder().name(PetCreatePayload.getName())
            .type(PetCreatePayload.getType())
            .color(PetCreatePayload.getColor())
            .father(father)
            .mother(mother)
            .build();

        return new ResponseEntity<>(petRepo.save(petObj), HttpStatus.OK);
    }

    public ResponseEntity<Pet> updatePet(UUID id, PetUpdatePayload newPet) {
        Optional<Pet> oldPetData = petRepo.findById(id);

        if (oldPetData.isPresent()) {
            Pet updatePetData = getPet(newPet, oldPetData);

            Pet petObj = petRepo.save(updatePetData);

            return new ResponseEntity<>(petObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private static Pet getPet(PetUpdatePayload newPet, Optional<Pet> oldPetData) {
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

        Optional<Pet> fatherData = petRepo.findById(newPet.getFather());
        Pet father = fatherData.orElse(null);

        if (father == null) {
            updatePetData.setFather(null);
        } else {
            updatePetData.setFather(father);
        }

        Optional<Pet> motherData = petRepo.findById(newPet.getMother());
        Pet mother = motherData.orElse(null);

        if (mother == null) {
            updatePetData.setMother(null);
        } else {
            updatePetData.setMother(mother);
        }

        return updatePetData;
    }

    public ResponseEntity<Pet> deletePet(UUID id) {
        petRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private PetDetail mappingPetDet(Pet pet) {
        Pet father = pet.getFather();
        Pet mother = pet.getMother();

        PetDetail petDetail = PetDetail.builder()
                .id(pet.getId())
                .name(pet.getName())
                .type(pet.getType())
                .color(pet.getColor())
                .build();
        if (father != null) {
            petDetail.setFatherId(father.getId());
            petDetail.setFatherName(father.getName());
        }

        if (mother != null) {
            petDetail.setMotherId(mother.getId());
            petDetail.setMotherName(mother.getName());
        }
        return petDetail;
    }

}
