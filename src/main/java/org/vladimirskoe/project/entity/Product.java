package org.vladimirskoe.project.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Double price;

    private Integer weight;

    private String type;

    private String producer;

    private Double hcp;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Package> packageSet;

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

    public Set<Package> getPackageSet() {
        return packageSet;
    }

    public void setPackageSet(Set<Package> packageSet) {
        this.packageSet = packageSet;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(price)
                .append(weight)
                .append(type)
                .append(producer)
                .append(hcp)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("price", price)
                .append("weight", weight)
                .append("type", type)
                .append("producer", producer)
                .append("hcp", hcp)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Product product = (Product) obj;

        return new EqualsBuilder()
                .append(id, product.id)
                .append(name, product.name)
                .append(price, product.price)
                .append(weight, product.weight)
                .append(type, product.type)
                .append(producer, product.producer)
                .append(hcp, product.hcp)
                .isEquals();
    }
}
