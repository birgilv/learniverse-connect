package no.ntnu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import no.ntnu.backend.model.Tags;
import no.ntnu.backend.repository.TagsRepository;

/**
 * Service class for managing tags.
 * 
 * @author Group 01
 * @version 22.05.2024
 */
@Service
public class TagsService {

  @Autowired
  private TagsRepository tagsRepository;

  /**
   * Create a new tag.
   * 
   * @param tags The tag to be created.
   * @return ResponseEntity containing a message about the result of the
   *         operation.
   */
  public ResponseEntity<String> create(Tags tags) {
    try {
      addTag(tags);
      return new ResponseEntity<>(tags.toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException iae) {
      return new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Retrieve all tags.
   * 
   * @return List of all tags.
   */
  public List<Tags> readAll() {
    return tagsRepository.findAll();
  }

  /**
   * Retrieve a tag by its ID.
   * 
   * @param id The ID of the tag to retrieve.
   * @return ResponseEntity containing the retrieved tag or a not found status.
   */
  public ResponseEntity<Tags> readById(int id) {
    Tags tags = getTagById(id);
    if (tags != null && tags.isValid()) {
      return new ResponseEntity<>(tags, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Update a tag.
   * 
   * @param id   The ID of the tag to update.
   * @param tags The updated tag information.
   * @return ResponseEntity containing a message about the result of the
   *         operation.
   */
  public ResponseEntity<String> update(int id, Tags tags) {
    try {
      updateTag(id, tags);
      return new ResponseEntity<>(tags.toString(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Delete a tag by its ID.
   * 
   * @param id The ID of the tag to delete.
   * @return ResponseEntity containing a message about the result of the
   *         operation.
   */
  public ResponseEntity<String> delete(int id) {
    if (removeTag(id)) {
      return new ResponseEntity<>("Tag with ID: " + id + " has been deleted", HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Private methods for internal use

  private Tags getTagById(int id) {
    return this.tagsRepository.findById(id).orElse(null);
  }

  private void addTag(Tags tags) throws IllegalArgumentException {
    if (!tags.isValid() || tags == null) {
      throw new IllegalArgumentException("Tag is invalid");
    }
    this.tagsRepository.save(tags);
  }

  private void updateTag(int id, Tags tags) throws IllegalArgumentException {
    Tags existingTag = this.getTagById(id);
    if (existingTag == null) {
      throw new IllegalArgumentException("No tags with ID: " + id + " was found");
    }
    if (tags == null || !tags.isValid()) {
      throw new IllegalArgumentException("wrong data in request body");
    }
    if (tags.getId() != id) {
      throw new IllegalArgumentException("Tag ID in URL does not match the ID in JSON data");
    }
    tags.setId(existingTag.getId());
    this.tagsRepository.save(tags);
  }

  private boolean removeTag(int id) {
    try {
      this.tagsRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID");
    }
  }
}
