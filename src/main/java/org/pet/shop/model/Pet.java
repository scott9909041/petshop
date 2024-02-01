package org.pet.shop.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Parent;

import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name="pet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;

    private String type;

    private String color;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private Pet father;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    private Pet mother;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "pet_customer",
            joinColumns =
                    { @JoinColumn(name = "pet_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "customer_id", referencedColumnName = "id") })
    private Customer customer;

}

