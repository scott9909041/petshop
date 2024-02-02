package org.pet.shop.vo;

import lombok.*;

import java.net.ProtocolFamily;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PetDetail {
    private UUID id;
    private String name;
    private String type;
    private String color;
    private UUID fatherId;
    private String fatherName;
    private UUID motherId;
    private String motherName;
    private UUID customerId;
    private String customerName;
}
