package ua.tqs.project.quickserve.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.dto.FullOrderDTO;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.repositories.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository repository;

    public List<Order> getOrdersByStatus(Status status) {
        return repository.findByStatus(status);
    }

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

    public void makeOrder(FullOrderDTO order) {
        return;
    }
}
