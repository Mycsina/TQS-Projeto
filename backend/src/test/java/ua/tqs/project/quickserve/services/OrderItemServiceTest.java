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

import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.PickupMethod;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.repositories.OrderItemRepository;

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
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderItemServiceTest {
    protected static final Logger logger = LogManager.getLogger(OrderItemServiceTest.class);

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ItemIngredientService itemIngredientService;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeEach
    public void setUp() {
        Address restaurantAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Address deliveryAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User client = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, deliveryAddress);
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress, manager);
        Category category = new Category("Burgers", menu);
        Item item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        Order order = new Order(LocalDateTime.now(), 5.0, restaurant, client, PickupMethod.AT_RESTAURANT); order.setId(1L);
        restaurant.setTimes("10:00:00", "04:00:00");

        Ingredient ingredient1 = new Ingredient("Burger", 1.0, true, restaurant);
        Ingredient ingredient2 = new Ingredient("Lettuce", 0.5, true, restaurant);

        ItemIngredient itemIngredient1 = new ItemIngredient(2, true, item, ingredient1); itemIngredient1.setId(1L);
        ItemIngredient itemIngredient2 = new ItemIngredient(1, true, item, ingredient2); itemIngredient2.setId(2L);

        OrderItem orderItem1 = new OrderItem(5.0, item, order); orderItem1.setId(1L);
        OrderItem orderItem2 = new OrderItem(6.0, item, order); orderItem2.setId(2L);
        OrderItem orderItem3 = new OrderItem(7.0, item, order); orderItem3.setId(3L);

        List<OrderItem> allOrderItems = Arrays.asList(orderItem1, orderItem2, orderItem3);

        Mockito.when(orderItemRepository.findById(orderItem1.getId())).thenReturn(Optional.of(orderItem1));
        Mockito.when(orderItemRepository.findById(orderItem2.getId())).thenReturn(Optional.of(orderItem2));
        Mockito.when(orderItemRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(orderItemRepository.findAll()).thenReturn(allOrderItems);

        Mockito.when(orderItemRepository.save(orderItem1)).thenReturn(orderItem1);
        
        Mockito.when(orderItemRepository.findByOrderId(1L)).thenReturn(allOrderItems);

        Mockito.when(itemIngredientService.getByItemId(item.getId())).thenReturn(Arrays.asList(itemIngredient1, itemIngredient2));
        Mockito.when(itemIngredientService.getByOrderItemId(orderItem1.getId())).thenReturn(Arrays.asList(itemIngredient1, itemIngredient2));
    }
    
    @Test
    void whenValidIdthenOrderItemShouldBeFound() {
        OrderItem found = orderItemService.getOrderItemById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenOrderItemShouldNotBeFound() {
        OrderItem found = orderItemService.getOrderItemById(-99L);
        assertThat(found).isNull();;
    }
    
    @Test
    void whenGetOrderItemsByOrderIdthenReturnOrderItems() {        
        List<OrderItem> found = orderItemService.getOrderItemsByOrderId(1L);
        assertThat(found).hasSize(3);
    }

    @Test
    void whenSaveOrderItemthenOrderItemShouldBeReturned() {
        Address restaurantAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Address deliveryAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User client = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, deliveryAddress);
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress, manager);
        Category category = new Category("Burgers", menu);
        Item item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        Order order = new Order(LocalDateTime.now(), 5.0, restaurant, client, PickupMethod.AT_RESTAURANT);
        restaurant.setTimes("10:00:00", "04:00:00");

        OrderItem orderItem = new OrderItem(5.0, item, order); orderItem.setId(1L);
        Mockito.when(orderItemRepository.save(orderItem)).thenReturn(orderItem);
        
        OrderItem savedOrderItem = orderItemService.save(orderItem);
        assertThat(savedOrderItem).isNotNull();
    }

    @Test
    void whenDeleteOrderItemthenOrderItemShouldBeDeleted() {
        Long orderItemId = 1L;

        // Setup mock behavior
        when(orderItemRepository.findById(orderItemId)).thenReturn(Optional.of(new OrderItem()));
            
        orderItemService.deleteOrderItemById(orderItemId);
        Mockito.verify(orderItemRepository, times(1)).deleteById(orderItemId);
    }

    @Test   
    void whenGetOrderItemIngredientsByOrderItemthenItemIngredientsShouldBeReturned() {
        List<ItemIngredient> found = orderItemService.getOrderItemIngredients(1L);
        assertThat(found).hasSize(2);
    }

}
