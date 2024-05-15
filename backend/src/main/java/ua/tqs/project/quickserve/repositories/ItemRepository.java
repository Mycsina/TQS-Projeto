package ua.tqs.project.quickserve.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {  

}
