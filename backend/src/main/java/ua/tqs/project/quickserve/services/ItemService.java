package ua.tqs.project.quickserve.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.dto.ItemDTO;
import ua.tqs.project.quickserve.repositories.ItemRepository;

@Service
@AllArgsConstructor
public class ItemService {
    
    private ItemRepository repository;

    private ItemIngredientService itemIngredientService;

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public ItemDTO convertItemToDTO(Item item) {
        return new ItemDTO(item, itemIngredientService.getItemIngredients(item.getId()));
    }

    public List<ItemDTO> convertItemListToDTOs(List<Item> items) {
        List<ItemDTO> itemDTOs = new java.util.ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(convertItemToDTO(item));
        }
        return itemDTOs;
    }

    public List<Item> getItemsByRestaurant(long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }

    public List<Item> getItemsByCategory(long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    public Item save(Item item) {
        return repository.save(item);
    }

    public Item getItemById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteItemById(long id) {
        repository.deleteById(id);
    }
}
