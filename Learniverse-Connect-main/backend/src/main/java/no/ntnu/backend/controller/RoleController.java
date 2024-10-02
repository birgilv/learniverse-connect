package no.ntnu.backend.controller;

import java.util.List;

import no.ntnu.backend.repository.CourseTagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.ntnu.backend.model.Role;
import no.ntnu.backend.service.RoleService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for managing operations related to roles.
 * Handles HTTP requests/responses for role-related endpoints.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RoleController {

  private final RoleService roleService;

  /**
   * Constructor for RoleController.
   *
   * @param roleService The RoleService to be injected.
   */
  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  /**
   * Creates a new role.
   *
   * @param role The role object to be created.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Create a new role", description = "Creates a new role object in the system.")
  @PostMapping()
  public ResponseEntity<String> createRole(@RequestBody Role role) {
    return roleService.create(role);
  }

  /**
   * Retrieves all roles.
   *
   * @return List of Role containing information about all roles.
   */
  @Operation(summary = "Retrieves all roles", description = "Retrieves a list of all role objects in the system.")
  @GetMapping()
  public List<Role> readAllRoles() {
    return roleService.readAll();
  }

  /**
   * Retrieves a role by its ID.
   *
   * @param id The ID of the role to retrieve.
   * @return ResponseEntity containing the requested role, if found.
   */
  @Operation(summary = "Retrieve a role by ID", description = "Retrieves a specific role object based on its ID.")
  @GetMapping("/{id}")
  public ResponseEntity<Role> readRoleById(@PathVariable int id) {
    return roleService.readById(id);
  }

  /**
   * Updates an existing role.
   *
   * @param id   The ID of the role to be updated.
   * @param role The updated role object.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Update a role", description = "Updates an existing role object in the system.")
  @PutMapping("/{id}")
  public ResponseEntity<String> updateRole(@PathVariable int id, @RequestBody Role role) {
    return roleService.update(id, role);
  }

  /**
   * Deletes a role by its ID.
   *
   * @param id The ID of the role to be deleted.
   * @return ResponseEntity indicating the success/failure of the operation.
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Delete a role", description = "Deletes a role object from the system based on its ID.")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteRole(@PathVariable int id) {
    return roleService.delete(id);
  }
}