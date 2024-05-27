package ua.tqs.project.quickserve.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tqs.project.quickserve.dto.UserCreateDTO;
import ua.tqs.project.quickserve.dto.UserLoginDTO;
import ua.tqs.project.quickserve.entities.User;
import ua.tqs.project.quickserve.services.UserService;

@Log4j2
@RestController
@RequestMapping("/api/${app.api.version}/auth")
@Tag(name = "User Login", description = "User login operations")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        log.info("Creating user with data: {}", userCreateDTO.toString());
        try {
            userService.save(userCreateDTO.toUser());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }
        log.info("User created with email: {}", userCreateDTO.getEmail());
        return ResponseEntity.ok("User created");
    }

    @Operation(summary = "Login a user")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("Logging in user with data: {}", userLoginDTO.toString());
        User user = userService.getUserByEmail(userLoginDTO.getEmail());
        if (user == null) {
            log.info("User not found");
            return ResponseEntity.badRequest().body("User not found");
        }
        String password = user.getPassword();
        if (!password.equals(userLoginDTO.getPassword())) {
            log.info("Invalid password");
            return ResponseEntity.badRequest().body("Invalid password");
        }
        log.info("User logged in");
        return ResponseEntity.ok("User logged in");
    }
}
