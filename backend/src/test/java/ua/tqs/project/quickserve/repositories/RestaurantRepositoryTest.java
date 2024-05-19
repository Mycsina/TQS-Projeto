package ua.tqs.project.quickserve.repositories;

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
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RestaurantRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(RestaurantRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Address restaurantAddress1;
    private Address restaurantAddress2;
    private Menu menu1;
    private Menu menu2;
    private User manager1;
    private User manager2;

    @BeforeEach
    void setUp() {
        this.restaurantAddress1 = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.restaurantAddress2 = new Address("Rua do Burger", "Porto", "4200-055", "Portugal");
        this.menu1 = new Menu();
        this.menu2 = new Menu();
        this.manager1 = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        this.manager2 = new User("Burger King Manager", "1234", RoleEnum.MANAGER, "burgerking.mc.pt", 123123123);

        entityManager.persistAndFlush(restaurantAddress1);
        entityManager.persistAndFlush(restaurantAddress2);
        entityManager.persistAndFlush(menu1);
        entityManager.persistAndFlush(menu2);
        entityManager.persistAndFlush(manager1);
        entityManager.persistAndFlush(manager2);
    }

    @Test
    @DisplayName("Find Restaurant By Id")
    void whenFindRestaurantByIdthenReturnRestaurant() {
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress1, menu1, manager1);
        restaurant.setTimes("10:00:00", "04:00:00");

        entityManager.persistAndFlush(restaurant); //ensure data is persisted at this point

        long id = restaurant.getId();
        Restaurant found = restaurantRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllRestaurantsthenReturnRestaurantes() {
        Restaurant restaurant1 = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress1, menu1, manager1);
        restaurant1.setTimes("10:00:00", "04:00:00");
        Restaurant restaurant2 = new Restaurant("Burger King", "Number 2 in the fast food industry!", 123123123, State.OPEN, restaurantAddress2, menu2, manager2);
        restaurant2.setTimes("10:00:00", "04:00:00");
        entityManager.persist(restaurant1);
        entityManager.persist(restaurant2);
        entityManager.flush();
        
        List<Restaurant> found = restaurantRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveRestaurant() {
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress1, menu1, manager1);
        restaurant.setTimes("10:00:00", "04:00:00");
        long id = restaurant.getId();

        restaurantRepository.save(restaurant);
        assertThat(restaurantRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteRestaurant() {
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, restaurantAddress1, menu1, manager1);
        restaurant.setTimes("10:00:00", "04:00:00");
        entityManager.persistAndFlush(restaurant);
        long id = restaurant.getId();

        assertThat(restaurantRepository.findById(id)).isNotNull();
        restaurantRepository.delete(restaurant);
        assertThat(restaurantRepository.findById(id)).isEmpty();
    }
}