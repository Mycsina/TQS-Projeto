package ua.tqs.project.quickserve.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import ua.tqs.project.quickserve.entities.ItemIngredient;

@Data
public class ItemIngredientDTO {
    ItemDTO itemDTO;
    IngredientDTO ingredientDTO;
    int quantity;

    public ItemIngredientDTO() {
    }

    public ItemIngredientDTO(ItemDTO itemDTO, IngredientDTO ingredientDTO, int quantity) {
        this.itemDTO = itemDTO;
        this.ingredientDTO = ingredientDTO;
        this.quantity = quantity;
    }

    public ItemIngredientDTO(ItemIngredient itemIngredient) {
        this.itemDTO = new ItemDTO(itemIngredient.getItem());
        this.ingredientDTO = new IngredientDTO(itemIngredient.getIngredient());
        this.quantity = itemIngredient.getIngredientQuantity();
    }

    public static List<ItemIngredientDTO> convertToDTOList(List<ItemIngredient> itemIngredients) {
        List<ItemIngredientDTO> itemIngredientDTOs = new ArrayList<>();
        for (ItemIngredient itemIngredient : itemIngredients) {
            itemIngredientDTOs.add(new ItemIngredientDTO(itemIngredient));
        }
        return itemIngredientDTOs;
    }
}
