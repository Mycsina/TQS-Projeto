package ua.tqs.project.quickserve.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.OrderItem;

@Getter
@Setter
public class BaseItemDTO {
    long itemId;
    String name;
    String description;
    String image;
    double price;
    long restaurantId;
    long categoryId;

    public BaseItemDTO(long itemId, String name, String description, String image, double price, long restaurantId, long categoryId) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
    }

    public BaseItemDTO(String name, String description, String image, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public BaseItemDTO(Item item) {
        this.itemId = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.image = item.getImage();
        this.price = item.getPrice();
        this.restaurantId = item.getRestaurant().getId();
        this.categoryId = item.getCategory().getId();
    }

    public BaseItemDTO(OrderItem orderItem) {
        Item item = orderItem.getItem();
        this.itemId = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.image = item.getImage();
        this.price = item.getPrice();
        this.restaurantId = item.getRestaurant().getId();
        this.categoryId = item.getCategory().getId();
    }

    public BaseItemDTO() {
    }

    public List<BaseItemDTO> convertToDTOList(List<Item> items) {
        List<BaseItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(new BaseItemDTO(item));
        }
        return itemDTOs;
    }
}
