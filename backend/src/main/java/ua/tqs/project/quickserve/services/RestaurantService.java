package ua.tqs.project.quickserve.services; 

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.repositories.RestaurantRepository;

@Service
@AllArgsConstructor
public class RestaurantService {
    
    private RestaurantRepository repository;

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant getRestaurantById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteRestaurantById(UUID id) {
        repository.deleteById(id);
    }
}
