package org.vladimirskoe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vladimirskoe.project.converter.OrderConverter;
import org.vladimirskoe.project.dto.OrderDto;
import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    private OrderConverter orderConverter;
    private OrderService orderService;

    @Autowired
    private OrderController(OrderService orderService, OrderConverter orderConverter) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        Order order = orderConverter.fromDtoToOrder(orderDto);
        orderService.addOrder(order);
        return orderConverter.fromOrderToDto(order);
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

    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Integer id, @RequestBody OrderDto orderDto) {
        Order order = orderConverter.fromDtoToOrder(orderDto);
        orderService.updateOrder(id, order);
        return orderConverter.fromOrderToDto(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }
}
