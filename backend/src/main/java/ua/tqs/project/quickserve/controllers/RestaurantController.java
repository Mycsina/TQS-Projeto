package ua.tqs.project.quickserve.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import ua.tqs.project.quickserve.services.RestaurantService;
import ua.tqs.project.quickserve.dto.RestaurantDTO;
import ua.tqs.project.quickserve.dto.MenuDTO;

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

    @Operation(summary = "Get all open restaurants")
    @GetMapping("/open")
    public ResponseEntity<List<RestaurantDTO>> getOpenRestaurants() {
        return new ResponseEntity<>(service.convertRestaurantListToDTOs(service.getOpenRestaurants()), HttpStatus.OK);
    }

    @Operation(summary = "Import menu")
    @PostMapping("/{id}/menu")
    public ResponseEntity<String> importMenu(@PathVariable long id, @RequestBody MenuDTO menu) {
        service.setMenu(menu);
        return new ResponseEntity<>("Menu imported successfully", HttpStatus.OK);
    }

    @Operation(summary = "Create restaurant")
    @PostMapping
    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantDTO restaurant) {
        service.save(service.convertDTOToRestaurant(restaurant));
        return new ResponseEntity<>("Restaurant created successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Delete restaurant by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurantById(@PathVariable long id) {
        service.deleteRestaurantById(id);
        return new ResponseEntity<>("Restaurant deleted successfully", HttpStatus.OK);
    }
}

