package ua.tqs.project.quickserve.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.tqs.project.quickserve.services.RestaurantService;
import ua.tqs.project.quickserve.dto.RestaurantDTO;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/${app.api.version}/restaurants")
@Tag(name = "Restaurant", description = "Operations pertaining to restaurants")
public class RestaurantController {

    private RestaurantService service;

    @Operation(summary = "Get restaurant by id")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable long id) {
        return new ResponseEntity<>(service.convertRestaurantToDTO(service.getRestaurantById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get all restaurants")
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return new ResponseEntity<>(service.convertRestaurantListToDTOs(service.getAllRestaurants()), HttpStatus.OK);
    }
}

