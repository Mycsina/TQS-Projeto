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
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MenuRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(MenuRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MenuRepository menuRepository;

    private Address address;
    private User manager;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        this.address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        this.restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, manager);
        restaurant.setTimes("10:00:00", "04:00:00");

        entityManager.persistAndFlush(address);
        entityManager.persistAndFlush(manager);
        entityManager.persistAndFlush(restaurant);
    }

    @Test
    @DisplayName("Find Menu By Id")
    void whenFindMenuByIdthenReturnMenu() {
        Menu menu = new Menu(restaurant);

        entityManager.persistAndFlush(menu); //ensure data is persisted at this point

        long id = menu.getId();
        Menu found = menuRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllMenusthenReturnMenus() {
        Menu menu = new Menu(restaurant);
        entityManager.persist(menu);
        entityManager.flush();
        
        List<Menu> found = menuRepository.findAll();
        assertThat(found).hasSize(1);
    }

    @Test 
    void saveMenu() {
        Menu menu = new Menu(restaurant);
        long id = menu.getId();

        menuRepository.save(menu);
        assertThat(menuRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteMenu() {
        Menu menu = new Menu(restaurant);
        entityManager.persistAndFlush(menu);
        long id = menu.getId();

        assertThat(menuRepository.findById(id)).isNotNull();
        menuRepository.delete(menu);
        assertThat(menuRepository.findById(id)).isEmpty();
    }
}