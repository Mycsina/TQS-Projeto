package ua.tqs.project.quickserve.services; 

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

    public ItemIngredient getItemIngredientById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteItemIngredientById(long id) {
        repository.deleteById(id);
    }
}
