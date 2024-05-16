package ua.tqs.project.quickserve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {  

}
