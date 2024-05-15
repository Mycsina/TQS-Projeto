package ua.tqs.project.quickserve.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {  

}
