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

    @Test
    void whenGetOpenRestaurantsthenGetRestaurants( ) {
        Restaurant someRestaurant = new Restaurant();
        Restaurant otherRestaurant = new Restaurant();

        List<Restaurant> allRestaurants = Arrays.asList(someRestaurant, otherRestaurant);

        when( service.getOpenRestaurants() ).thenReturn(allRestaurants);

        RestaurantDTO someRestaurantDTO = new RestaurantDTO();
        RestaurantDTO otherRestaurantDTO = new RestaurantDTO();

        List<RestaurantDTO> allRestaurantDTOs = Arrays.asList(someRestaurantDTO, otherRestaurantDTO);

        when( service.convertRestaurantListToDTOs(allRestaurants) ).thenReturn(allRestaurantDTOs);
        
        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/restaurants/open")
            .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void whenImportMenuthenMenuImported( ) {
        RestAssuredMockMvc
            .given()
              .contentType("application/json")
              .body("{\"restaurantId\":\"3\",\"categories\":[{\"name\":\"Burgers\",\"items\":[{\"item\":{\"name\":\"Coronel Single\",\"description\":\"The classic Coronel burger\",\"image\":\"coronel_single.jpg\",\"price\":5.99},\"itemIngredients\":[{\"quantity\":1,\"ingredient\":{\"name\":\"Chicken Fillet\",\"price\":1.99},\"default\":true},{\"quantity\":1,\"ingredient\":{\"name\":\"Cheese\",\"price\":0.39},\"default\":true},{\"quantity\":1,\"ingredient\":{\"name\":\"Lettuce\",\"price\":0.19},\"default\":true},{\"quantity\":1,\"ingredient\":{\"name\":\"Tomato\",\"price\":0.19},\"default\":true},{\"quantity\":1,\"ingredient\":{\"name\":\"Coronel Sauce\",\"price\":0.19},\"default\":true},{\"quantity\":1,\"ingredient\":{\"name\":\"Mayonnaise\",\"price\":0.19},\"default\":true}]}]}]}")
            .when()
                .post("/api/v1/restaurants/1/menu")
            .then()
                .statusCode(200);
    }

    @Test
    void whenCreateRestaurantthenRestaurantCreated( ) {
        RestAssuredMockMvc
            .given()
               .contentType("application/json")
               .body("{\"name\":\"KFC\",\"description\":\"Finger Licking Good\",\"phoneNumber\":123123123,\"openingTime\":\"09:30:00\",\"closingTime\":\"18:00:00\",\"state\":\"OPEN\",\"address\":{\"street\":\"string\",\"city\":\"string\",\"postalCode\":\"string\",\"country\":\"string\"},\"managerId\":4}")
            .when()
                .post("/api/v1/restaurants")
            .then()
                .statusCode(201);
    }

    @Test
    void whenDeleteRestaurantthenRestaurantShouldBeDeleted( ) {
        RestAssuredMockMvc
            .given()
            .when()
                .delete("/api/v1/restaurants/1")
            .then()
                .statusCode(200);
    }
}