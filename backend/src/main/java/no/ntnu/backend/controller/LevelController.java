package no.ntnu.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import no.ntnu.backend.model.Level;
import no.ntnu.backend.service.LevelService;

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
import io.swagger.v3.oas.annotations.Operation;


/**
 * Controller class for managing operations related to levels.
 * Handles HTTP requests/responses for level-related endpoints.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@RestController
@RequestMapping("/api/levels")
@CrossOrigin
public class LevelController {

  private final LevelService levelService;

  /**
   * Constructor for LevelController.
   *
   * @param levelService The LevelService to be injected.
   */
  @Autowired
  public LevelController(LevelService levelService) {
    this.levelService = levelService;
  }

  /**
   * Creates a new level.
   *
   * @param level The level object to be created.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Create a new level", description = "Creates a new level object in the system.")
  @PostMapping()
  public ResponseEntity<String> createLevel(@RequestBody Level level) {
    return levelService.create(level);
  }

  /**
   * Retrieves all levels.
   *
   * @return List of Level containing information about all levels.
   */
  @Operation(summary = "Retrieves all levels", description = "Retrieves a list of all level objects in the system.")
  @GetMapping()
  public List<Level> readAllLevels() {
    return levelService.readAll();
  }

  /**
   * Retrieves a level by its ID.
   *
   * @param id The ID of the level to retrieve.
   * @return ResponseEntity containing the requested level, if found.
   */
  @Operation(summary = "Retrieve a level by ID", description = "Retrieves a specific level object based on its ID.")
  @GetMapping("/{id}")
  public ResponseEntity<Level> readLevelById(@PathVariable int id) {
    return levelService.readById(id);
  }

  /**
   * Updates an existing level.
   *
   * @param id    The ID of the level to be updated.
   * @param level The updated level object.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Update a level", description = "Updates an existing level object in the system.")
  @PutMapping("/{id}")
  public ResponseEntity<String> updateLevel(@PathVariable int id, @RequestBody Level level) {
    return levelService.update(id, level);
  }

  /**
   * Deletes a level by its ID.
   *
   * @param id The ID of the level to be deleted.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Delete a level", description = "Deletes a level object from the system based on its ID.")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteLevel(@PathVariable int id) {
    return levelService.delete(id);
  }
}