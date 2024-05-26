package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.tqs.project.quickserve.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Long> { 
    
    @Query("SELECT i FROM Item i WHERE i.category.id = :id")
    public List<Item> findByCategoryId(@Param("id") long id);
}
