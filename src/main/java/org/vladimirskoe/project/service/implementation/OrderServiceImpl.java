package org.vladimirskoe.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vladimirskoe.project.dao.OrderRepository;
import org.vladimirskoe.project.dao.UserRepository;
import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.entity.User;
import org.vladimirskoe.project.exception.NullObjectException;
import org.vladimirskoe.project.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    public OrderServiceImpl() {
    }

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(final Order order) {
        //add tmp User for order due to lack of implementation
        User user = new User();
        user.setId(1);
        order.setUser(user);
        order.setDateTime(LocalDateTime.now());
        order.setOrderItems(order.getOrderItems()
                .stream()
                .peek(orderItem -> orderItem.setOrder(order))
                .collect(Collectors.toSet()));
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NullObjectException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Integer id, Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new NullObjectException("Order does not exist and cannot be updated");
        }
        order.setUser(optionalOrder.get().getUser());
        order.setDateTime(optionalOrder.get().getDateTime());
        order.setOrderItems(order.getOrderItems()
                .stream()
                .peek(orderItem -> orderItem.setOrder(order))
                .collect(Collectors.toSet()));
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new NullObjectException("Order does not exist and cannot be deleted");
        }
        orderRepository.deleteById(id);
    }
}
