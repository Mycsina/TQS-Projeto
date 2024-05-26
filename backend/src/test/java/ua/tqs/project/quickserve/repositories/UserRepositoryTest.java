package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import ua.tqs.project.quickserve.entities.Address;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.entities.RoleEnum;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(UserRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private Address address;

    @BeforeEach
    void setUp() {
        this.address = new Address("Rua do Amial", "Porto", "4200-055", "Portugal");
        entityManager.persistAndFlush(address);
    }

    @Test
    @DisplayName("Find User By Id")
    void whenFindUserByIdthenReturnUser() {
        User user = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, address);

        entityManager.persistAndFlush(user); //ensure data is persisted at this point

        long id = user.getId();
        User found = userRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllUsersthenReturnUseres() {
        User user1 = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, address);
        User user2 = new User("McDonald's Manager", "1234", RoleEnum.MANAGER, "mcdonalds.mc.pt", 123123123);
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
        
        List<User> found = userRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveUser() {
        User user = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, address);
        long id = user.getId();

        userRepository.save(user);
        assertThat(userRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteUser() {
        User user = new User("John Doe", "1234", RoleEnum.CLIENT, "john.doe.pt", 123123123, address);
        entityManager.persistAndFlush(user);
        long id = user.getId();

        assertThat(userRepository.findById(id)).isNotNull();
        userRepository.delete(user);
        assertThat(userRepository.findById(id)).isEmpty();
    }
}