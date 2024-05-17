package ua.tqs.project.quickserve.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Order;

@Getter
@Setter
public class WorkerOrderDTO {
    // FullOrderDTO needs to consist of:
    // - the order itself
    // - the itemingredients in the order, which are the added/removed ingredients in the items
    OrderDTO order;
    Map<String, List<ItemIngredientDTO>> itemIngredients;

    public WorkerOrderDTO(Order order, Map<String, List<ItemIngredientDTO>> itemIngredients) {
        this.order = new OrderDTO(order);
        this.itemIngredients = itemIngredients;
    }
}
