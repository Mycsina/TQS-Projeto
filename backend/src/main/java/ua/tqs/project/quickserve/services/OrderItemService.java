package ua.tqs.project.quickserve.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ua.tqs.project.quickserve.dto.ItemDTO;
import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.repositories.OrderItemRepository;

@Service
@AllArgsConstructor
public class OrderItemService {

    private OrderItemRepository repository;

    private ItemIngredientService itemIngredientService;

    public List<OrderItem> getOrderItemsByOrderId(long orderId) {
        return repository.findByOrderId(orderId);
    }
    
        public List<ItemIngredient> getOrderItemIngredients(long orderItemId) {
        OrderItem orderItem = getOrderItemById(orderItemId);
        List<ItemIngredient> defaultIngredients = new ArrayList<>(itemIngredientService.getByItemId(orderItem.getItem().getId()));
        List<ItemIngredient> modifiedIngredients = itemIngredientService.getByOrderItemId(orderItemId);

        for (ItemIngredient modifiedIngredient : modifiedIngredients) {
            for (ItemIngredient defaultIngredient : defaultIngredients) {
                if (modifiedIngredient.getIngredient().getId() == defaultIngredient.getIngredient().getId()) {
                    defaultIngredients.remove(defaultIngredient);
                    defaultIngredients.add(modifiedIngredient);
                    break;
                }
            }
        }

        return defaultIngredients;
    }

    public ItemDTO convertOrderItemToItemDTO(OrderItem orderItem) {
        return new ItemDTO(orderItem, getOrderItemIngredients(orderItem.getId()));
    }

    public List<ItemDTO> convertOrderItemsToItemDTOs(List<OrderItem> orderItems) {
        List<ItemDTO> itemDTOs = new java.util.ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            itemDTOs.add(convertOrderItemToItemDTO(orderItem));
        }
        return itemDTOs;
    }

    public OrderItem save(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    public OrderItem getOrderItemById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteOrderItemById(long id) {
        repository.deleteById(id);
    }
}
