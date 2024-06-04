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
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.PickupMethod;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderItemRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(OrderItemRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderItemRepository orderItemRepository;

    private Address deliveryAddress;
    private Address restaurantAddress;
    private Menu menu;
    private User client;
    private User manager;
    private Restaurant restaurant;
    private Item item;
    private Category category;
    private Order order;

    @BeforeEach
    void setUp() {
        this.restaurantAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.deliveryAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.client = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 12312312, deliveryAddress);
        this.manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123126);
        this.restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123127, State.OPEN, restaurantAddress, manager);
        this.menu = new Menu(restaurant);
        this.category = new Category("Burgers", menu);
        this.item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        this.order = new Order(LocalDateTime.now(), 5.0, restaurant, client, PickupMethod.AT_RESTAURANT);

        restaurant.setTimes("10:00:00", "04:00:00");

        entityManager.persistAndFlush(restaurantAddress);
        entityManager.persistAndFlush(deliveryAddress);
        entityManager.persistAndFlush(client);
        entityManager.persistAndFlush(manager);
        entityManager.persistAndFlush(restaurant);
        entityManager.persistAndFlush(menu);
        entityManager.persistAndFlush(category);
        entityManager.persistAndFlush(item);
        entityManager.persistAndFlush(order);
    }

    @Test
    @DisplayName("Find OrderItem By Id")
    void whenFindOrderItemByIdthenReturnOrderItem() {
        OrderItem orderItem = new OrderItem(5.0, item, order);

        entityManager.persistAndFlush(orderItem); //ensure data is persisted at this point

        long id = orderItem.getId();
        OrderItem found = orderItemRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllOrderItemsthenReturnOrderItemes() {
        OrderItem orderItem1 = new OrderItem(5.0, item, order);
        OrderItem orderItem2 = new OrderItem(6.0, item, order);
        entityManager.persist(orderItem1);
        entityManager.persist(orderItem2);
        entityManager.flush();
        
        List<OrderItem> found = orderItemRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveOrderItem() {
        OrderItem orderItem = new OrderItem(5.0, item, order);
        long id = orderItem.getId();

        orderItemRepository.save(orderItem);
        assertThat(orderItemRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteOrderItem() {
        OrderItem orderItem = new OrderItem(5.0, item, order);
        entityManager.persistAndFlush(orderItem);
        long id = orderItem.getId();

        assertThat(orderItemRepository.findById(id)).isNotNull();
        orderItemRepository.delete(orderItem);
        assertThat(orderItemRepository.findById(id)).isEmpty();
    }

    @Test
    void whenFindByOrderIdthenReturnOrderItems() {
        OrderItem orderItem1 = new OrderItem(5.0, item, order);
        OrderItem orderItem2 = new OrderItem(6.0, item, order);
        OrderItem orderItem3 = new OrderItem(7.0, item, order);
        entityManager.persist(orderItem1);
        entityManager.persist(orderItem2);
        entityManager.persist(orderItem3);
        entityManager.flush();
        
        List<OrderItem> found = orderItemRepository.findByOrderId(order.getId());
        assertThat(found).hasSize(3);
    }
}