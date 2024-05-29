package ua.tqs.project.quickserve.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.project.quickserve.services.RestaurantService;
import ua.tqs.project.quickserve.entities.Restaurant;
import ua.tqs.project.quickserve.dto.RestaurantDTO;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {
    
    @MockBean
    private RestaurantService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenGetAllRestaurantsthenGetRestaurants( ) {
        Restaurant someRestaurant = new Restaurant();
        Restaurant otherRestaurant = new Restaurant();

        List<Restaurant> allRestaurants = Arrays.asList(someRestaurant, otherRestaurant);

        when( service.getAllRestaurants() ).thenReturn(allRestaurants);

        RestaurantDTO someRestaurantDTO = new RestaurantDTO();
        RestaurantDTO otherRestaurantDTO = new RestaurantDTO();

        List<RestaurantDTO> allRestaurantDTOs = Arrays.asList(someRestaurantDTO, otherRestaurantDTO);

        when( service.convertRestaurantListToDTOs(allRestaurants) ).thenReturn(allRestaurantDTOs);
        
        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/restaurants")
            .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void whenGetRestaurantByIdthenGetRestaurant( ) {
        Restaurant someRestaurant = new Restaurant();
        RestaurantDTO someRestaurantDTO = new RestaurantDTO();

        when( service.getRestaurantById(1L) ).thenReturn(someRestaurant);
        when( service.convertRestaurantToDTO(someRestaurant) ).thenReturn(someRestaurantDTO);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/restaurants/1")
            .then()
                .statusCode(200);
    }
}