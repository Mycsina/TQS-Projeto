package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.project.quickserve.entities.ItemIngredient;

public interface ItemIngredientRepository extends JpaRepository<ItemIngredient, Long> {

    @Query("SELECT i FROM ItemIngredient i WHERE i.item.id = :itemId")
    public List<ItemIngredient> findByItemId(@Param("itemId") long itemId);

    @Query("SELECT i FROM ItemIngredient i WHERE i.orderItem.id = :orderItemId")
    public List<ItemIngredient> findByOrderItemId(@Param("orderItemId") long orderItemId);
}

