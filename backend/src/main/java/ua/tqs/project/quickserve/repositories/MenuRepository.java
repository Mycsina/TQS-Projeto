package ua.tqs.project.quickserve.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.tqs.project.quickserve.entities.Menu;

public interface MenuRepository extends JpaRepository<Menu, UUID> {  

}