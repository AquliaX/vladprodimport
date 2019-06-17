package org.vladimirskoe.project.converter.implementation;

import org.junit.Assert;

import org.junit.Test;

import org.vladimirskoe.project.converter.OrderConverter;
import org.vladimirskoe.project.converter.OrderItemConverter;

import org.vladimirskoe.project.dto.OrderDto;
import org.vladimirskoe.project.dto.OrderItemDto;
import org.vladimirskoe.project.entity.Order;

import org.vladimirskoe.project.entity.OrderItem;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.entity.User;


public class OrderConverterImplTest {

    private OrderConverter orderConverter = new OrderConverterImpl();

    @Test
    public void fromOrderToDtoTest() {
        Order order = new Order();
        order.setId(1);
        User user = new User();
        user.setId(0);
        order.setUser(user);

        OrderDto dto = new OrderDto();
        dto.setId(1);
        dto.setUserId(0);

        OrderDto actual = orderConverter.fromOrderToDto(order);

        Assert.assertEquals(actual.getUserId(), dto.getUserId());
        Assert.assertEquals(actual.getId(), dto.getId());
    }

    private OrderItemConverter orderItemConverter = new OrderItemConverterImpl();

    @Test
    public void fromDtoToOrderItem() {

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setPackageId(1);

        Package pack = new Package();
        pack.setId(1);

        OrderItem orderItem = new OrderItem();
        orderItem.setPack(pack);

        OrderItem actual = orderItemConverter.fromDtoToOrderItem(orderItemDto);

        Assert.assertEquals(actual.getPack().getId(), orderItemDto.getPackageId());
    }
}