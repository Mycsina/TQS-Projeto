package ua.tqs.project.quickserve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {  

}
