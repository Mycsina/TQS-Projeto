package ua.tqs.project.quickserve.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSubmitDTO {
    // OrderSubmitDTO needs to consist of:
    // - the order itself
    // - the ids of the items in the order
    // - the itemingredients in the order, which are the added/removed ingredients in the items
    OrderDTO order;
    long[] items;
    ItemIngredientDTO[] itemIngredients;
}
