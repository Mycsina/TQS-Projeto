package ua.tqs.project.quickserve.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import ua.tqs.project.quickserve.entities.Order;

@Getter
@Setter
public class OrderDTO {
    // OrderDTO needs to consist of:
    // - the order itself
    // - the itemingredients in the order, which are the added/removed ingredients in the items
    BaseOrderDTO order;
    List<ItemDTO> items;

    public OrderDTO(Order order, List<ItemDTO> items) {
        this.order = new BaseOrderDTO(order);
        this.items = items;
    }

    public OrderDTO() {
    }
}
