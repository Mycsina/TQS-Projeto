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
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ItemRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(ItemRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    private Address address;
    private Menu menu;
    private User manager;
    private Restaurant restaurant;
    private Category category;

    @BeforeEach
    void setUp() {
        this.address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        this.restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, manager);
        this.category = new Category("Burgers", menu);
        restaurant.setTimes("10:00:00", "04:00:00");
        this.menu = new Menu(restaurant);

        entityManager.persistAndFlush(address);
        entityManager.persistAndFlush(manager);
        entityManager.persistAndFlush(restaurant);
        entityManager.persistAndFlush(category);
        entityManager.persistAndFlush(menu);
    }

    @Test
    @DisplayName("Find Item By Id")
    void whenFindItemByIdthenReturnItem() {
        Item item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);

        entityManager.persistAndFlush(item); //ensure data is persisted at this point

        long id = item.getId();
        Item found = itemRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllItemsthenReturnItemes() {
        Item item1 = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        Item item2 = new Item("CBO", "Bacon delicacy!", "./images/cbopic", 7.0, restaurant, category);
        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.flush();
        
        List<Item> found = itemRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveItem() {
        Item item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        long id = item.getId();

        itemRepository.save(item);
        assertThat(itemRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteItem() {
        Item item = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        entityManager.persistAndFlush(item);
        long id = item.getId();

        assertThat(itemRepository.findById(id)).isNotNull();
        itemRepository.delete(item);
        assertThat(itemRepository.findById(id)).isEmpty();
    }

    @Test
    void whenFindByCategoryIdthenReturnItems() {
        Item item1 = new Item("Big Mac", "The most famous burger in the world!", "./images/bigmacpic", 5.0, restaurant, category);
        Item item2 = new Item("CBO", "Bacon delight!", "./images/cbopic", 7.0, restaurant, category);
        Item item3 = new Item("McChicken", "Chicken!", "./images/mcchickenpic", 4.5, restaurant, category);
        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.persist(item3);
        entityManager.flush();
        
        List<Item> found = itemRepository.findByCategoryId(category.getId());
        assertThat(found).hasSize(3);
    }
}