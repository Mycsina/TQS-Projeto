package ua.tqs.project.quickserve.dto;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Item;

@Getter
@Setter
public class ItemDTO {
    String name;
    long restaurantId;
    long categoryId;

    public ItemDTO(String name, long restaurantId, long categoryId) {
        this.name = name;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
    }

    public ItemDTO(Item item) {
        this.name = item.getName();
        this.restaurantId = item.getRestaurant().getId();
        this.categoryId = item.getCategory().getId();
    }
}
