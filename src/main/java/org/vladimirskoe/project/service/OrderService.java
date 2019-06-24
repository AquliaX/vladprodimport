package org.vladimirskoe.project.service;

import org.vladimirskoe.project.entity.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);

    Order getOrderById(Integer id);

    List<Order> getAllOrders();

    Order updateOrder(Integer id, Order order);

    void deleteOrder(Integer id);
}
