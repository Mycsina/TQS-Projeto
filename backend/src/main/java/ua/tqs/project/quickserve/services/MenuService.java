package ua.tqs.project.quickserve.services; 

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.repositories.MenuRepository;

@Service
@AllArgsConstructor
public class MenuService {
    
    private MenuRepository repository;

    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    public Menu getMenuById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteMenuById(UUID id) {
        repository.deleteById(id);
    }
}
