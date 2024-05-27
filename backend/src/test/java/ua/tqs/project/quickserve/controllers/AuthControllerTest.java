package ua.tqs.project.quickserve.controllers;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.project.quickserve.dto.UserCreateDTO;
import ua.tqs.project.quickserve.dto.UserLoginDTO;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.services.UserService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @MockBean UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenPostValidRequestToSignup_thenReturnsOk() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmail("asd@example.com");
        userCreateDTO.setPassword("123456");
        userCreateDTO.setName("asd");
        userCreateDTO.setPhone("123456789");

        RestAssuredMockMvc
            .given()
            .contentType("application/json")
            .body(userCreateDTO)
            .when()
                .post("/api/v1/auth/signup")
            .then()
                .statusCode(200);
    }

    @Test
    void whenPostInvalidRequestToSignup_thenReturnsBadRequest() {
        RestAssuredMockMvc
            .given()
            .contentType("application/json")
            .body("{}")
            .when()
                .post("/api/v1/auth/signup")
            .then()
                .statusCode(400);
    }

    @Test
    void whenPostValidRequestToLogin_thenReturnsOk() {
        User user = new User();
        user.setEmail("asd@test.com");
        user.setPassword("123456");
        when(userService.getUserByEmail("asd@test.com")).thenReturn(user);

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("asd@test.com");
        userLoginDTO.setPassword("123456");

        RestAssuredMockMvc
            .given()
            .contentType("application/json")
            .body(userLoginDTO)
            .when()
                .post("/api/v1/auth/login")
            .then()
                .statusCode(200);
    }

    @Test
    void whenPostInvalidRequestToLogin_thenReturnsBadRequest() {
        RestAssuredMockMvc
            .given()
            .contentType("application/json")
            .body("{}")
            .when()
                .post("/api/v1/auth/login")
            .then()
                .statusCode(400);
    }

    @Test
    void whenPostInvalidEmailToLogin_thenReturnsBadRequest() {
        when(userService.getUserByEmail(anyString())).thenReturn(null);

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("asd@example.com");
        userLoginDTO.setPassword("123456");

        RestAssuredMockMvc
            .given()
            .contentType("application/json")
            .body(userLoginDTO)
            .when()
                .post("/api/v1/auth/login")
            .then()
                .statusCode(400);
    }

    @Test
    void whenPostInvalidPasswordToLogin_thenReturnsBadRequest() {
        User user = new User();
        user.setEmail("asd@example.com");
        user.setPassword("123456");
        when(userService.getUserByEmail("asd@example.com")).thenReturn(user);

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("asd@example.com");
        userLoginDTO.setPassword("wrong password");

        RestAssuredMockMvc
            .given()
            .contentType("application/json")
            .body(userLoginDTO)
            .when()
                .post("/api/v1/auth/login")
            .then()
                .statusCode(400);
    }
}