package ua.tqs.project.quickserve.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.project.quickserve.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT i FROM Ingredient i WHERE i.name = :name AND i.restaurant.id = :restaurantId")
    Ingredient findByNameAndRestaurant(String name, long restaurantId);
}
