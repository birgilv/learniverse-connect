package no.ntnu.backend.service;

import java.util.List;
import java.util.Optional;

import no.ntnu.backend.model.Role;
import no.ntnu.backend.model.User;
import no.ntnu.backend.repository.RoleRepository;
import no.ntnu.backend.repository.UserRepository;
import no.ntnu.backend.security.AccessUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Service class for managing users.
 * This class provides functionality related to user management,
 * including user creation, retrieval, updating, and deletion.
 * It also provides methods for loading user details by email
 * and retrieving the currently authenticated user.
 * 
 * @author Group 01
 * @version 22.05.2024
 */
@Service
public class UserService implements UserDetailsService {

  private static final int MIN_PASSWORD_LENGTH = 6;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private RoleService roleService;

  /**
   * Load user details by username (in this case, email).
   * 
   * @param email The email address of the user.
   * @return UserDetails object representing the user.
   * @throws UsernameNotFoundException If the user with the specified email is not
   *                                   found.
   */
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      return new AccessUserDetails(user.get());
    } else {
      throw new UsernameNotFoundException("User with email " + email + " not found");
    }
  }

  /**
   * Get the currently authenticated user from the security context.
   * 
   * @return The currently authenticated User object.
   */
  public User getSessionUser() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    String email = authentication.getName();
    return this.userRepository.findByUsername(email).orElse(null);
  }

  private boolean userExists(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  /**
   * Attempt to create a new user with the given email, password, and username.
   * 
   * @param email    The email address of the new user.
   * @param password The password of the new user.
   * @param username The username of the new user.
   * @return A string indicating success or an error message.
   */
  public String tryCreateNewUser(String email, String password, String username) {
    String errorMessage = checkEmailAndPassword(email, password);
    if (errorMessage == null) {
      createUser(email, password, username);
    }
    return errorMessage;
  }

  private String checkEmailAndPassword(String email, String password) {
    if (email.isEmpty()) {
      return "Email can't be empty";
    } else if (userExists(email)) {
      return "Email already taken";
    } else {
      return checkPasswordRequirements(password);
    }
  }

  private String checkPasswordRequirements(String password) {
    if (password == null || password.isEmpty()) {
      return "Password can't be empty";
    } else if (password.length() < MIN_PASSWORD_LENGTH) {
      return "Password must be at least " + MIN_PASSWORD_LENGTH + " characters";
    }
    return null;
  }

  /**
   * Create a new user with the given email, password, and username.
   * 
   * @param email    The email address of the new user.
   * @param password The password of the new user.
   * @param username The username of the new user.
   */
  private void createUser(String email, String password, String username) {
    Role userRole = roleRepository.findOneById(2);
    if (userRole != null) {
      User user = new User(email, createHash(password));
      user.addRole(userRole);
      user.setUsername(username);
      user.setStartDate(new java.sql.Date(new java.util.Date().getTime()));
      create(user);
    }
  }

  /**
   * Create a hash of the given password using BCrypt.
   * 
   * @param password The password to hash.
   * @return The hashed password.
   */
  private String createHash(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  /**
   * Create a new user.
   * 
   * @param user The user to create.
   * @return ResponseEntity containing the created user or an error message.
   */
  public ResponseEntity<String> create(User user) {
    try {
      addUser(user);
      return new ResponseEntity<>(user.toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException iae) {
      return new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Retrieve all users.
   * 
   * @return A list of all users.
   */
  public List<User> readAll() {
    return userRepository.findAll();
  }

  /**
   * Retrieve a user by their ID.
   * 
   * @param id The ID of the user to retrieve.
   * @return ResponseEntity containing the retrieved user or a not found response.
   */
  public ResponseEntity<User> readById(int id) {
    return userRepository.findById(id)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Retrieve a user by their email address.
   * 
   * @param email The email address of the user to retrieve.
   * @return ResponseEntity containing the retrieved user or a not found response.
   */
  public ResponseEntity<User> readByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Update a user's information.
   * 
   * @param id   The ID of the user to update.
   * @param user The updated user information.
   * @return ResponseEntity containing the updated user or an error message.
   */
  public ResponseEntity<String> update(int id, User user) {
    try {
      updateUser(id, user);
      return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Delete a user by their ID.
   * 
   * @param id The ID of the user to delete.
   * @return ResponseEntity indicating success or a not found response.
   */
  public ResponseEntity<String> delete(int id) {
    if (removeUser(id)) {
      return new ResponseEntity<>("User with ID: " + id + " has been deleted", HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  private Optional<User> getUserById(int id) {
    return userRepository.findById(id);
  }

  private Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  private void addUser(User user) throws IllegalArgumentException {
    if (user == null) {
      throw new IllegalArgumentException("User is invalid");
    }
    userRepository.save(user);
  }

  private void updateUser(int id, User user) throws IllegalArgumentException {
    Optional<User> existingUser = getUserById(id);
    if (existingUser.isEmpty()) {
      throw new IllegalArgumentException("No user with ID: " + id + " was found");
    }
    if (user == null || user.getId() != id) {
      throw new IllegalArgumentException("Invalid user data");
    }
    user.setStartDate(existingUser.get().getStartDate());
    user.setId(existingUser.get().getId());
    addUser(user);
  }

  private boolean removeUser(int id) {
    try {
      userRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID");
    }
  }
}