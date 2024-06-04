package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Ingredient;

class IngredientDTOTest {

    @DisplayName("Test IngredientDTO Constructors")
    @Test
    void testConstructors() {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName("Ingredient");

        assertThat(ingredientDTO.getName()).isEqualTo("Ingredient");

        IngredientDTO ingredientDTO2 = new IngredientDTO("Ingredient2", 0.5);

        assertThat(ingredientDTO2.getName()).isEqualTo("Ingredient2");
        assertThat(ingredientDTO2.getPrice()).isEqualTo(0.5);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        Category category = new Category();
        category.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Ingredient3");
        ingredient.setRestaurant(restaurant);

        IngredientDTO ingredientDTO3 = new IngredientDTO(ingredient);

        assertThat(ingredientDTO3.getName()).isEqualTo("Ingredient3");
    }

}
