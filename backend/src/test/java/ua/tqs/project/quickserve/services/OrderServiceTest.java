package ua.tqs.project.quickserve.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Order;
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


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    protected static final Logger logger = LogManager.getLogger(OrderServiceTest.class);

    @Mock( lenient = true)
    private OrderRepository orderRepository;

    @Mock( lenient = true)
    private OrderItemService orderItemService;

    @Mock( lenient = true)
    private ItemIngredientService itemIngredientService;

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

        Mockito.when(orderItemService.getOrderItemsByOrderId(order1.getId())).thenReturn(Arrays.asList());
        Mockito.when(orderItemService.getOrderItemsByOrderId(order2.getId())).thenReturn(Arrays.asList());
        Mockito.when(orderItemService.getOrderItemsByOrderId(order3.getId())).thenReturn(Arrays.asList());
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

}
