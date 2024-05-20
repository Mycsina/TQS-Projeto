package ua.tqs.project.quickserve.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.repositories.CategoryRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    protected static final Logger logger = LogManager.getLogger(CategoryServiceTest.class);

    @Mock( lenient = true)
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        Menu menu = new Menu();

        Category category1 = new Category("Burgers", menu); category1.setId(1L);
        Category category2 = new Category("Sauces", menu); category2.setId(2L);
        Category category3 = new Category("Drinks", menu); category3.setId(3L);

        List<Category> allCategorys = Arrays.asList(category1, category2, category3);

        Mockito.when(categoryRepository.findById(category1.getId())).thenReturn(Optional.of(category1));
        Mockito.when(categoryRepository.findById(category2.getId())).thenReturn(Optional.of(category2));
        Mockito.when(categoryRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findAll()).thenReturn(allCategorys);

        Mockito.when(categoryRepository.save(category1)).thenReturn(category1);
    }
    
    @Test
    void whenValidIdthenCategoryShouldBeFound() {
        Category found = categoryService.getCategoryById(1L);
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenInValidIdthenCategoryShouldNotBeFound() {
        Category found = categoryService.getCategoryById(-99L);
        assertThat(found).isNull();;
    }

    @Test
    void whenSaveCategorythenCategoryShouldBeReturned() {
        Menu menu = new Menu();
        Category category = new Category("Burgers", menu); category.setId(1L);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        
        Category savedCategory = categoryService.save(category);
        assertThat(savedCategory).isNotNull();
    }

    @Test
    void whenDeleteCategorythenCategoryShouldBeDeleted() {
        Long categoryId = 1L;

        // Setup mock behavior
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(new Category()));
            
        categoryService.deleteCategoryById(categoryId);
        Mockito.verify(categoryRepository, times(1)).deleteById(categoryId);
    }

}
