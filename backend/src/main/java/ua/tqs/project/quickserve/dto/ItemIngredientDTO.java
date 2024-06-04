package ua.tqs.project.quickserve.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.ItemIngredient;

@Getter
@Setter
public class ItemIngredientDTO {
    boolean isDefault;
    int quantity;
    IngredientDTO ingredient;

    public ItemIngredientDTO() {
    }

    public ItemIngredientDTO( boolean isDefault, IngredientDTO ingredient, int quantity) {
        this.isDefault = isDefault;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public ItemIngredientDTO(ItemIngredient itemIngredient) {
        this.isDefault = itemIngredient.isDefault();
        this.ingredient = new IngredientDTO(itemIngredient.getIngredient());
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
