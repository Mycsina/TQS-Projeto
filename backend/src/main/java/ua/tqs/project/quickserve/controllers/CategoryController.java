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

import ua.tqs.project.quickserve.services.CategoryService;
import ua.tqs.project.quickserve.entities.Category;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/${app.api.version}/categories")
@Tag(name = "Category", description = "Operations pertaining to categories")
public class CategoryController {

    private CategoryService service;

    @Operation(summary = "Get category by id")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        return new ResponseEntity<>(service.getCategoryById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get categories by menu")
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<Category>> getCategoriesByMenu(@PathVariable long menuId) {
        return new ResponseEntity<>(service.getCategoriesByMenu(menuId), HttpStatus.OK);
    }
}
