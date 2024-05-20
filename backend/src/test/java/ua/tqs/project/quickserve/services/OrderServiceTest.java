package ua.tqs.project.quickserve.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.project.quickserve.dto.FullOrderDTO;
import ua.tqs.project.quickserve.dto.OrderDTO;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void getOrdersByStatus_ShouldReturnOrdersByStatus() {
        Status status = Status.SCHEDULED;
        List<Order> orders = List.of(new Order(), new Order()); // Mock orders
        when(orderRepository.findByStatus(status)).thenReturn(orders);

        List<Order> result = orderService.getOrdersByStatus(status);

        verify(orderRepository, times(1)).findByStatus(status);
        assertEquals(orders, result);
    }

    @Test
    void getAllOrders_ShouldReturnAllOrders() {
        List<Order> orders = List.of(new Order(), new Order()); // Mock orders
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        verify(orderRepository, times(1)).findAll();
        assertEquals(orders, result);
    }

    @Test
    void getAllOrdersByUserId_ShouldReturnOrdersByUserId() {
        long userId = 1L;
        List<Order> orders = List.of(new Order(), new Order()); // Mock orders
        when(orderRepository.findByUserId(userId)).thenReturn(orders);

        List<Order> result = orderService.getAllOrdersByUserId(userId);

        verify(orderRepository, times(1)).findByUserId(userId);
        assertEquals(orders, result);
    }

    @Test
    void getOrderByIdIfUser_ShouldReturnOrderIfUserMatches() {
        long userId = 1L;
        long orderId = 1L;
        User user = new User(); // Mock user
        user.setId(userId);
        Order order = new Order(); // Mock order
        order.setUser(user);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderByIdIfUser(userId, orderId);

        verify(orderRepository, times(1)).findById(orderId);
        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }

    @Test
    void getOrderByIdIfUser_ShouldReturnEmptyIfUserDoesNotMatch() {
        long userId = 1L;
        long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        Optional<Order> result = orderService.getOrderByIdIfUser(userId, orderId);

        verify(orderRepository, times(1)).findById(orderId);
        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldSaveOrder() {
        Order order = new Order(); // Mock order
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        Order savedOrder = orderService.save(order);

        verify(orderRepository, times(1)).save(order);
        assertEquals(order, savedOrder);
    }

    @Test
    void getOrderById_ShouldReturnOrderById() {
        long id = 1L;
        Order order = new Order(); // Mock order
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(id);

        verify(orderRepository, times(1)).findById(id);
        assertEquals(order, result);
    }

    @Test
    void deleteOrderById_ShouldDeleteOrderById() {
        long id = 1L;
        doNothing().when(orderRepository).deleteById(id);

        orderService.deleteOrderById(id);

        verify(orderRepository, times(1)).deleteById(id);
    }

    @Test
    void makeOrder_ShouldMakeOrder() {
        // Prepare the FullOrderDTO with necessary data
        FullOrderDTO fullOrderDTO = new FullOrderDTO();
        Order expectedOrder = new Order();
        User user = new User();
        user.setId(1L);
        expectedOrder.setUser(user);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        expectedOrder.setRestaurant(restaurant);
        OrderDTO orderDTO = orderService.getOrderDTO(expectedOrder);
        fullOrderDTO.setOrderDTO(orderDTO);


        when(orderRepository.save(any(Order.class))).thenReturn(expectedOrder);
        when(userService.getUserById(anyLong())).thenReturn(user);
        when(restaurantService.getRestaurantById(anyLong())).thenReturn(restaurant);

        Order result = orderService.makeOrder(fullOrderDTO);

        verify(orderRepository, times(1)).save(any(Order.class));

        assertEquals(expectedOrder.getId(), result.getId());
    }

}
