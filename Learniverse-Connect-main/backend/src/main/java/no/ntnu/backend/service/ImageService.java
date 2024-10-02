package no.ntnu.backend.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import no.ntnu.backend.model.Image;
import no.ntnu.backend.repository.ImageRepository;

/**
 * Service class for managing images.
 *
 * @version 23.05.2024
 * @autho Group 01
 */
@Service
public class ImageService {

  @Autowired
  private ImageRepository imageRepository;

  /**
   * Adds a new image to the repository.
   *
   * @param file the image file to be added
   * @param altText the alt text for the image
   * @return a ResponseEntity containing the result of the operation
   * @throws IOException if there is an error reading the image file
   */
  public ResponseEntity<String> create(MultipartFile file, String altText) throws IOException {
    ResponseEntity<String> response;

    try {
      Image image = new Image();
      image.setFileName(file.getOriginalFilename());
      image.setData(file.getBytes());
      image.setContentType(file.getContentType());
      image.setAlt(altText);

      this.addImage(image);
      response = new ResponseEntity<>(file.toString(), HttpStatus.OK);
    } catch (IllegalArgumentException iae) {
      response = new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Retrieves all images from the repository.
   *
   * @return a list of all images
   */
  public List<Image> readAll() {
    return this.imageRepository.findAll();
  }

  /**
   * Retrieves an image by its ID.
   *
   * @param id the ID of the image to be retrieved
   * @return a ResponseEntity containing the image
   */
  public ResponseEntity<Image> readById(int id) {
    ResponseEntity<Image> response;

    Image image = this.getImageById(id);
    if (image != null && image.isValid()) {
      response = new ResponseEntity<>(image, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Retrieves the image data by its ID.
   *
   * @param id the ID of the image to be retrieved
   * @return a ResponseEntity containing the image data
   */
  public ResponseEntity<byte[]> readImageById(int id) {
    ResponseEntity<byte[]> response;

    Image image = this.getImageById(id);
    if (image != null && image.getData() != null && image.isValid()) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.valueOf(image.getContentType()));
      response = new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Updates an existing image in the repository.
   *
   * @param id the ID of the image to be updated
   * @param file the new image file
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> update(@PathVariable int id, MultipartFile file) {
    ResponseEntity<String> response;

    try {
      Image image = new Image();
      image.setFileName(file.getOriginalFilename());
      image.setData(file.getBytes());
      image.setContentType(file.getContentType());
      this.updateImage(id, image);
      response = new ResponseEntity<>(image.toString(), HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Deletes an image by its ID.
   *
   * @param id the ID of the image to be deleted
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> delete(@PathVariable int id) {
    ResponseEntity<String> response;

    if (this.removeImage(id)) {
      response = new ResponseEntity<>("Image with ID: " + id + " has been deleted", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Adds an image to the repository.
   *
   * @param image the image to be added
   * @throws IllegalArgumentException if the image is invalid
   */
  private void addImage(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image is invalid");
    }

    this.addToRepo(image);
  } 

  /**
   * Retrieves an image by its ID.
   *
   * @param id the ID of the image to be retrieved
   * @return the image with the specified ID
   */
  private Image getImageById(int id) {
    return this.imageRepository.findById(id).orElse(null);
  }

  /**
   * Updates an existing image in the repository.
   *
   * @param id the ID of the image to be updated
   * @param image the new image data
   */
  private void updateImage(int id, Image image) {
    Image existingImage = this.getImageById(id);

    if (existingImage == null) {
      throw new IllegalArgumentException("No image with ID: " + id + " was found");
    }
    if (image == null || !image.isValid()) {
      throw new IllegalArgumentException("Invalid image data");
    }
    if (image.getId() != id) {
      throw new IllegalArgumentException("Image ID in URL does not match the ID in JSON data");
    }

    image.setId(existingImage.getId());
    this.addToRepo(image);
  }

  /**
   * Removes an image by its ID.
   *
   * @param id the ID of the image to be removed
   * @return true if the image was successfully removed, false otherwise
   */
  private boolean removeImage(int id) {
    try {
      Image image = this.getImageById(id);
      if (image == null) {
        return false;
      }
      this.imageRepository.delete(image);
      return true;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID", e);
    }
  }

  /**
   * Adds an image to the repository after validating its content type.
   *
   * @param image the image to be added
   */
  private void addToRepo(Image image) {
    if (!isImage(image)) {
      throw new IllegalArgumentException("Image does not have valid content type.");
    }

    this.imageRepository.save(image);
  }

  /**
   * Checks if the image is valid.
   *
   * @param image the image to be checked
   * @return true if the image is valid, false otherwise
   */
  private boolean isImage(Image image) {
    return image != null && this.isImageContentType(image.getContentType());
  }

  private static final String[] IMAGE_CONTENT_TYPES = {"image/png", "image/jpg", "image/jpeg", "image/webp", "image/svg+xml"};

  /**
   * Checks if the content type of the image is valid.
   *
   * @param contentType the content type to be checked
   * @return true if the content type is valid, false otherwise
   */
  private boolean isImageContentType(String contentType) {
    return Arrays.asList(IMAGE_CONTENT_TYPES).contains(contentType);
  }
}
