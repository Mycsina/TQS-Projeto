package ua.tqs.project.quickserve.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.repositories.ItemRepository;

@Service
@AllArgsConstructor
public class ItemService {
    
    private ItemRepository repository;

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
