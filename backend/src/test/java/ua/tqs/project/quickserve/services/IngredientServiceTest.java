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
import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.RoleEnum;
import ua.tqs.project.quickserve.entities.State;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.repositories.IngredientRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class IngredientServiceTest {
    protected static final Logger logger = LogManager.getLogger(IngredientServiceTest.class);

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @BeforeEach
    public void setUp() {
        Address address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, manager);
        restaurant.setTimes("10:00:00", "04:00:00");


        Ingredient ingredient1 = new Ingredient("Burger", 1.0, true, restaurant); ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient("Tomato", 0.6, true, restaurant); ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient("Lettuce", 0.6, true, restaurant); ingredient3.setId(3L);

        List<Ingredient> allIngredients = Arrays.asList(ingredient1, ingredient2, ingredient3);

        Mockito.when(ingredientRepository.findById(ingredient1.getId())).thenReturn(Optional.of(ingredient1));
        Mockito.when(ingredientRepository.findById(ingredient2.getId())).thenReturn(Optional.of(ingredient2));
        Mockito.when(ingredientRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(ingredientRepository.findAll()).thenReturn(allIngredients);

        Mockito.when(ingredientRepository.save(ingredient1)).thenReturn(ingredient1);
    }
    
    @Test
    void whenValidIdthenIngredientShouldBeFound() {
        Ingredient found = ingredientService.getIngredientById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenIngredientShouldNotBeFound() {
        Ingredient found = ingredientService.getIngredientById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenSaveIngredientthenIngredientShouldBeReturned() {
        Address address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        User manager = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        Restaurant restaurant = new Restaurant("McDonald's", "Number 1 in the fast food industry!", 123123123, State.OPEN, address, manager);
        restaurant.setTimes("10:00:00", "04:00:00");

        Ingredient ingredient = new Ingredient("Burger", 1.0, true, restaurant); ingredient.setId(1L);
        Mockito.when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
        
        Ingredient savedIngredient = ingredientService.save(ingredient);
        assertThat(savedIngredient).isNotNull();
    }

    @Test
    void whenDeleteIngredientthenIngredientShouldBeDeleted() {
        Long ingredientId = 1L;

        // Setup mock behavior
        when(ingredientRepository.findById(ingredientId)).thenReturn(Optional.of(new Ingredient()));
            
        ingredientService.deleteIngredientById(ingredientId);
        Mockito.verify(ingredientRepository, times(1)).deleteById(ingredientId);
    }

}
