package org.pet.shop.controller;

import org.pet.shop.model.Pet;
import org.pet.shop.service.PetService;
import org.pet.shop.vo.PetCreatePayload;
import org.pet.shop.vo.PetDetail;
import org.pet.shop.vo.PetUpdatePayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("")
    public ResponseEntity<List<PetDetail>> getPets() {
        return petService.getPets();
    }

    @PostMapping("/query")
    public ResponseEntity<List<PetDetail>> getPetsByName(@RequestBody Pet pet) {
        return petService.getPetsByName(pet.getName());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PetDetail> getPetById(@PathVariable UUID id) {
        return petService.getPetById(id);
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody PetCreatePayload PetCreatePayload) {
        return petService.createPet(PetCreatePayload);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable UUID id, @RequestBody PetUpdatePayload newPet) {
        return petService.updatePet(id, newPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable UUID id) {
        return petService.deletePet(id);
    }

}
