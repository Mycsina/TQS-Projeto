package ua.tqs.project.quickserve.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.dto.IngredientDTO;
import ua.tqs.project.quickserve.entities.Ingredient;
import ua.tqs.project.quickserve.repositories.IngredientRepository;

@Service
@AllArgsConstructor
public class IngredientService {

    private IngredientRepository repository;

    public Ingredient save(Ingredient ingredient) {
        Ingredient existingIngredient = repository.findByNameAndRestaurant(ingredient.getName(), ingredient.getRestaurant().getId());
        if (existingIngredient != null) {
            return existingIngredient;
        }
        return repository.save(ingredient);
    }

    public Ingredient getIngredientById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Ingredient defineIngredient(IngredientDTO ingredientDTO, Item item) {
        Ingredient ingredient = repository.findByNameAndRestaurant(ingredientDTO.getName(), item.getRestaurant().getId());
        if (ingredient != null) {
            return ingredient;
        }
        return save(new Ingredient(ingredientDTO.getName(), ingredientDTO.getPrice(), item.getRestaurant()));
    }

    public void deleteIngredientById(long id) {
        repository.deleteById(id);
    }
}
