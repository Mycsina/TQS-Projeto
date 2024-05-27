package ua.tqs.project.quickserve.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.ItemIngredient;

@Getter
@Setter
public class ItemIngredientDTO {
    long itemIngredientId;
    boolean isDefault;
    int quantity;
    IngredientDTO ingredientDTO;

    public ItemIngredientDTO() {
    }

    public ItemIngredientDTO(long itemIngredientId, boolean isDefault, IngredientDTO ingredientDTO, int quantity) {
        this.itemIngredientId = itemIngredientId;
        this.isDefault = isDefault;
        this.ingredientDTO = ingredientDTO;
        this.quantity = quantity;
    }

    public ItemIngredientDTO(ItemIngredient itemIngredient) {
        this.itemIngredientId = itemIngredient.getId();
        this.isDefault = itemIngredient.isDefault();
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
