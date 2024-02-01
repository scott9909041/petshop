package org.pet.shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;

    @OneToMany(mappedBy="customer")
    private Set<Pet> pets;
}
