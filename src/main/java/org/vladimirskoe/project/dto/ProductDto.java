package org.vladimirskoe.project.dto;

public class ProductDto {

    private Integer id;
    private String name;
    private Double price;
    private Integer weight;
    private String type;
    private String producer;
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
