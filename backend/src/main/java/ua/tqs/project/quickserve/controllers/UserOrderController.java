package ua.tqs.project.quickserve.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tqs.project.quickserve.dto.FullOrderDTO;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.services.OrderService;
import ua.tqs.project.quickserve.services.UserService;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/${app.api.version}/user/orders")
@Tag(name = "User Order", description = "Operations on Orders made by users")
public class UserOrderController {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public UserOrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Operation(summary = "Get all orders made by the user")
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable long userId) {
        return new ResponseEntity<>(orderService.getAllOrdersByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Get order by id")
    @GetMapping("/{userId}/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long userId, @PathVariable long id) {
        Optional<Order> order = orderService.getOrderByIdIfUser(userId, id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Place an order")
    @PostMapping("/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable long userId, FullOrderDTO order) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.makeOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
