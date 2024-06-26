package ua.tqs.project.quickserve.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.Restaurant;

class BaseItemDTOTest {

    @DisplayName("Test BaseItemDTO Constructors")
    @Test
    void testConstructors() {
        BaseItemDTO itemDTO = new BaseItemDTO();
        itemDTO.setName("Item");
        itemDTO.setDescription("Description");
        itemDTO.setImage("Image");
        itemDTO.setPrice(10.0);

        assertThat(itemDTO.getName()).isEqualTo("Item");
        assertThat(itemDTO.getDescription()).isEqualTo("Description");
        assertThat(itemDTO.getImage()).isEqualTo("Image");
        assertThat(itemDTO.getPrice()).isEqualTo(10.0);

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
 
        BaseItemDTO itemDTO2 = new BaseItemDTO(item);

        assertThat(itemDTO2.getPrice()).isEqualTo(10.0);
        assertThat(itemDTO2.getDescription()).isEqualTo("Description");
        assertThat(itemDTO2.getImage()).isEqualTo("Image");

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);

        BaseItemDTO itemDTO3 = new BaseItemDTO(orderItem);

        assertThat(itemDTO3.getPrice()).isEqualTo(10.0);
        assertThat(itemDTO3.getDescription()).isEqualTo("Description");

        BaseItemDTO itemDTO4 = new BaseItemDTO("Item2", "Description2", "Image2", 20.0);

        assertThat(itemDTO4.getName()).isEqualTo("Item2");
        assertThat(itemDTO4.getDescription()).isEqualTo("Description2");
    }
}
