package org.vladimirskoe.project.service.implementation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.vladimirskoe.project.dao.OrderRepository;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.entity.*;
import org.vladimirskoe.project.service.OrderService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void addOrderTest() {
        Order order = new Order();

        User user = new User();
        user.setId(1);

        Product product = new Product();
        product.setId(1);

        Package pack = new Package();
        pack.setId(1);
        pack.setProduct(product);

        OrderItem item = new OrderItem();
        item.setPack(pack);

        order.setUser(user);
        order.addOrderItem(item);

        Order orderExpected = new Order();
        orderExpected.setId(1);
        orderExpected.setUser(user);
        orderExpected.addOrderItem(item);

        given(orderRepository.save(order)).willReturn(orderExpected);

        Order orderActual = orderService.addOrder(order);

        Assert.assertEquals(orderExpected, orderActual);

    }

    @Test
    public void updateOrderTest() {
        Order order = new Order();
        order.setId(1);

        User user = new User();
        user.setId(1);

        Product product = new Product();
        product.setId(1);

        Package pack = new Package();
        pack.setId(1);
        pack.setProduct(product);

        OrderItem item = new OrderItem();
        item.setPack(pack);

        order.setUser(user);
        order.addOrderItem(item);

        Order orderExpected = new Order();
        orderExpected.setId(1);
        orderExpected.setUser(user);
        orderExpected.addOrderItem(item);

        given(orderRepository.findById(1)).willReturn(Optional.of(orderExpected));
        given(orderRepository.save(order)).willReturn(orderExpected);

        Order orderActual = orderService.updateOrder(1, order);

        Assert.assertEquals(orderExpected, orderActual);
    }
}