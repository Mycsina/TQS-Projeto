package ua.tqs.project.quickserve.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.project.quickserve.services.OrderService;
import ua.tqs.project.quickserve.entities.Order;
import ua.tqs.project.quickserve.dto.OrderDTO;
import ua.tqs.project.quickserve.entities.Status;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

//import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    
    @MockBean
    private OrderService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenGetAllOrdersthenGetOrders( ) throws Exception {
        Order someOrder = new Order();
        Order otherOrder = new Order();

        List<Order> allOrders = Arrays.asList(someOrder, otherOrder);

        when( service.getAllOrders() ).thenReturn(allOrders);

        OrderDTO someOrderDTO = new OrderDTO();
        OrderDTO otherOrderDTO = new OrderDTO();

        List<OrderDTO> allOrderDTOs = Arrays.asList(someOrderDTO, otherOrderDTO);

        when( service.convertOrderListToDTOs(allOrders) ).thenReturn(allOrderDTOs);
        
        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/order")
            .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void whenGetOrderByIdthenGetOrder( ) throws Exception {
        Order someOrder = new Order();
        OrderDTO someOrderDTO = new OrderDTO();

        when( service.getOrderById(1L) ).thenReturn(someOrder);
        when( service.convertOrderToDTO(someOrder) ).thenReturn(someOrderDTO);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/order/1")
            .then()
                .statusCode(200);
    }

    @Test
    void whenGetInMakingOrdersthenGetOrders( ) throws Exception {
        Order someOrder = new Order();
        someOrder.setStatus(Status.IN_MAKING);
        Order otherOrder = new Order();
        otherOrder.setStatus(Status.IN_MAKING);

        List<Order> allOrders = Arrays.asList(someOrder, otherOrder);

        when( service.getOrdersByStatus(Status.IN_MAKING) ).thenReturn(allOrders);

        OrderDTO someOrderDTO = new OrderDTO();
        OrderDTO otherOrderDTO = new OrderDTO();

        List<OrderDTO> allOrderDTOs = Arrays.asList(someOrderDTO, otherOrderDTO);

        when( service.convertOrderListToDTOs(allOrders) ).thenReturn(allOrderDTOs);
        
        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/order/inmaking")
            .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }
}