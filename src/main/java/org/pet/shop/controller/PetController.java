package org.pet.shop.controller;

import org.pet.shop.model.Pet;
import org.pet.shop.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("")
    public ResponseEntity<List<Pet>> getPets() {
        return petService.getPets();
    }

    @PostMapping("/query")
    public ResponseEntity<List<Pet>> getPetsByName(@RequestBody Pet pet) {
        System.out.println(pet.getName());
        return petService.getPetsByName(pet.getName());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable UUID id) {
        System.out.println(id);
        return petService.getPetById(id);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Pet> getPetById(@PathVariable UUID id) {
//        System.out.println(id);
//        return petService.getPetById(id);
//    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet Pet) {
        System.out.println(Pet.toString());
        return petService.createPet(Pet);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable UUID id, @RequestBody Pet newPet) {
        return petService.updatePet(id, newPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable UUID id) {
        return petService.deletePet(id);
    }


}
