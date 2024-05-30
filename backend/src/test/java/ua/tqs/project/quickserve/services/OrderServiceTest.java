package ua.tqs.project.quickserve.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import ua.tqs.project.quickserve.dto.BaseOrderDTO;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.dto.FullOrderDTO;
import ua.tqs.project.quickserve.dto.OrderDTO;
import ua.tqs.project.quickserve.entities.PickupMethod;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.repositories.OrderRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceTest {
    protected static final Logger logger = LogManager.getLogger(OrderServiceTest.class);

    @Mock
    private UserService userService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemService orderItemService;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        Address restaurantAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Address deliveryAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User client = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, deliveryAddress);
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress, menu, manager);
        restaurant.setTimes("10:00:00", "04:00:00");

        Order order1 = new Order(LocalDateTime.now(), 5.0, deliveryAddress, restaurant, client, PickupMethod.DELIVERY); order1.setId(1L);
        Order order2 = new Order(LocalDateTime.now(), 6.0, deliveryAddress, restaurant, client, PickupMethod.TO_GO); order2.setId(2L);
        Order order3 = new Order(LocalDateTime.now(), 4.0, deliveryAddress, restaurant, client, PickupMethod.AT_RESTAURANT);order3.setId(3L);

        List<Order> allOrders = Arrays.asList(order1, order2, order3);

        Mockito.when(orderRepository.findById(order1.getId())).thenReturn(Optional.of(order1));
        Mockito.when(orderRepository.findById(order2.getId())).thenReturn(Optional.of(order2));
        Mockito.when(orderRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(orderRepository.findAll()).thenReturn(allOrders);

        Mockito.when(orderRepository.save(order1)).thenReturn(order1);

        Mockito.when(orderRepository.findByStatus(Status.SCHEDULED)).thenReturn(Arrays.asList(order1, order2, order3));

        Mockito.when(orderItemService.getOrderItemsByOrderId(order1.getId())).thenReturn(List.of());
        Mockito.when(orderItemService.getOrderItemsByOrderId(order2.getId())).thenReturn(List.of());
        Mockito.when(orderItemService.getOrderItemsByOrderId(order3.getId())).thenReturn(List.of());
    }
    
    @Test
    void whenValidIdthenOrderShouldBeFound() {
        Order found = orderService.getOrderById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenOrderShouldNotBeFound() {
        Order found = orderService.getOrderById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenGetOrdersByStatusthenReturnOrders() {        
        List<Order> found = orderService.getOrdersByStatus(Status.SCHEDULED);
        assertThat(found).hasSize(3);
    }

    @Test
    void whenConvertOrderToDTOthenReturnOrderDTO() {
        Order order = orderRepository.findById(1L).get();
        OrderDTO orderDTO = orderService.convertOrderToDTO(order);
        assertThat(orderDTO.getOrder().getOrderId()).isEqualTo(order.getId());
    }

    @Test
    void whenConvertOrderListToDTOsthenReturnOrderDTOList() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = orderService.convertOrderListToDTOs(orders);
        assertThat(orderDTOs).hasSize(3);
    }

    @Test
    void whenSaveOrderthenOrderShouldBeReturned() {
        Address restaurantAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Address deliveryAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User client = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, deliveryAddress);
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress, menu, manager);
        restaurant.setTimes("10:00:00", "04:00:00");

        Order order = new Order(LocalDateTime.now(), 5.0, deliveryAddress, restaurant, client, PickupMethod.DELIVERY); order.setId(1L);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        
        Order savedOrder = orderService.save(order);
        assertThat(savedOrder).isNotNull();
    }

    @Test
    void whenDeleteOrderthenOrderShouldBeDeleted() {
        Long orderId = 1L;

        // Setup mock behavior
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(new Order()));
            
        orderService.deleteOrderById(orderId);
        Mockito.verify(orderRepository, times(1)).deleteById(orderId);
    }

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
        BaseOrderDTO orderDTO = orderService.getOrderDTO(expectedOrder);
        fullOrderDTO.setOrder(orderDTO);


        when(orderRepository.save(any(Order.class))).thenReturn(expectedOrder);
        when(userService.getUserById(anyLong())).thenReturn(user);
        when(restaurantService.getRestaurantById(anyLong())).thenReturn(restaurant);

        Order result = orderService.makeOrder(fullOrderDTO);

        verify(orderRepository, times(1)).save(any(Order.class));

        assertEquals(expectedOrder.getId(), result.getId());
    }

}
