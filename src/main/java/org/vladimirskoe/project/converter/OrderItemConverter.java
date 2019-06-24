package org.vladimirskoe.project.converter;

import org.vladimirskoe.project.dto.OrderItemDto;
import org.vladimirskoe.project.entity.OrderItem;

public interface OrderItemConverter {

    OrderItemDto fromOrderItemToDto(OrderItem orderItem);

    OrderItem fromDtoToOrderItem(OrderItemDto orderItemDto);
}