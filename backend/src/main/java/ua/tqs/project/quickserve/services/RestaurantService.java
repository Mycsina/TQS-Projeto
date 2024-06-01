package ua.tqs.project.quickserve.services;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.dto.MenuDTO;
import ua.tqs.project.quickserve.dto.RestaurantDTO;
import ua.tqs.project.quickserve.repositories.RestaurantRepository;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository repository;

    private MenuService menuService;

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public RestaurantDTO convertRestaurantToDTO(Restaurant restaurant) {
        return new RestaurantDTO(restaurant);
    }

    public List<RestaurantDTO> convertRestaurantListToDTOs(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::convertRestaurantToDTO).toList();
    }

    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    public Restaurant getRestaurantById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void setMenu(MenuDTO menu) {
        Restaurant restaurant = getRestaurantById(menu.getRestaurantId());
        menuService.defineMenu(menu, restaurant);
    }

    public void deleteRestaurantById(long id) {
        repository.deleteById(id);
    }
}
