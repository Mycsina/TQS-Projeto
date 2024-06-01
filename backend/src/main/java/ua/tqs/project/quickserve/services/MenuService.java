package ua.tqs.project.quickserve.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.dto.MenuDTO;
import ua.tqs.project.quickserve.dto.CategoryDTO;
import ua.tqs.project.quickserve.repositories.MenuRepository;

@Service
@AllArgsConstructor
public class MenuService {

    private MenuRepository repository;

    private CategoryService categoryService;

    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    public Menu getRestaurantMenu(long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }

    public Menu getMenuById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void defineMenu(MenuDTO menuDTO, Restaurant restaurant) {
        Menu menu = save(new Menu(restaurant));

        for (CategoryDTO categoryDTO : menuDTO.getCategories()) {
            categoryService.defineCategory(categoryDTO, menu);
        }
    }

    public void deleteMenuById(long id) {
        repository.deleteById(id);
    }
}
