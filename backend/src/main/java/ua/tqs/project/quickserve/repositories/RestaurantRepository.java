package ua.tqs.project.quickserve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.project.quickserve.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}