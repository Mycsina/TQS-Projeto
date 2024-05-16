package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {  
    List<Order> findByStatus(Status status);
}
