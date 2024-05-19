package ua.tqs.project.quickserve.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.repositories.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository repository;

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category getCategoryById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteCategoryById(long id) {
        repository.deleteById(id);
    }
}
