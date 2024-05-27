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
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.PickupMethod;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.repositories.ItemIngredientRepository;

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
class ItemIngredientServiceTest {
    protected static final Logger logger = LogManager.getLogger(ItemIngredientServiceTest.class);

    @Mock
    private ItemIngredientRepository itemIngredientRepository;

    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private ItemIngredientService itemIngredientService;

    @BeforeEach
    public void setUp() {
        Address address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Address deliveryAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        User client = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, deliveryAddress); 
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, menu, manager);
        Category category = new Category("Burgers", menu);
        Item item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category); item.setId(1L);
        Ingredient ingredient1 = new Ingredient("Burger", 1.0, true, restaurant);
        Ingredient ingredient2 = new Ingredient("Lettuce", 0.5, true, restaurant);
        
        Order order = new Order(LocalDateTime.now(), 5.0, deliveryAddress, restaurant, client, PickupMethod.DELIVERY); order.setId(1L);

        OrderItem orderItem = new OrderItem(5.0, item, order); orderItem.setId(1L);

        restaurant.setTimes("10:00:00", "04:00:00");

        ItemIngredient itemIngredient1 = new ItemIngredient(2, true, item, ingredient1); itemIngredient1.setId(1L);
        ItemIngredient itemIngredient2 = new ItemIngredient(1, true, item, ingredient2); itemIngredient2.setId(2L);
        ItemIngredient itemIngredient3 = new ItemIngredient(1, true, orderItem, ingredient2); itemIngredient3.setId(3L);

        List<ItemIngredient> allItemIngredients = Arrays.asList(itemIngredient1, itemIngredient2, itemIngredient3);

        Mockito.when(itemIngredientRepository.findById(itemIngredient1.getId())).thenReturn(Optional.of(itemIngredient1));
        Mockito.when(itemIngredientRepository.findById(itemIngredient2.getId())).thenReturn(Optional.of(itemIngredient2));
        Mockito.when(itemIngredientRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(itemIngredientRepository.findAll()).thenReturn(allItemIngredients);
        Mockito.when(itemIngredientRepository.findByItemId(item.getId())).thenReturn(Arrays.asList(itemIngredient1, itemIngredient2));
        Mockito.when(itemIngredientRepository.findByOrderItemId(orderItem.getId())).thenReturn(Arrays.asList(itemIngredient3));

        Mockito.when(itemIngredientRepository.save(itemIngredient1)).thenReturn(itemIngredient1);

        Mockito.when(orderItemService.getOrderItemById(orderItem.getId())).thenReturn(orderItem);
    }
    
    @Test
    void whenValidIdthenItemIngredientShouldBeFound() {
        ItemIngredient found = itemIngredientService.getItemIngredientById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenItemIngredientShouldNotBeFound() {
        ItemIngredient found = itemIngredientService.getItemIngredientById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenSaveItemIngredientthenItemIngredientShouldBeReturned() {
        Address address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        Menu menu = new Menu();
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, menu, manager);
        Category category = new Category("Burgers", menu);
        Item item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        Ingredient ingredient1 = new Ingredient("Burger", 1.0, true, restaurant);

        ItemIngredient itemIngredient = new ItemIngredient(2, true, item, ingredient1); itemIngredient.setId(1L);
        Mockito.when(itemIngredientRepository.save(itemIngredient)).thenReturn(itemIngredient);
        
        ItemIngredient savedItemIngredient = itemIngredientService.save(itemIngredient);
        assertThat(savedItemIngredient).isNotNull();
    }

    @Test
    void whenDeleteItemIngredientthenItemIngredientShouldBeDeleted() {
        Long itemIngredientId = 1L;

        // Setup mock behavior
        when(itemIngredientRepository.findById(itemIngredientId)).thenReturn(Optional.of(new ItemIngredient()));
            
        itemIngredientService.deleteItemIngredientById(itemIngredientId);
        Mockito.verify(itemIngredientRepository, times(1)).deleteById(itemIngredientId);
    }

}
