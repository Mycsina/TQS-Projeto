package ua.tqs.project.quickserve.services; 

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.repositories.IngredientRepository;

@Service
@AllArgsConstructor
public class IngredientService {
    
    private IngredientRepository repository;

    public Ingredient save(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Ingredient getIngredientById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteIngredientById(UUID id) {
        repository.deleteById(id);
    }
}
