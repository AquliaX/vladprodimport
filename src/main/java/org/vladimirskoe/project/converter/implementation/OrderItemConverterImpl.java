package org.vladimirskoe.project.converter.implementation;

import org.springframework.stereotype.Component;
import org.vladimirskoe.project.converter.OrderItemConverter;
import org.vladimirskoe.project.dto.OrderItemDto;
import org.vladimirskoe.project.entity.OrderItem;
import org.vladimirskoe.project.entity.Package;

@Component
public class OrderItemConverterImpl implements OrderItemConverter {
    @Override
    public OrderItemDto fromOrderItemToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setAmount(orderItem.getAmount());
        orderItemDto.setPackageId(orderItem.getPack().getId());
        return orderItemDto;
    }

    @Override
    public OrderItem fromDtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        Package pack = new Package();
        pack.setId(orderItemDto.getPackageId());
        orderItem.setPack(pack);

        orderItem.setAmount(orderItemDto.getAmount());

        return orderItem;
    }
}
