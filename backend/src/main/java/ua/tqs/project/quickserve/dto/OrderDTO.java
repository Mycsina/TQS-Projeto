package ua.tqs.project.quickserve.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ua.tqs.project.quickserve.entities.Order;


@Data
@Getter
@Setter
public class OrderDTO {
    // FullOrderDTO needs to consist of:
    // - the order itself
    // - the itemingredients in the order, which are the added/removed ingredients in the items
    BaseOrderDTO order;
    Map<String, List<ItemIngredientDTO>> itemIngredients;

    public OrderDTO(Order order, Map<String, List<ItemIngredientDTO>> itemIngredients) {
        this.order = new BaseOrderDTO(order);
        this.itemIngredients = itemIngredients;
    }

    public OrderDTO() {
    }
}
