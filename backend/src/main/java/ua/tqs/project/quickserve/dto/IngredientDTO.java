package ua.tqs.project.quickserve.dto;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Ingredient;

@Getter
@Setter
public class IngredientDTO {
    String name;
    double price;

    public IngredientDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public IngredientDTO(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.price = ingredient.getPrice();
    }

    public IngredientDTO() {
    }
}
