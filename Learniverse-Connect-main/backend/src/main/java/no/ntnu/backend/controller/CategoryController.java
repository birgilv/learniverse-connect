package no.ntnu.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.backend.model.Category;
import no.ntnu.backend.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controller class for handling CRUD operations related to categories.
 * Handles HTTP requests/responses for category-related endpoints.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

  /**
   * Constructor for CategoryController.
   *
   * @param categoryService The CategoryService to be injected.
   */
  @Autowired
  public CategoryController(CategoryService categoryService){
    this.categoryService = categoryService;
  }

  /**
   * Creates a new category.
   *
   * @param category The category object to be created.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Creates a new category", description = "Creates a new category.")
  @PostMapping()
  public ResponseEntity<String> createCategory(@RequestBody Category category) {
    return this.categoryService.create(category);
  }

  /**
   * Retrieves all categories.
   *
   * @return List of all categories.
   */
  @Operation(summary = "Retrieves all categories", description = "Retrieves all categories.")
  @GetMapping()
  public List<Category> readAllCategories() {
    return this.categoryService.readAll();
  }

  /**
   * Retrieves a category by its ID.
   *
   * @param id The ID of the category to retrieve.
   * @return ResponseEntity containing the requested category, if found.
   */
  @Operation(summary = "Retrieves a category by its ID", description = "Retrieves a category by its ID.")
  @GetMapping("/{id}")
  public ResponseEntity<Category> readCategoryById(@PathVariable int id) {
    return this.categoryService.readById(id);
  }

  /**
   * Updates an existing category.
   *
   * @param id       The ID of the category to be updated.
   * @param category The updated category object.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Updates an existing category", description = "Updates an existing category.")
  @PutMapping("/{id}")
  public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody Category category) {
    return this.categoryService.update(id, category);
  }

  /**
   * Deletes a category by its ID.
   *
   * @param id The ID of the category to be deleted.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Deletes a category by its ID", description = "Deletes a category by its ID.")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCategory(@PathVariable int id) {
    return this.categoryService.delete(id);
  }
}
