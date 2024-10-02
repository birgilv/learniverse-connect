package no.ntnu.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.backend.model.Image;
import no.ntnu.backend.service.ImageService;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controller class for managing operations related to images.
 * Handles HTTP requests/responses for image-related endpoints.
 * 
 * @author Group 01
 * @version 23.05.2024
 */
@RestController
@CrossOrigin
@RequestMapping("/api/images")
public class ImageController {
  @Autowired
  private ImageService imageService;

  /**
   * Uploads a new image.
   *
   * @param image The image to be uploaded.
   * @return ResponseEntity indication the success/failure of the operation.
   * @throws IOException
   */
  @Operation(summary = "Upload a new image", description = "Uploads a new image object in the system.")
  @PostMapping()
  public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile image, @RequestParam("alt") String altText) throws IOException {
    return this.imageService.create(image, altText);
  }

  /**
   * Retrieved all images.
   *
   * @return List of Images containing information about all images.
   */
  @Operation(summary = "Retrieves all images", description = "Retrieves a list of all image objects in the system.")
  @GetMapping()
  public List<Image> readAllImages() {
    return this.imageService.readAll();
  }

  /**
   * Retrieved an image by its ID.
   *
   * @param id The ID of the image to retrieve.
   * @return ResponseEntity indication the success/failure of the operation.
   */
  @Operation(summary = "Retrieve an image by ID", description = "Retrieves a specific image object based on its ID.")
  @GetMapping("/{id}")
  public ResponseEntity<Image> readImageById(@PathVariable int id) {
    return this.imageService.readById(id);
  }

  /**
   * Retrieved an image by its ID.
   *
   * @param id The ID of the image to retrieve.
   * @return ResponseEntity indication the success/failure of the operation.
   */
  @Operation(summary = "Retrieve an image by ID", description = "Retrieves a specific image object based on its ID.")
  @GetMapping("/{id}/data")
  public ResponseEntity<byte[]> getImageDataById(@PathVariable int id) {
    return this.imageService.readImageById(id);
  }

  /**
   * Updates an existing image.
   *
   * @param id   The ID of the image to update.
   * @param image The image to be updated.
   * @return ResponseEntity indication the success/failure of the operation.
   * @throws IOException
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Update an image", description = "Update an existing image object in the system.")
  @PutMapping("/{id}")
  public ResponseEntity<String> updateImage(@PathVariable int id, @RequestParam("file") MultipartFile image)
      throws IOException {
    return this.imageService.update(id, image);
  }

  /**
   * Deletes an image by its ID.
   *
   * @param id The ID of the image to delete.
   * @return ResponseEntity indication the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Delete an image", description = "Deletes an image object from the system based on its ID.")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteImage(@PathVariable int id) {
    return this.imageService.delete(id);
  }
}