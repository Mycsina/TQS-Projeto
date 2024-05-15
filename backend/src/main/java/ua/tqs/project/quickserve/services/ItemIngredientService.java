package ua.tqs.project.quickserve.services; 

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.ItemIngredient;
import ua.tqs.project.quickserve.repositories.ItemIngredientRepository;

@Service
@AllArgsConstructor
public class ItemIngredientService {
    
    private ItemIngredientRepository repository;

    public ItemIngredient save(ItemIngredient itemIngredient) {
        return repository.save(itemIngredient);
    }

    public ItemIngredient getItemIngredientById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteItemIngredientById(UUID id) {
        repository.deleteById(id);
    }
}
