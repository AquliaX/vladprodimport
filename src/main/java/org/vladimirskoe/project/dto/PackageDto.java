package org.vladimirskoe.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PackageDto {

    private Integer id;

    @NotBlank(message = "Product name cannot be null")
    private String name;

    @NotNull(message = "Product cannot be null")
    private ProductDto product;

    @NotNull(message = "Amount cannot be null")
    @Positive
    private Integer amount;

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

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
