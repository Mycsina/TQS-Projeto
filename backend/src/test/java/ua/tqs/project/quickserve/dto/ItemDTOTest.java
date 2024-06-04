package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.entities.OrderItem;

class ItemDTOTest {

    @DisplayName("Test ItemDTO Constructors")
    @Test
    void testConstructors() {
        BaseItemDTO baseItemDTO = new BaseItemDTO();
        baseItemDTO.setName("Item");

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem(baseItemDTO);

        assertThat(itemDTO.getItem()).isNotNull();
        assertThat(itemDTO.getItem().getName()).isEqualTo("Item");

        Item item = new Item();
        item.setPrice(10.0);
        item.setDescription("Description");
        item.setImage("Image");
        
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        item.setRestaurant(restaurant);

        Category category = new Category();
        category.setId(1L);

        item.setCategory(category);

        ItemDTO itemDTO2 = new ItemDTO(item, new ArrayList<ItemIngredient>());

        assertThat(itemDTO2.getItem().getPrice()).isEqualTo(10.0);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);

        ItemDTO itemDTO3 = new ItemDTO(orderItem, new ArrayList<ItemIngredient>());

        assertThat(itemDTO3.getItem().getPrice()).isEqualTo(10.0);
    }
}
