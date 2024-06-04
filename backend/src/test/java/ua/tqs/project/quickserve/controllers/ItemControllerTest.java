package ua.tqs.project.quickserve.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.project.quickserve.services.ItemService;
import ua.tqs.project.quickserve.entities.Item;
import ua.tqs.project.quickserve.entities.Category;
import ua.tqs.project.quickserve.dto.ItemDTO;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {
    
    @MockBean
    private ItemService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenGetAllItemsthenGetItems( ) {
        Item someItem = new Item();
        Item otherItem = new Item();

        List<Item> allItems = Arrays.asList(someItem, otherItem);

        when( service.getAllItems() ).thenReturn(allItems);

        ItemDTO someItemDTO = new ItemDTO();
        ItemDTO otherItemDTO = new ItemDTO();

        List<ItemDTO> allItemDTOs = Arrays.asList(someItemDTO, otherItemDTO);

        when( service.convertItemListToDTOs(allItems) ).thenReturn(allItemDTOs);
        
        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/items")
            .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void whenGetItemByIdthenGetItem( ) {
        Item someItem = new Item();
        ItemDTO someItemDTO = new ItemDTO();

        when( service.getItemById(1L) ).thenReturn(someItem);
        when( service.convertItemToDTO(someItem) ).thenReturn(someItemDTO);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/items/1")
            .then()
                .statusCode(200);
    }

    @Test
    void whenGetItemsByCategorythenGetItems( ) {
        Category someCategory = new Category();
        someCategory.setId(1L);

        Item someItem = new Item();
        someItem.setCategory(someCategory);
        Item otherItem = new Item();
        otherItem.setCategory(someCategory);

        List<Item> allItems = Arrays.asList(someItem, otherItem);

        when( service.getItemsByCategory(1L) ).thenReturn(allItems);

        ItemDTO someItemDTO = new ItemDTO();
        ItemDTO otherItemDTO = new ItemDTO();

        List<ItemDTO> allItemDTOs = Arrays.asList(someItemDTO, otherItemDTO);

        when( service.convertItemListToDTOs(allItems) ).thenReturn(allItemDTOs);
        
        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/v1/items/category/1")
            .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }
}