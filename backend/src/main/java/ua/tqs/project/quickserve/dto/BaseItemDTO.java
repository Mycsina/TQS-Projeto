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
    String name;
    String description;
    String image;
    double price;
    public BaseItemDTO(String name, String description, String image, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public BaseItemDTO(Item item) {
        this.name = item.getName();
        this.description = item.getDescription();
        this.image = item.getImage();
        this.price = item.getPrice();
    }

    public BaseItemDTO(OrderItem orderItem) {
        Item item = orderItem.getItem();
        this.name = item.getName();
        this.description = item.getDescription();
        this.image = item.getImage();
        this.price = item.getPrice();
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
