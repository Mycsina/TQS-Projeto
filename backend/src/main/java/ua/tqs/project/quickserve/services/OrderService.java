package ua.tqs.project.quickserve.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.repositories.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
    
    private OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public Order getOrderById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteOrderById(long id) {
        repository.deleteById(id);
    }
}
