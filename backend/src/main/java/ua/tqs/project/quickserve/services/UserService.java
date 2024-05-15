package ua.tqs.project.quickserve.services; 

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User getUserById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteUserById(UUID id) {
        repository.deleteById(id);
    }
}
