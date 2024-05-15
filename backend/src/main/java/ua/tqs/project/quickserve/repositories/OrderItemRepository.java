package ua.tqs.project.quickserve.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {  

}