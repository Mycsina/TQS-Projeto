package ua.tqs.project.quickserve.dto;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Ingredient;

@Getter
@Setter
public class IngredientDTO {
    String name;
    long restaurantId;

    public IngredientDTO(String name, long restaurantId) {
        this.name = name;
        this.restaurantId = restaurantId;
    }

    public IngredientDTO(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.restaurantId = ingredient.getRestaurant().getId();
    }

    public IngredientDTO() {
    }
}
