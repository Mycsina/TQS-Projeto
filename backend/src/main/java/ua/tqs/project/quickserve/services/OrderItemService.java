package ua.tqs.project.quickserve.services; 

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.repositories.OrderItemRepository;

@Service
@AllArgsConstructor
public class OrderItemService {
    
    private OrderItemRepository repository;

    public OrderItem save(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    public OrderItem getOrderItemById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteOrderItemById(long id) {
        repository.deleteById(id);
    }
}
