package ua.tqs.project.quickserve.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.services.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
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
}