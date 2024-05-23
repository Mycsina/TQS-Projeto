package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Item;

class ItemIngredientDTOTest {

    @DisplayName("Test ItemIngredientDTO Constructors")
    @Test
    void testConstructors() {
        ItemIngredientDTO itemIngredientDTO = new ItemIngredientDTO();
        itemIngredientDTO.setQuantity(10);

        assertThat(itemIngredientDTO.getQuantity()).isEqualTo(10);

        ItemDTO itemDTO = new ItemDTO();
        IngredientDTO ingredientDTO = new IngredientDTO();
        int quantity = 1;
        ItemIngredientDTO itemIngredientDTO2 = new ItemIngredientDTO(itemDTO, ingredientDTO, quantity);

        assertThat(itemIngredientDTO2.getItemDTO()).isEqualTo(itemDTO);
        assertThat(itemIngredientDTO2.getIngredientDTO()).isEqualTo(ingredientDTO);
        assertThat(itemIngredientDTO2.getQuantity()).isEqualTo(quantity);

        ItemIngredient itemIngredient = new ItemIngredient();
        itemIngredient.setIngredientQuantity(2);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        Category category = new Category();
        category.setId(1L);

        Item item = new Item();
        item.setName("Ingredient");
        item.setRestaurant(restaurant);
        item.setCategory(category);
        itemIngredient.setItem(item);

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Ingredient");
        ingredient.setRestaurant(restaurant);
        itemIngredient.setIngredient(ingredient);

        ItemIngredientDTO itemIngredientDTO3 = new ItemIngredientDTO(itemIngredient);

        assertThat(itemIngredientDTO3.getQuantity()).isEqualTo(2);
    }

}
