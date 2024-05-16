package ua.tqs.project.quickserve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {  

}
