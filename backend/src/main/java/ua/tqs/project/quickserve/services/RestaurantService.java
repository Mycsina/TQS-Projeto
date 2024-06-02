package ua.tqs.project.quickserve.services;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.dto.MenuDTO;
import ua.tqs.project.quickserve.dto.RestaurantDTO;
import ua.tqs.project.quickserve.repositories.RestaurantRepository;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository repository;

    private MenuService menuService;

    private UserService userService;

    private AddressService addressService;

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public RestaurantDTO convertRestaurantToDTO(Restaurant restaurant) {
        return new RestaurantDTO(restaurant);
    }

    public List<RestaurantDTO> convertRestaurantListToDTOs(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::convertRestaurantToDTO).toList();
    }

    public Restaurant convertDTOToRestaurant(RestaurantDTO restaurantDTO) {
        Address address = addressService.getAddressByPostalCode(restaurantDTO.getAddress().getPostalCode());
        if (address == null) {
            address = addressService.save(new Address(restaurantDTO.getAddress()));
        }
        Restaurant restaurant = new Restaurant(restaurantDTO.getName(), restaurantDTO.getDescription(), restaurantDTO.getPhoneNumber(), restaurantDTO.getState(), address, userService.getUserById(restaurantDTO.getManagerId()));
        restaurant.setTimes(restaurantDTO.getOpeningTime(), restaurantDTO.getClosingTime());
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    public Restaurant getRestaurantById(long id) {
        return repository.findById(id).orElse(null);
    }
    
    public List<Restaurant> getOpenRestaurants() {
        return repository.findOpenRestaurants();
    }

    public void setMenu(MenuDTO menu) {
        Restaurant restaurant = getRestaurantById(menu.getRestaurantId());
        menuService.defineMenu(menu, restaurant);
    }

    public void deleteRestaurantById(long id) {
        repository.deleteById(id);
    }
}
