package ua.tqs.project.quickserve.dto;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Ingredient;

@Getter
@Setter
public class IngredientDTO {
    long ingredientId;
    String name;
    double price;
    long restaurantId;

    public IngredientDTO(long ingredientId, String name, double price, long restaurantId) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public IngredientDTO(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.name = ingredient.getName();
        this.price = ingredient.getPrice();
        this.restaurantId = ingredient.getRestaurant().getId();
    }

    public IngredientDTO() {
    }
}
