package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.tqs.project.quickserve.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE r.state = ua.tqs.project.quickserve.entities.State.OPEN")
    public List<Restaurant> findOpenRestaurants();
}