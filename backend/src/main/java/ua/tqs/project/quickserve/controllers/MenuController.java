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

import ua.tqs.project.quickserve.services.MenuService;
import ua.tqs.project.quickserve.entities.Menu;

@RestController
@AllArgsConstructor
@RequestMapping("/api/${app.api.version}/menus")
@Tag(name = "Menu", description = "Operations pertaining to menus")
public class MenuController {

    private MenuService service;

    @Operation(summary = "Get menu by id")
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable long id) {
        return new ResponseEntity<>(service.getMenuById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get restaurant menu")
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Menu> getRestaurantMenu(@PathVariable long restaurantId) {
        return new ResponseEntity<>(service.getRestaurantMenu(restaurantId), HttpStatus.OK);
    }
}
