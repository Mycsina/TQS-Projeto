package ua.tqs.project.quickserve.services; 

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

    public Menu getMenuById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteMenuById(long id) {
        repository.deleteById(id);
    }
}
