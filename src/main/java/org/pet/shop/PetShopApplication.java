package org.pet.shop;

import org.pet.shop.model.Pet;
import org.pet.shop.repo.PetRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShopApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(PetRepo petRepo) {
        Pet father = Pet.builder().name("Father").type("dog").color("red").build();
        Pet mother = Pet.builder().name("Mother").type("dog").color("Yellow").build();
        return args -> {
            petRepo.save(father);
            petRepo.save(mother);
            petRepo.save(Pet.builder().name("Ken").type("dog").color("red").father(father).mother(mother).build());
            petRepo.save(Pet.builder().name("Larry").type("kitty").color("blue").build());
            petRepo.save(Pet.builder().name("Mix").type("pig").color("brown").build());
            petRepo.save(Pet.builder().name("Ace").type("mouse").color("yellow").build());
        };
    }
}
