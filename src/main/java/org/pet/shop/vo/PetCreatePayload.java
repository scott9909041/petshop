package org.pet.shop.vo;

import java.util.UUID;

public class PetCreatePayload {

    private String name;

    private String type;

    private String color;

    private UUID father;
    private UUID mother;

    private UUID customer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UUID getFather() {
        return father;
    }

    public void setFather(UUID father) {
        this.father = father;
    }

    public UUID getMother() {
        return mother;
    }

    public void setMother(UUID mother) {
        this.mother = mother;
    }

    public UUID getCustomer() {
        return customer;
    }

    public void setCustomer(UUID customer) {
        this.customer = customer;
    }
}
