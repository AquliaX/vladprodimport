package org.vladimirskoe.project.converter;

import org.vladimirskoe.project.dto.OrderDto;
import org.vladimirskoe.project.entity.Order;

public interface OrderConverter {

    OrderDto fromOrderToDto(Order order);

    Order fromDtoToOrder(OrderDto orderDto);
}
