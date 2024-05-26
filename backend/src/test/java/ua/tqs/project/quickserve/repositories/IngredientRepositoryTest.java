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
import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IngredientRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(IngredientRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IngredientRepository ingredientRepository;

    private Address address;
    private Menu menu;
    private User manager;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        this.address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        this.menu = new Menu();
        this.manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        this.restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, menu, manager);
        restaurant.setTimes("10:00:00", "04:00:00");

        entityManager.persistAndFlush(address);
        entityManager.persistAndFlush(menu);
        entityManager.persistAndFlush(manager);
        entityManager.persistAndFlush(restaurant);
    }

    @Test
    @DisplayName("Find Ingredient By Id")
    void whenFindIngredientByIdthenReturnIngredient() {
        Ingredient ingredient = new Ingredient("Burger", 1.0, true, restaurant);

        entityManager.persistAndFlush(ingredient); //ensure data is persisted at this point

        long id = ingredient.getId();
        Ingredient found = ingredientRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllIngredientsthenReturnIngredientes() {
        Ingredient ingredient1 = new Ingredient("Burger", 1.0, true, restaurant);
        Ingredient ingredient2 = new Ingredient("Tomato", 0.6, true, restaurant);
        entityManager.persist(ingredient1);
        entityManager.persist(ingredient2);
        entityManager.flush();
        
        List<Ingredient> found = ingredientRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveIngredient() {
        Ingredient ingredient = new Ingredient("Burger", 1.0, true, restaurant);
        long id = ingredient.getId();

        ingredientRepository.save(ingredient);
        assertThat(ingredientRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteIngredient() {
        Ingredient ingredient = new Ingredient("Burger", 1.0, true, restaurant);
        entityManager.persistAndFlush(ingredient);
        long id = ingredient.getId();

        assertThat(ingredientRepository.findById(id)).isNotNull();
        ingredientRepository.delete(ingredient);
        assertThat(ingredientRepository.findById(id)).isEmpty();
    }
}