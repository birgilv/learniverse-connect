package no.ntnu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import no.ntnu.backend.model.Role;
import no.ntnu.backend.repository.RoleRepository;

/**
 * Service class for managing roles.
 */
@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  /**
   * Creates a new role and adds it to the repository.
   *
   * @param role the role to be created
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> create(Role role) {
    ResponseEntity<String> response;

    try {
      this.addRole(role);
      response = new ResponseEntity<>(role.toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException iae) {
      response = new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Retrieves all roles from the repository.
   *
   * @return a list of all roles
   */
  public List<Role> readAll() {
    return this.roleRepository.findAll();
  }

  /**
   * Retrieves a role by its ID.
   *
   * @param id the ID of the role to be retrieved
   * @return a ResponseEntity containing the role
   */
  public ResponseEntity<Role> readById(int id) {
    ResponseEntity<Role> response;

    Role role = this.getRoleById(id);
    if (role != null && role.isValid()) {
      response = new ResponseEntity<>(role, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Updates an existing role in the repository.
   *
   * @param id the ID of the role to be updated
   * @param role the new role data
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> update(int id, Role role) {
    ResponseEntity<String> response;

    try {
      this.updateRole(id, role);
      response = new ResponseEntity<>(role.toString(), HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Deletes a role by its ID.
   *
   * @param id the ID of the role to be deleted
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> delete(int id) {
    ResponseEntity<String> response;

    if (this.removeRole(id)) {
      response = new ResponseEntity<>("Role with ID: " + id + " has been deleted", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Retrieves a role by its ID.
   *
   * @param id the ID of the role to be retrieved
   * @return the role with the specified ID
   */
  private Role getRoleById(int id) {
    return this.roleRepository.findById(id).orElse(null);
  }

  /**
   * Adds a new role to the repository.
   *
   * @param role the role to be added
   * @throws IllegalArgumentException if the role is invalid
   */
  private void addRole(Role role) throws IllegalArgumentException {
    if (role == null || !role.isValid()) {
      throw new IllegalArgumentException("Role is invalid");
    }

    this.roleRepository.save(role);
  }

  /**
   * Updates an existing role in the repository.
   *
   * @param id the ID of the role to be updated
   * @param role the new role data
   * @throws IllegalArgumentException if the role is invalid
   */
  private void updateRole(int id, Role role) throws IllegalArgumentException {
    Role existingRole = this.getRoleById(id);

    if (existingRole == null) {
      throw new IllegalArgumentException("No role with ID: " + id + " was found");
    }
    if (role == null || !role.isValid()) {
      throw new IllegalArgumentException("Invalid data in request body");
    }
    if (role.getId() != id) {
      throw new IllegalArgumentException("Role ID in URL does not match the ID in JSON data");
    }

    role.setId(existingRole.getId());
    this.roleRepository.save(role);
  }

  /**
   * Removes a role by its ID.
   *
   * @param id the ID of the role to be removed
   * @return true if the role was successfully removed, false otherwise
   * @throws IllegalArgumentException if the ID is invalid
   */
  private boolean removeRole(int id) {
    boolean result = false;

    try {
      Role role = this.getRoleById(id);
      if (role != null && !role.getTitle().equals("ROLE_ADMIN")) {
        this.roleRepository.delete(role);
        result = true;
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID");
    }

    return result;
  }
}