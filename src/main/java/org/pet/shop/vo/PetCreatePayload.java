package org.pet.shop.vo;

import lombok.*;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class PetCreatePayload {
    private String name;
    private String type;
    private String color;
    private UUID father;
    private UUID mother;
    private UUID customer;

}
