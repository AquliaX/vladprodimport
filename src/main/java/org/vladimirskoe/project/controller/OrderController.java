package org.vladimirskoe.project.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vladimirskoe.project.converter.OrderConverter;
import org.vladimirskoe.project.dto.OrderDto;
import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.service.OrderService;
import org.vladimirskoe.project.service.UserService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private OrderConverter orderConverter;
    private OrderService orderService;
    private UserService userService;

    @Autowired
    private OrderController(OrderService orderService, OrderConverter orderConverter,
            UserService userService) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return orderConverter.fromOrderToDto(order);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(order -> orderConverter.fromOrderToDto(order))
                .collect(Collectors.toList());
    }
}
