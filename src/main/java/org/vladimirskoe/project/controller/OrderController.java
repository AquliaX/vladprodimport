package org.vladimirskoe.project.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.vladimirskoe.project.converter.OrderConverter;
import org.vladimirskoe.project.dto.OrderDto;
import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.entity.User;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@Valid @RequestBody OrderDto orderDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByEmail(userDetails.getUsername());
        Order order = orderConverter.fromDtoToOrder(orderDto);
        order.setUser(user);
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
    public OrderDto updateOrder(@PathVariable Integer id,@Valid @RequestBody OrderDto orderDto) {
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
