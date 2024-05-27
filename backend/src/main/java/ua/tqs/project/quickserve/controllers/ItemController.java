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

import ua.tqs.project.quickserve.dto.ItemDTO;
//import org.springframework.web.bind.annotation.*;
import ua.tqs.project.quickserve.services.ItemService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/${app.api.version}/items")
@Tag(name = "Item", description = "Operations pertaining to items")
public class ItemController {

    private ItemService service;

    @Operation(summary = "Get all items")
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return new ResponseEntity<>(service.convertItemListToDTOs(service.getAllItems()), HttpStatus.OK);
    }

    @Operation(summary = "Get item by id")
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable long id) {
        return new ResponseEntity<>(service.convertItemToDTO(service.getItemById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get items by restaurant")
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ItemDTO>> getItemsByRestaurant(@PathVariable long restaurantId) {
        return new ResponseEntity<>(service.convertItemListToDTOs(service.getItemsByRestaurant(restaurantId)), HttpStatus.OK);
    }

    @Operation(summary = "Get items by category")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ItemDTO>> getItemsByCategory(@PathVariable long categoryId) {
        return new ResponseEntity<>(service.convertItemListToDTOs(service.getItemsByCategory(categoryId)), HttpStatus.OK);
    }
}
