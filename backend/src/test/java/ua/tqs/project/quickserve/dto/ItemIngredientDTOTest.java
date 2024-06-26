package ua.tqs.project.quickserve.dto;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
        itemIngredientDTO.setIngredient(new IngredientDTO());
        itemIngredientDTO.setQuantity(10);
        itemIngredientDTO.setDefault(true);

        assertThat(itemIngredientDTO.getQuantity()).isEqualTo(10);

        IngredientDTO ingredientDTO = new IngredientDTO();
        ItemIngredientDTO itemIngredientDTO2 = new ItemIngredientDTO(true, ingredientDTO, 5);

        assertThat(itemIngredientDTO2.isDefault()).isTrue();
        assertThat(itemIngredientDTO2.getIngredient()).isEqualTo(ingredientDTO);
        assertThat(itemIngredientDTO2.getQuantity()).isEqualTo(5);

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

    @DisplayName("Test ItemIngredientDTO.convertToDTOList")
    @Test
    void testConvertToDTOList() {
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

        ItemIngredient itemIngredient2 = new ItemIngredient();
        itemIngredient2.setIngredientQuantity(3);
        itemIngredient2.setItem(item);
        itemIngredient2.setIngredient(ingredient);

        List<ItemIngredient> itemIngredientList = List.of(itemIngredient, itemIngredient2);

        assertThat(ItemIngredientDTO.convertToDTOList(itemIngredientList)).isNotNull();
        assertThat(ItemIngredientDTO.convertToDTOList(itemIngredientList)).hasSize(2);
    }

}
