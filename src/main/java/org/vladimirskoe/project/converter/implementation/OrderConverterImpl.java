package org.vladimirskoe.project.converter.implementation;

import org.springframework.beans.BeanUtils;
import org.vladimirskoe.project.converter.OrderConverter;
import org.vladimirskoe.project.dto.OrderDto;
import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.entity.User;

public class OrderConverterImpl implements OrderConverter {
    @Override
    public OrderDto fromOrderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto, "user");
        orderDto.setUserId(order.getUser().getId());
        return orderDto;
    }

    @Override
    public Order fromDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(order, orderDto, "userId");
        User user = new User();
        user.setId(orderDto.getUserId());
        order.setUser(user);
        return order;
    }
}
