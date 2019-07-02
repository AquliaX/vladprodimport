package org.vladimirskoe.project.dto;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OrderDto {

    private Integer id;

    @NotNull(message = "UserId cannot be null")
    private Integer userId;

    private LocalDateTime dateTime;

    private String comment;

    private String state;

    @NotNull(message = "OrderItems cannot be null")
    @Size(min = 1)
    private Set<OrderItemDto> orderItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderDto orderDto = (OrderDto) o;

        return new EqualsBuilder()
                .append(id, orderDto.id)
                .append(userId, orderDto.userId)
                .append(dateTime, orderDto.dateTime)
                .append(comment, orderDto.comment)
                .append(state, orderDto.state)
                .append(orderItems, orderDto.orderItems)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(userId)
                .append(dateTime)
                .append(comment)
                .append(state)
                .append(orderItems)
                .toHashCode();
    }
}