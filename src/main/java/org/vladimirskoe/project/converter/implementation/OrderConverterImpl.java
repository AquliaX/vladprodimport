package org.vladimirskoe.project.converter.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.vladimirskoe.project.converter.OrderConverter;
import org.vladimirskoe.project.converter.OrderItemConverter;
import org.vladimirskoe.project.dto.OrderDto;
import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.entity.User;


import java.util.stream.Collectors;

public class OrderConverterImpl implements OrderConverter {

    private OrderItemConverter orderItemConverter;

    @Autowired
    public OrderConverterImpl(OrderItemConverter orderItemConverter) {
        this.orderItemConverter = orderItemConverter;
    }

    @Override
    public OrderDto fromOrderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setState(order.getState());
        orderDto.setComment(order.getComment());
        orderDto.setDateTime(order.getDateTime());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setOrderItems(order.getOrderItems().stream()
                .map(orderItemConverter::fromOrderItemToDto)
                .collect(Collectors.toSet()));

        return orderDto;
    }

    @Override
    public Order fromDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        User user = new User();
        user.setId(orderDto.getUserId());
        order.setUser(user);
        order.setId(orderDto.getId());
        order.setComment(orderDto.getComment());
        order.setDateTime(orderDto.getDateTime());
        order.setState(orderDto.getState());
        order.setOrderItems(orderDto.getOrderItems().stream()
                .map(orderItemConverter::fromDtoToOrderItem)
                .collect(Collectors.toSet()));
        return order;
    }
}
