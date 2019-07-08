package org.vladimirskoe.project.service;

import java.util.List;
import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.entity.User;

public interface OrderService {

    Order addOrder(Order order);

    Order getOrderById(Integer id);

    List<Order> getAllOrders();

    List<Order> getAllUserOrders(User user);

    Order updateOrder(Integer id, Order order);

    void deleteOrder(Integer id);
}
