package ua.tqs.project.quickserve.repositories;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import ua.tqs.project.quickserve.entities.Menu;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MenuRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(MenuRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DisplayName("Find Menu By Id")
    void whenFindMenuByIdthenReturnMenu() {
        Menu menu = new Menu();

        entityManager.persistAndFlush(menu); //ensure data is persisted at this point

        long id = menu.getId();
        Menu found = menuRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllMenuesthenReturnMenues() {
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        entityManager.persist(menu1);
        entityManager.persist(menu2);
        entityManager.flush();
        
        List<Menu> found = menuRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveMenu() {
        Menu menu = new Menu();
        long id = menu.getId();

        menuRepository.save(menu);
        assertThat(menuRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteMenu() {
        Menu menu = new Menu();
        entityManager.persistAndFlush(menu);
        long id = menu.getId();

        assertThat(menuRepository.findById(id)).isNotNull();
        menuRepository.delete(menu);
        assertThat(menuRepository.findById(id)).isEmpty();
    }
}