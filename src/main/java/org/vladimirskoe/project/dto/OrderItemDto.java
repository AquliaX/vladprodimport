package org.vladimirskoe.project.dto;

import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.entity.Package;

public class OrderItemDto {

    private Integer id;
    private Order order;
    private Package pack;
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
