package org.vladimirskoe.project.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import org.vladimirskoe.project.entity.User.UserRole;
import org.vladimirskoe.project.service.OrderService;
import org.vladimirskoe.project.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private UserService userService;
    private OrderService orderService;
    private OrderConverter orderConverter;

    public UserController(UserService userService,
            OrderService orderService, OrderConverter orderConverter) {
        this.userService = userService;
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }

    private void userChecker(Integer userId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserById(userId);

        boolean isValidClient = (userDetails.getAuthorities()
                .contains
                        (new SimpleGrantedAuthority(UserRole.CLIENT.toString())))
                && (userDetails.getUsername().equals(user.getEmail()));

        boolean isValidOperator = (userDetails.getAuthorities()
                .contains
                        (new SimpleGrantedAuthority(UserRole.OPERATOR.toString())));

        if (!(isValidClient || isValidOperator)) {
            throw new AccessDeniedException("Orders not found");
        }
    }

    @PostMapping("/{userId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@Valid @RequestBody OrderDto orderDto,
            @AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer userId) {
        User user = userService.findUserById(userId);

        userChecker(userId, userDetails);

        Order order = orderConverter.fromDtoToOrder(orderDto);
        order.setUser(user);
        orderService.addOrder(order);
        return orderConverter.fromOrderToDto(order);
    }

    @GetMapping("/{userId}/orders")
    public List<OrderDto> getAllUserOrders(
            @AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer userId) {
        User user = userService.findUserById(userId);
        userChecker(userId, userDetails);
        return orderService.getAllUserOrders(user)
                .stream()
                .map(order -> orderConverter.fromOrderToDto(order))
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public OrderDto getUserOrderById(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer orderId, @PathVariable Integer userId) {

        //User user = userService.findUserById(userId);
        userChecker(userId, userDetails);
        Order order = orderService.getOrderById(orderId);
        return orderConverter.fromOrderToDto(order);
    }

    @PutMapping("/{userId}/orders/{orderId}")
    public OrderDto updateOrder(@PathVariable Integer orderId, @PathVariable Integer userId,
            @Valid @RequestBody OrderDto orderDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        //User user = userService.findUserById(userId);

        userChecker(userId, userDetails);

        Order order = orderConverter.fromDtoToOrder(orderDto);
        orderService.updateOrder(orderId, order);
        return orderConverter.fromOrderToDto(order);
    }

    @DeleteMapping("/{userId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer orderId, @PathVariable Integer userId,
            @AuthenticationPrincipal UserDetails userDetails) {
        userChecker(userId, userDetails);
        orderService.deleteOrder(orderId);
    }
}
