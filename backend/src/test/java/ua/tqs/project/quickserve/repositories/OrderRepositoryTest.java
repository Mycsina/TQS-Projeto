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
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.PickupMethod;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.Status;
import ua.tqs.project.quickserve.entities.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(OrderRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    private Address deliveryAddress;
    private Address restaurantAddress;
    private Menu menu;
    private User client;
    private User manager;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        this.restaurantAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.deliveryAddress = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.menu = new Menu();
        this.client = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, deliveryAddress);
        this.manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        this.restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress, menu, manager);
        restaurant.setTimes("10:00:00", "04:00:00");

        entityManager.persistAndFlush(restaurantAddress);
        entityManager.persistAndFlush(deliveryAddress);
        entityManager.persistAndFlush(menu);
        entityManager.persistAndFlush(client);
        entityManager.persistAndFlush(manager);
        entityManager.persistAndFlush(restaurant);
    }

    @Test
    @DisplayName("Find Order By Id")
    void whenFindOrderByIdthenReturnOrder() {
        Order order = new Order(LocalDateTime.now(), 5.0, deliveryAddress, restaurant, client, PickupMethod.DELIVERY);

        entityManager.persistAndFlush(order); //ensure data is persisted at this point

        long id = order.getId();
        Order found = orderRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
        assertThat(found.getTotalPrice()).isEqualTo(order.getTotalPrice());
        assertThat(found.getStatus()).isEqualTo(order.getStatus());
    }

    @Test
    void whenFindAllOrdersthenReturnOrderes() {
        Order order1 = new Order(LocalDateTime.now(), 5.0, deliveryAddress, restaurant, client, PickupMethod.DELIVERY);
        Order order2 = new Order(LocalDateTime.now(), 5.0, restaurant, client, PickupMethod.AT_RESTAURANT);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.flush();
        
        List<Order> found = orderRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveOrder() {
        Order order = new Order(LocalDateTime.now(), 5.0, deliveryAddress, restaurant, client, PickupMethod.DELIVERY);
        long id = order.getId();

        orderRepository.save(order);
        assertThat(orderRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteOrder() {
        Order order = new Order(LocalDateTime.now(), 5.0, deliveryAddress, restaurant, client, PickupMethod.DELIVERY, Status.IN_MAKING);
        entityManager.persistAndFlush(order);
        long id = order.getId();

        assertThat(orderRepository.findById(id)).isNotNull();
        orderRepository.delete(order);
        assertThat(orderRepository.findById(id)).isEmpty();
    }

    @Test
    void whenFindByStatusthenReturnOrders() {
        Order order1 = new Order(LocalDateTime.now(), 5.0, deliveryAddress, restaurant, client, PickupMethod.DELIVERY, Status.IN_MAKING);
        Order order2 = new Order(LocalDateTime.now(), 5.0, restaurant, client, PickupMethod.AT_RESTAURANT, Status.DELIVERED);
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.flush();
        
        List<Order> found = orderRepository.findByStatus(Status.IN_MAKING);
        assertThat(found).hasSize(1);
    }
}