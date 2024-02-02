package org.pet.shop.vo;

import jakarta.persistence.OneToMany;
import lombok.*;
import org.pet.shop.model.Pet;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerCreatePayload {

    private UUID id;

    private String name;

    private String email;

    private Set<Pet> pets;
}
