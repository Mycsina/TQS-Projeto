package ua.tqs.project.quickserve.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.dto.FullOrderDTO;
import ua.tqs.project.quickserve.dto.OrderDTO;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository repository, RestaurantService restaurantService, UserService userService) {
        this.repository = repository;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

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

    public void deleteOrderById(long id) {
        repository.deleteById(id);
    }

    public Order makeOrder(FullOrderDTO order) {
        OrderDTO orderEntity = order.getOrderDTO();
        Order newOrder = createOrderFromDTO(orderEntity);
        repository.save(newOrder);
        return newOrder;
    }

    public Order createOrderFromDTO(OrderDTO orderDTO) {
        Order newOrder = new Order();
        newOrder.setScheduledTime(orderDTO.getScheduledTime());
        newOrder.setDeliveryAddress(userService.getUserById(orderDTO.getUserId()).getAddress());
        newOrder.setRestaurant(restaurantService.getRestaurantById(orderDTO.getRestaurantId()));
        newOrder.setUser(userService.getUserById(orderDTO.getUserId()));
        newOrder.setPickupMethod(orderDTO.getPickupMethod());
        return newOrder;
    }

    public OrderDTO getOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setScheduledTime(order.getScheduledTime());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setRestaurantId(order.getRestaurant().getId());
        orderDTO.setPickupMethod(order.getPickupMethod());
        return orderDTO;
    }
}
