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
import ua.tqs.project.quickserve.entities.Category;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    protected static final Logger logger = LogManager.getLogger(CategoryRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Find Category By Id")
    void whenFindCategoryByIdthenReturnCategory() {
        Menu menu = new Menu();
        entityManager.persistAndFlush(menu);

        Category category = new Category("Burgers", menu);

        entityManager.persistAndFlush(category); //ensure data is persisted at this point

        long id = category.getId();
        Category found = categoryRepository.findById(id).orElse(null);
        
        assertThat(found).isNotNull();
    }

    @Test
    void whenFindAllCategoriesthenReturnCategoryes() {
        Menu menu = new Menu();
        entityManager.persistAndFlush(menu);

        Category category1 = new Category("Burgers", menu);
        Category category2 = new Category("Sauces", menu);
        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.flush();
        
        List<Category> found = categoryRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test 
    void saveCategory() {
        Menu menu = new Menu();
        entityManager.persistAndFlush(menu);

        Category category = new Category("Burgers", menu);
        long id = category.getId();

        categoryRepository.save(category);
        assertThat(categoryRepository.findById(id)).isNotNull();
    }

    @Test
    void deleteCategory() {
        Menu menu = new Menu();
        entityManager.persistAndFlush(menu);

        Category category = new Category("Burgers", menu);
        entityManager.persistAndFlush(category);
        long id = category.getId();

        assertThat(categoryRepository.findById(id)).isNotNull();
        categoryRepository.delete(category);
        assertThat(categoryRepository.findById(id)).isEmpty();
    }

    @Test
    void findByMenuId() {
        Menu menu = new Menu();
        entityManager.persistAndFlush(menu);
        
        Category category1 = new Category("Burgers", menu);
        Category category2 = new Category("Sauces", menu);
        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.flush();
        
        List<Category> found = categoryRepository.findByMenuId(menu.getId());
        assertThat(found).hasSize(2);
    }
}