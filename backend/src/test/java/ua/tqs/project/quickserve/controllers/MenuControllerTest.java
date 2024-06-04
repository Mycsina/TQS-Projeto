package ua.tqs.project.quickserve.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.project.quickserve.services.MenuService;
import ua.tqs.project.quickserve.entities.Menu;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.dto.MenuDTO;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;

@WebMvcTest(MenuController.class)
class MenuControllerTest {
    
    @MockBean
    private MenuService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenGetMenuByIdthenGetMenu( ) {
        Menu someMenu = new Menu();
        MenuDTO someMenuDTO = new MenuDTO();

        when( service.getMenuById(1L) ).thenReturn(someMenu);
        when( service.convertMenuToDTO(someMenu) ).thenReturn(someMenuDTO);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/menus/1")
            .then()
                .statusCode(200);
    }

    @Test
    void whenGetRestaurantMenuthenGetMenu( ) {
        Restaurant someRestaurant = new Restaurant();
        someRestaurant.setId(1L);

        Menu someMenu = new Menu();
        someMenu.setRestaurant(someRestaurant);

        when( service.getRestaurantMenu(1L) ).thenReturn(someMenu);

        MenuDTO someMenuDTO = new MenuDTO();
        someMenuDTO.setRestaurantId(someMenu.getRestaurant().getId());
        
        when( service.convertMenuToDTO(someMenu) ).thenReturn(someMenuDTO);
        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/menus/restaurant/1")
            .then()
                .statusCode(200)
                .body("restaurantId", equalTo(1));}
}