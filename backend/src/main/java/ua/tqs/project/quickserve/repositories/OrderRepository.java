package ua.tqs.project.quickserve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.entities.Status;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(Status status);
}
