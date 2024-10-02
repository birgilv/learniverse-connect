package no.ntnu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import no.ntnu.backend.model.Level;
import no.ntnu.backend.repository.LevelRepository;

/**
 * Service class for managing levels.
 *
 * @version 30.03.2024
 */
@Service
public class LevelService {

  @Autowired
  private LevelRepository levelRepository;

  /**
   * Creates a new level and adds it to the repository.
   *
   * @param level the level to be created
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> create(Level level) {
    ResponseEntity<String> response;

    try {
      this.addLevel(level);
      response = new ResponseEntity<>(level.toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException iae) {
      response = new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Retrieves all levels from the repository.
   *
   * @return a list of all levels
   */
  public List<Level> readAll() {
    return this.levelRepository.findAll();
  }

  /**
   * Retrieves a level by its ID.
   *
   * @param id the ID of the level to be retrieved
   * @return a ResponseEntity containing the level
   */
  public ResponseEntity<Level> readById(int id) {
    ResponseEntity<Level> response;

    Level level = this.getLevelById(id);
    if (level != null && level.isValid()) {
      response = new ResponseEntity<>(level, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Updates an existing level in the repository.
   *
   * @param id the ID of the level to be updated
   * @param level the new level data
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> update(int id, Level level) {
    ResponseEntity<String> response;

    try {
      this.updateLevel(id, level);
      response = new ResponseEntity<>(level.toString(), HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Deletes a level by its ID.
   *
   * @param id the ID of the level to be deleted
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> delete(int id) {
    ResponseEntity<String> response;

    if (this.removeLevel(id)) {
      response = new ResponseEntity<>("Level with ID: " + id + " has been deleted", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }
  
  /**
   * Retrieves a level by its ID.
   *
   * @param id the ID of the level to be retrieved
   * @return the level with the specified ID
   */
  private Level getLevelById(int id) {
    return this.levelRepository.findById(id).orElse(null);
  }

  /**
   * Adds a new level to the repository.
   *
   * @param level the level to be added
   * @throws IllegalArgumentException if the level is invalid
   */
  private void addLevel(Level level) throws IllegalArgumentException {
    if (level == null || !level.isValid()) {
      throw new IllegalArgumentException("Level is invalid");
    }

    this.levelRepository.save(level);
  }

  /**
   * Updates an existing level in the repository.
   *
   * @param id the ID of the level to be updated
   * @param level the new level data
   * @throws IllegalArgumentException if the level is invalid
   */
  private void updateLevel(int id, Level level) throws IllegalArgumentException {
    Level existingLevel = this.getLevelById(id);

    if (existingLevel == null) {
      throw new IllegalArgumentException("No level with ID: " + id + " was found");
    }
    if (level == null || !level.isValid()) {
      throw new IllegalArgumentException("Invalid level data");
    }
    if (level.getId() != id) {
      throw new IllegalArgumentException("Level ID in URL does not match the ID in JSON data");
    }

    level.setId(existingLevel.getId());
    this.levelRepository.save(level);
  }

  /**
   * Removes a level by its ID.
   *
   * @param id the ID of the level to be removed
   * @return true if the level was successfully removed, false otherwise
   */
  private boolean removeLevel(int id) {
    boolean result = false;

    try {
      Level level = this.getLevelById(id);
      if (level != null) {
        this.levelRepository.delete(level);
        result = true;
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID", e);
    }

    return result;
  }
}
