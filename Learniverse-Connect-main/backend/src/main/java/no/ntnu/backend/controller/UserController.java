package no.ntnu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.backend.model.User;
import no.ntnu.backend.service.UserService;

import java.util.List;

/**
 * Controller class for managing user-related operations.
 * Handles HTTP requests/responses for user-related endpoints.
 * Provides APIs for creating, reading, updating, and deleting users.
 * Requires authentication and authorization for certain operations.
 * 
 * @author Grouo 01
 * @version 23.05.2024
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Creates a new user.
   * 
   * @param user The user object to be created.
   * @return ResponseEntity indicating the result of the operation.
   */
  @Operation(summary = "Creates a new user", description = "Creates a new user.")
  @PostMapping()
  public ResponseEntity<String> createUser(@RequestBody User user) {
    return this.userService.create(user);
  }

  /**
   * Retrieves all users.
   * 
   * @return List of all users.
   */
  @Operation(summary = "Retrieves all users", description = "Retrieves all users.")
  @GetMapping()
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<User> readAllUsers() {
    return this.userService.readAll();
  }

  /**
   * Retrieves a user by its ID.
   * 
   * @param id The ID of the user to retrieve.
   * @return ResponseEntity containing the user if found, or not found status.
   */
  @Operation(summary = "Retrieves a user by its ID", description = "Retrieves a user by its ID.")
  @GetMapping("/{id}")
  public ResponseEntity<User> readUserById(@PathVariable int id) {
    return this.userService.readById(id);
  }

  /**
   * Retrieves a user by its email.
   * 
   * @param email The email address of the user to retrieve.
   * @return ResponseEntity containing the user if found, or not found status.
   */
  @Operation(summary = "Retrieves a user by its Email", description = "Retrieves a user by its Email.")
  @GetMapping("/email/{email}")
  public ResponseEntity<User> readUserByEmail(@PathVariable String email) {
    return this.userService.readByEmail(email);
  }

  /**
   * Updates an existing user.
   * 
   * @param id   The ID of the user to update.
   * @param user The updated user object.
   * @return ResponseEntity indicating the result of the operation.
   */
  // @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Updates an existing user", description = "Updates an existing user.")
  @PutMapping("/{id}")
  public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
    return this.userService.update(id, user);
  }

  /**
   * Deletes a user by its ID.
   * 
   * @param id The ID of the user to delete.
   * @return ResponseEntity indicating the result of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Deletes a user by its ID", description = "Deletes a user by its ID.")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable int id) {
    return this.userService.delete(id);
  }
}
