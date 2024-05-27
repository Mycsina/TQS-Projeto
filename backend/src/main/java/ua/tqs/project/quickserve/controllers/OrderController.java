package ua.tqs.project.quickserve.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.tqs.project.quickserve.dto.OrderDTO;
import ua.tqs.project.quickserve.dto.FullOrderDTO;
import org.springframework.web.bind.annotation.*;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.services.OrderService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/${app.api.version}/orders")
@Tag(name = "Order", description = "Operations pertaining to orders")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @Operation(summary = "Get all orders")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return new ResponseEntity<>(service.convertOrderListToDTOs(service.getAllOrders()), HttpStatus.OK);
    }

    @Operation(summary = "Get order by id")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable long id) {
        return new ResponseEntity<>(service.convertOrderToDTO(service.getOrderById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get in-making orders")
    @GetMapping("/inmaking")
    public ResponseEntity<List<OrderDTO>> getInMakingOrders() {
        return new ResponseEntity<>(service.convertOrderListToDTOs(service.getOrdersByStatus(Status.IN_MAKING)), HttpStatus.OK);
    }

    @Operation(summary = "Post an order")
    @PostMapping
    public ResponseEntity<Order> postOrder(FullOrderDTO order) {
        service.makeOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}