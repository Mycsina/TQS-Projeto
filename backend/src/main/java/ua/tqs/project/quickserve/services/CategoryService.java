package ua.tqs.project.quickserve.services; 

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.repositories.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService {
    
    private CategoryRepository repository;

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category getCategoryById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteCategoryById(UUID id) {
        repository.deleteById(id);
    }
}
