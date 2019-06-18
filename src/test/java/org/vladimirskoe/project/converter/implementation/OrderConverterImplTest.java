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

import java.util.HashSet;
import java.util.Set;


public class OrderConverterImplTest {
    private OrderItemConverter itemConverter = new OrderItemConverterImpl();
    private OrderConverter orderConverter = new OrderConverterImpl(itemConverter);

    @Test
    public void fromOrderToDtoTest() {
        User user = new User();
        user.setId(1);

        Package pack = new Package();
        pack.setId(1);

        OrderItem item = new OrderItem();
        item.setPack(pack);
        item.setId(1);

        Set<OrderItem> set = new HashSet<>();
        set.add(item);

        Order order = new Order();
        order.setId(1);
        order.setUser(user);
        order.setOrderItems(set);

        OrderDto actual = orderConverter.fromOrderToDto(order);

        OrderDto dtoExpected = new OrderDto();
        dtoExpected.setId(1);
        dtoExpected.setUserId(user.getId());

        OrderItemDto itemExpected = new OrderItemDto();
        itemExpected.setPackageId(pack.getId());

        Set<OrderItemDto> setExpected = new HashSet<>();
        setExpected.add(itemExpected);

        dtoExpected.setOrderItems(setExpected);

        Assert.assertEquals(actual, dtoExpected);
    }

    @Test
    public void fromDtoToOrderTest() {
        User user = new User();
        user.setId(1);

        Package pack = new Package();
        pack.setId(1);

        OrderItemDto item = new OrderItemDto();
        item.setPackageId(pack.getId());

        Set<OrderItemDto> set = new HashSet<>();
        set.add(item);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1);
        orderDto.setUserId(user.getId());
        orderDto.setOrderItems(set);

        Order actual = orderConverter.fromDtoToOrder(orderDto);

        OrderItem itemExpected = new OrderItem();
        itemExpected.setPack(pack);

        Set<OrderItem> setExpected = new HashSet<>();
        setExpected.add(itemExpected);

        Order orderExpected = new Order();
        orderExpected.setId(1);
        orderExpected.setUser(user);
        orderExpected.setOrderItems(setExpected);

        Assert.assertEquals(actual, orderExpected);

    }
}