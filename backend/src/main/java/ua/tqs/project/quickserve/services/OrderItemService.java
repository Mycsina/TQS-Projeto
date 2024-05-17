package ua.tqs.project.quickserve.services; 

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.repositories.OrderItemRepository;

@Service
@AllArgsConstructor
public class OrderItemService {
    
    private OrderItemRepository repository;

    public List<OrderItem> getOrderItemsByOrderId(long orderId) {
        return repository.findByOrderId(orderId);
    }

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
