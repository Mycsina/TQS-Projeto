package ua.tqs.project.quickserve.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tqs.project.quickserve.dto.FullOrderDTO;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.services.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/${app.api.version}/orders")
@Tag(name = "Order", description = "Operations pertaining to orders")
public class OrderController {

    private OrderService service;

    @Operation(summary = "Get all orders")
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }

    @Operation(summary = "Get order by id")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return new ResponseEntity<>(service.getOrderById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get in-making orders")
    @GetMapping("/inmaking")
    public ResponseEntity<List<Order>> getInMakingOrders() {
        return new ResponseEntity<>(service.getOrdersByStatus(Status.IN_MAKING), HttpStatus.OK);
    }

    @Operation(summary = "Post an order")
    @PostMapping
    public ResponseEntity<Order> postOrder(FullOrderDTO order) {
        service.makeOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}