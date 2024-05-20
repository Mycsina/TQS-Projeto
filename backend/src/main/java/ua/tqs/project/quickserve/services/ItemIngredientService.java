package ua.tqs.project.quickserve.services; 

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.OrderItem;
import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.repositories.ItemIngredientRepository;

@Service
@AllArgsConstructor
public class ItemIngredientService {
    
    private ItemIngredientRepository repository;

    private OrderItemService orderItemService;

    public List<ItemIngredient> getOrderItemIngredients(long orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItemById(orderItemId);
        List<ItemIngredient> defaultIngredients = new ArrayList<>(repository.findByItemId(orderItem.getItem().getId()));
        List<ItemIngredient> modifiedIngredients = repository.findByOrderItemId(orderItemId);

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

    public ItemIngredient save(ItemIngredient itemIngredient) {
        return repository.save(itemIngredient);
    }

    public ItemIngredient getItemIngredientById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteItemIngredientById(long id) {
        repository.deleteById(id);
    }
}
