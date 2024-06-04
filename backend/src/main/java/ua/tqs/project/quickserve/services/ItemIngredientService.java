package ua.tqs.project.quickserve.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.dto.ItemIngredientDTO;
import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.repositories.ItemIngredientRepository;

@Service
@AllArgsConstructor
public class ItemIngredientService {

    private ItemIngredientRepository repository;

    private IngredientService ingredientService;

    public List<ItemIngredient> getItemIngredients(long itemId) {
        return repository.findByItemId(itemId);
    }

    public List<ItemIngredient> getByOrderItemId(long orderItemId) {
        return repository.findByOrderItemId(orderItemId);
    }

    public List<ItemIngredient> getByItemId(long itemId) {
        return repository.findByItemId(itemId);
    }

    public ItemIngredient save(ItemIngredient itemIngredient) {
        return repository.save(itemIngredient);
    }

    public ItemIngredient getItemIngredientById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void defineItemIngredient(ItemIngredientDTO itemIngredientDTO, Item item) {
        Ingredient ingredient = ingredientService.defineIngredient(itemIngredientDTO.getIngredient(), item);
        save(new ItemIngredient(itemIngredientDTO.getQuantity(), itemIngredientDTO.isDefault(), item, ingredient));
    }

    public void deleteItemIngredientById(long id) {
        repository.deleteById(id);
    }
}
