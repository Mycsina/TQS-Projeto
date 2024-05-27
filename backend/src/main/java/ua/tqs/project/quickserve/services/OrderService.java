package ua.tqs.project.quickserve.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.dto.ItemDTO;
import ua.tqs.project.quickserve.dto.BaseOrderDTO;
import ua.tqs.project.quickserve.dto.FullOrderDTO;
import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.dto.OrderDTO;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.repositories.OrderRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final OrderItemService orderItemService;

    public List<Order> getOrdersByStatus(Status status) {
        return repository.findByStatus(status);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public List<Order> getAllOrdersByUserId(long userId) {
        return repository.findByUserId(userId);
    }

    public Optional<Order> getOrderByIdIfUser(long userId, long orderId) {
        Optional<Order> order = repository.findById(orderId);
        if (order.isEmpty()) {
            return Optional.empty();
        }
        if (order.get().getUser() == null) {
            return Optional.empty();
        }
        if (order.get().getUser().getId() == userId) {
            return order;
        }
        return Optional.empty();
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public Order getOrderById(long id) {
        return repository.findById(id).orElse(null);
    }

    public OrderDTO convertOrderToDTO(Order order) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(order.getId());
        List<ItemDTO> itemDTOs = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            List<ItemIngredient> itemIngredients = orderItemService.getOrderItemIngredients(orderItem.getId());
            itemDTOs.add(new ItemDTO(orderItem, itemIngredients));
        }

        return new OrderDTO(order, itemDTOs);
    }

    public List<OrderDTO> convertOrderListToDTOs(List<Order> orders) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(convertOrderToDTO(order));
        }
        return orderDTOs;
    }

    public void deleteOrderById(long id) {
        repository.deleteById(id);
    }

    public Order makeOrder(FullOrderDTO order) {
        BaseOrderDTO orderEntity = order.getOrder();
        Order newOrder = createOrderFromDTO(orderEntity);
        repository.save(newOrder);
        return newOrder;
    }

    public Order createOrderFromDTO(BaseOrderDTO orderDTO) {
        Order newOrder = new Order();
        newOrder.setScheduledTime(orderDTO.getScheduledTime());
        newOrder.setDeliveryAddress(userService.getUserById(orderDTO.getUserId()).getAddress());
        newOrder.setRestaurant(restaurantService.getRestaurantById(orderDTO.getRestaurantId()));
        newOrder.setUser(userService.getUserById(orderDTO.getUserId()));
        newOrder.setPickupMethod(orderDTO.getPickupMethod());
        return newOrder;
    }

    public BaseOrderDTO getOrderDTO(Order order) {
        BaseOrderDTO orderDTO = new BaseOrderDTO();
        orderDTO.setScheduledTime(order.getScheduledTime());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setRestaurantId(order.getRestaurant().getId());
        orderDTO.setPickupMethod(order.getPickupMethod());
        return orderDTO;
    }
}
