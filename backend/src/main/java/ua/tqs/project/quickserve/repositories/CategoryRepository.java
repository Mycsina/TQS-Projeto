package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.project.quickserve.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.menu.id = :menuId")
    List<Category> findByMenuId(@Param("menuId") Long menuId);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.menu.id = :menuId")
    Category findByNameAndMenuId(@Param("name") String name, @Param("menuId") Long menuId);
}
