package ua.tqs.project.quickserve.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.ItemIngredient;

@Getter
@Setter
public class ItemDTO {
    // ItemDTO needs to consist of:
    // - the item itself
    // - the itemingredients that make up the item
    BaseItemDTO item;
    List<ItemIngredientDTO> itemIngredients;

    public ItemDTO(Item item, List<ItemIngredient> itemIngredients) {
        this.item = new BaseItemDTO(item);
        this.itemIngredients = ItemIngredientDTO.convertToDTOList(itemIngredients);
    }

    public ItemDTO(OrderItem orderItem, List<ItemIngredient> itemIngredients) {
        this.item = new BaseItemDTO(orderItem);
        this.itemIngredients = ItemIngredientDTO.convertToDTOList(itemIngredients);
    }

    public ItemDTO() {
    }
}
