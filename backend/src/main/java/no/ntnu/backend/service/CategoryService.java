package no.ntnu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import no.ntnu.backend.model.Category;
import no.ntnu.backend.repository.CategoryRepository;
import no.ntnu.backend.service.CategoryService;

/**
 * Service class for managing Category entities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  /**
   * Creates a new Category.
   * 
   * @param category the Category to be created
   * @return ResponseEntity with the created Category string and HTTP status
   */
  public ResponseEntity<String> create(Category category) {
    ResponseEntity<String> response;

    try {
      this.addCategory(category);
      response = new ResponseEntity<>(category.toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException iae) {
      response = new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Retrieves all Categories.
   * 
   * @return a list of all Categories
   */
  public List<Category> readAll() {
    return this.categoryRepository.findAll();
  }

  /**
   * Retrieves a Category by its ID.
   * 
   * @param id the ID of the Category
   * @return ResponseEntity with the Category and HTTP status
   */
  public ResponseEntity<Category> readById(int id) {
    ResponseEntity<Category> response;

    Category category = this.getCategoryById(id);
    if (category.isValid() && category != null) {
      response = new ResponseEntity<>(category, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Updates an existing Category.
   * 
   * @param id the ID of the Category to be updated
   * @param category the new Category data
   * @return ResponseEntity with the updated Category string and HTTP status
   */
  public ResponseEntity<String> update(int id, Category category) {
    ResponseEntity<String> response;

    try {
      this.updateCategory(id, category);
      response = new ResponseEntity<>(category.toString(), HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Deletes a Category by its ID.
   * 
   * @param id the ID of the Category to be deleted
   * @return ResponseEntity with a message and HTTP status
   */
  public ResponseEntity<String> delete(int id) {
    ResponseEntity<String> response;

    if (this.removeCategory(id)) {
      response = new ResponseEntity<>("Category with ID: " + id + " has been deleted", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }
  
  /**
   * Retrieves a Category by its ID.
   * 
   * @param id the ID of the Category
   * @return the Category entity
   */
  private Category getCategoryById(int id) {
    return this.categoryRepository.findById(id).get();
  }

  /**
   * Adds a new Category.
   * 
   * @param category the Category to be added
   * @throws IllegalArgumentException if the Category is invalid
   */
  private void addCategory(Category category) throws IllegalArgumentException {
    if (!category.isValid() || category == null) {
      throw new IllegalArgumentException("Category is invalid");
    }

    this.categoryRepository.save(category);
  }

  /**
   * Updates an existing Category.
   * 
   * @param id the ID of the Category to be updated
   * @param category the new Category data
   * @throws IllegalArgumentException if the Category is invalid or ID mismatch
   */
  private void updateCategory(int id, Category category) throws IllegalArgumentException {
    Category existingCategory = this.getCategoryById(id);

    if (existingCategory == null) {
      throw new IllegalArgumentException("No category with ID: " + id + " was found");
    }
    if (category == null || !category.isValid()) {
      throw new IllegalArgumentException("wrong data in request body");
    }
    if ((category.getId()) != id) {
      throw new IllegalArgumentException("Category ID in URL does not match the ID in JSON data");
    }

    category.setId(existingCategory.getId());
    this.categoryRepository.save(category);
  }

  /**
   * Removes a Category by its ID.
   * 
   * @param id the ID of the Category to be removed
   * @return true if the Category was successfully removed, false otherwise
   */
  private boolean removeCategory(int id) {
    boolean result = false;

    try {
      this.categoryRepository.delete(this.getCategoryById(id));
      result = true;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID");
    }

    return result;
  }
}