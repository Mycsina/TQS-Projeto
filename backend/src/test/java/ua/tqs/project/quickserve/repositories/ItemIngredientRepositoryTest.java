package ua.tqs.project.quickserve.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.PickupMethod;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ItemIngredientRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(ItemIngredientRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemIngredientRepository itemIngredientRepository;

    private Address address;
    private Address deliveryAddress;
    private Menu menu;
    private User manager;
    private User client;
    private Restaurant restaurant;
    private Item item;
    private Category category;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Order order;
    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        this.address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.deliveryAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.menu = new Menu();
        this.manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123125);
        this.client = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123126, deliveryAddress);
        this.restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123127, State.OPEN, address, menu, manager);
        this.category = new Category("Burgers", menu);
        this.item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        this.ingredient1 = new Ingredient("Burger", 1.0, true, restaurant);
        this.ingredient2 = new Ingredient("Lettuce", 0.5, true, restaurant);
        this.order = new Order(LocalDateTime.now(), 5.0, restaurant, client, PickupMethod.AT_RESTAURANT);
        this.orderItem = new OrderItem(5.0, item, order);

        restaurant.setTimes("10:00:00", "04:00:00");

        entityManager.persistAndFlush(address);
        entityManager.persistAndFlush(deliveryAddress);
        entityManager.persistAndFlush(menu);
        entityManager.persistAndFlush(manager);
        entityManager.persistAndFlush(client);
        entityManager.persistAndFlush(restaurant);
        entityManager.persistAndFlush(category);
        entityManager.persistAndFlush(item);
        entityManager.persistAndFlush(ingredient1);
        entityManager.persistAndFlush(ingredient2);
        entityManager.persistAndFlush(order);
        entityManager.persistAndFlush(orderItem);
    }

    @Test
    @DisplayName("Find ItemIngredient By Id")
    void whenFindItemIngredientByIdthenReturnItemIngredient() {
        ItemIngredient itemIngredient = new ItemIngredient(2, true, item, ingredient1);

        entityManager.persistAndFlush(itemIngredient); //ensure data is persisted at this point

        long id = itemIngredient.getId();
        ItemIngredient found = itemIngredientRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllItemIngredientsthenReturnItemIngredientes() {
        ItemIngredient itemIngredient1 = new ItemIngredient(2, true, item, ingredient1);
        ItemIngredient itemIngredient2 = new ItemIngredient(1, true, item, ingredient2);
        entityManager.persist(itemIngredient1);
        entityManager.persist(itemIngredient2);
        entityManager.flush();
        
        List<ItemIngredient> found = itemIngredientRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveItemIngredient() {
        ItemIngredient itemIngredient = new ItemIngredient(2, true, item, ingredient1);
        long id = itemIngredient.getId();

        itemIngredientRepository.save(itemIngredient);
        assertThat(itemIngredientRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteItemIngredient() {
        ItemIngredient itemIngredient = new ItemIngredient(2, true, item, ingredient1);
        entityManager.persistAndFlush(itemIngredient);
        long id = itemIngredient.getId();

        assertThat(itemIngredientRepository.findById(id)).isNotNull();
        itemIngredientRepository.delete(itemIngredient);
        assertThat(itemIngredientRepository.findById(id)).isEmpty();
    }

    @Test
    void findByItemId() {
        ItemIngredient itemIngredient1 = new ItemIngredient(2, true, item, ingredient1);
        ItemIngredient itemIngredient2 = new ItemIngredient(1, true, item, ingredient2);
        entityManager.persist(itemIngredient1);
        entityManager.persist(itemIngredient2);
        entityManager.flush();
        
        List<ItemIngredient> found = itemIngredientRepository.findByItemId(item.getId());
        assertThat(found).hasSize(2);
    }

    @Test
    void findByOrderItemId() {
        ItemIngredient itemIngredient1 = new ItemIngredient(2, true, orderItem, ingredient1);
        ItemIngredient itemIngredient2 = new ItemIngredient(1, true, orderItem, ingredient2);
        entityManager.persist(itemIngredient1);
        entityManager.persist(itemIngredient2);
        entityManager.flush();
        
        List<ItemIngredient> found = itemIngredientRepository.findByOrderItemId(orderItem.getId());
        assertThat(found).hasSize(2);
    }
}