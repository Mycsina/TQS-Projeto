package ua.tqs.project.quickserve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.tqs.project.quickserve.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}