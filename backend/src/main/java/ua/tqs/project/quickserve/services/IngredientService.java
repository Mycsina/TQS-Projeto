package ua.tqs.project.quickserve.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.repositories.IngredientRepository;

@Service
@AllArgsConstructor
public class IngredientService {

    private IngredientRepository repository;

    public Ingredient save(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Ingredient getIngredientById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteIngredientById(long id) {
        repository.deleteById(id);
    }
}
