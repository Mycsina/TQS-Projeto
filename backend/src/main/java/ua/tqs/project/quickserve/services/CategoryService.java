package ua.tqs.project.quickserve.services;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import ua.tqs.project.quickserve.dto.CategoryDTO;
import ua.tqs.project.quickserve.dto.ItemDTO;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.repositories.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository repository;

    private ItemService itemService;

    public Category save(Category category) {
        return repository.save(category);
    }

    public List<Category> getCategoriesByMenu(long menuId) {
        return repository.findByMenuId(menuId);
    }

    public Category getCategoryById(long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean existsByNameAndMenu(String name, long menuId) {
        Category category = repository.findByNameAndMenuId(name, menuId);
        return category != null;
    }

    public void defineCategory(CategoryDTO category, Menu menu) {
        if (existsByNameAndMenu(category.getName(), menu.getId())) {
            return;
        }
        Category newCategory = save(new Category(category.getName(), menu));

        for (ItemDTO item : category.getItems() ) {
            itemService.defineItem(item, newCategory);
        }
    }
    
    public List<CategoryDTO> convertCategoryListToDTOs(List<Category> categories) {
        List<CategoryDTO> categoryDTOs = new java.util.ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(new CategoryDTO(category.getName(), itemService.convertItemListToDTOs(itemService.getItemsByCategory(category.getId()))));
        }
        return categoryDTOs;
    }

    public void deleteCategoryById(long id) {
        repository.deleteById(id);
    }
}
