package org.vladimirskoe.project.converter.implementation;

import org.springframework.beans.BeanUtils;
import org.vladimirskoe.project.converter.OrderItemConverter;
import org.vladimirskoe.project.dto.OrderItemDto;
import org.vladimirskoe.project.entity.OrderItem;
import org.vladimirskoe.project.entity.Package;

public class OrderItemConverterImpl implements OrderItemConverter {
    @Override
    public OrderItemDto fromOrderItemToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(orderItem, orderItemDto, "pack");
        orderItemDto.setPackageId(orderItem.getPack().getId());
        return orderItemDto;
    }

    @Override
    public OrderItem fromDtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(orderItemDto, orderItem, "packageId");
        Package pack = new Package();
        pack.setId(orderItemDto.getPackageId());
        orderItem.setPack(pack);
        return orderItem;
    }
}
