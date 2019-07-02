package org.vladimirskoe.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductDto {

    private Integer id;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Positive
    private Double price;

    @NotNull(message = "Weight cannot be null")
    @Positive
    private Integer weight;

    @NotBlank(message = "Type cannot be null")
    private String type;

    @NotBlank(message = "Producer cannot be null")
    private String producer;

    @NotNull(message = "Hcp cannot be null")
    @Positive
    private Double hcp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getHcp() {
        return hcp;
    }

    public void setHcp(Double hcp) {
        this.hcp = hcp;
    }
}
