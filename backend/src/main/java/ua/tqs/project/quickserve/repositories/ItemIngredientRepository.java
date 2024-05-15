package ua.tqs.project.quickserve.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.ItemIngredient;

public interface ItemIngredientRepository extends JpaRepository<ItemIngredient, UUID> {  

}

