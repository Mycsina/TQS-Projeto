package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { 
    
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :id")
    public List<OrderItem> findByOrderId(@Param("id") long id);
}