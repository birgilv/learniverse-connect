package no.ntnu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.backend.model.Tags;
import no.ntnu.backend.service.TagsService;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controller class for managing operations related to tags.
 * Handles HTTP requests/responses for tag-related endpoints.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@RestController
@RequestMapping("/api/tags")
@CrossOrigin
public class TagsController {

  private final TagsService tagsService;

  /**
   * Constructor for TagsController.
   *
   * @param tagsService The TagsService to be injected.
   */
  @Autowired
  public TagsController(TagsService tagsService) {
    this.tagsService = tagsService;
  }

  /**
   * Creates a new tag.
   *
   * @param tags The tag object to be created.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Creates a new tag", description = "Creates a new tag.")
  @PostMapping()
  public ResponseEntity<String> createTag(@RequestBody Tags tags) {
    return this.tagsService.create(tags);
  }

  /**
   * Retrieves all tags.
   *
   * @return List of Tags containing information about all tags.
   */
  @Operation(summary = "Retrieves all tags", description = "Retrieves all tags.")
  @GetMapping()
  public List<Tags> readAllTags() {
    return this.tagsService.readAll();
  }

  /**
   * Retrieves a tag by its ID.
   *
   * @param id The ID of the tag to retrieve.
   * @return ResponseEntity containing the requested tag, if found.
   */
  @Operation(summary = "Retrieves a tag by its ID", description = "Retrieves a tag by its ID.")
  @GetMapping("/{id}")
  public ResponseEntity<Tags> readTagById(@PathVariable int id) {
    return this.tagsService.readById(id);
  }

  /**
   * Updates an existing tag.
   *
   * @param id   The ID of the tag to be updated.
   * @param tags The updated tag object.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Updates an existing tag", description = "Updates an existing tag.")
  @PutMapping("/{id}")
  public ResponseEntity<String> updateTag(@PathVariable int id, @RequestBody Tags tags) {
    return this.tagsService.update(id, tags);
  }

  /**
   * Deletes a tag by its ID.
   *
   * @param id The ID of the tag to be deleted.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Deletes a tag by its ID", description = "Deletes a tag by its ID.")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTag(@PathVariable int id) {
    return this.tagsService.delete(id);
  }
}
