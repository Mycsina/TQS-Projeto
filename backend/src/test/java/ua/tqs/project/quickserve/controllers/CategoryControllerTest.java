package ua.tqs.project.quickserve.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.project.quickserve.services.CategoryService;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.entities.Menu;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    
    @MockBean
    private CategoryService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenGetCategoryByIdthenGetCategory( ) {
        Category someCategory = new Category();
        someCategory.setId(1L);

        when( service.getCategoryById(1L) ).thenReturn(someCategory);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/items/1")
            .then()
                .statusCode(200);
    }

    @Test
    void whenGetCategoriesByMenuthenGetCategories( ) {
        Menu someMenu = new Menu();
        someMenu.setId(1L);

        Category someCategory = new Category();
        someCategory.setMenu(someMenu);
        Category otherCategory = new Category();
        otherCategory.setMenu(someMenu);

        List<Category> allCategorys = Arrays.asList(someCategory, otherCategory);

        when( service.getCategoriesByMenu(1L) ).thenReturn(allCategorys);
        
        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/items/restaurant/1")
            .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }
}