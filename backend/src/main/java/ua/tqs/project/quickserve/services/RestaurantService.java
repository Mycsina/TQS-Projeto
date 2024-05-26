package ua.tqs.project.quickserve.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.repositories.RestaurantRepository;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository repository;

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant getRestaurantById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteRestaurantById(long id) {
        repository.deleteById(id);
    }
}
