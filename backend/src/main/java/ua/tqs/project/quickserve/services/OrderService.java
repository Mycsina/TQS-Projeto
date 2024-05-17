package ua.tqs.project.quickserve.services;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.dto.OrderDTO;
import ua.tqs.project.quickserve.dto.WorkerOrderDTO;
import ua.tqs.project.quickserve.dto.ItemIngredientDTO;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.repositories.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
    
    private OrderRepository repository;

    private OrderItemService orderItemService;

    private ItemIngredientService itemIngredientService;

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

    public OrderDTO getOrderDTOById(long id) {
        Order order = getOrderById(id);
        return new OrderDTO(order);
    }

    public WorkerOrderDTO getWorkerOrderById(long id) {
        Order order = this.getOrderById(id);
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(order.getId());
        Map<String, List<ItemIngredientDTO>> ingredients = new HashMap<>();
        for (OrderItem orderItem : orderItems) {
            List<ItemIngredient> itemIngredients = itemIngredientService.getOrderItemIngredients(orderItem.getId());
            List<ItemIngredientDTO> itemIngredientsDTO = ItemIngredientDTO.convertToDTOList(itemIngredients);
            ingredients.put(orderItem.getItem().getName(), itemIngredientsDTO);
        }

        return new WorkerOrderDTO(order, ingredients);
    }

    public void deleteOrderById(long id) {
        repository.deleteById(id);
    }

    public void makeOrder(WorkerOrderDTO order) {
        return;
    }
}
