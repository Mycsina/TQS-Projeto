package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Item;

class ItemDTOTest {

    @DisplayName("Test ItemDTO Constructors")
    @Test
    void testConstructors() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Item");

        assertThat(itemDTO.getName()).isEqualTo("Item");

        ItemDTO itemDTO2 = new ItemDTO("Item2", 1L, 1L);

        assertThat(itemDTO2.getName()).isEqualTo("Item2");
        assertThat(itemDTO2.getRestaurantId()).isEqualTo(1L);
        assertThat(itemDTO2.getCategoryId()).isEqualTo(1L);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        Category category = new Category();
        category.setId(1L);

        Item item = new Item();
        item.setName("Item3");
        item.setRestaurant(restaurant);
        item.setCategory(category);

        ItemDTO itemDTO3 = new ItemDTO(item);

        assertThat(itemDTO3.getName()).isEqualTo("Item3");
        assertThat(itemDTO3.getRestaurantId()).isEqualTo(1L);
        assertThat(itemDTO3.getCategoryId()).isEqualTo(1L);
    }

}
