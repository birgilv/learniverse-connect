package no.ntnu.backend.exception;

/**
 * Exception class for when a user is not found.
 * Inherits from RuntimeException.
 * Thrown when attempting to retrieve a user with an unknown ID.
 *
 * @author Group 01
 * @version 23.05.2024
 */
public class UserNotFoundException extends RuntimeException {

  /**
   * Constructs a UserNotFoundException with the specified user ID.
   *
   * @param id The ID of the user that was not found.
   */
  public UserNotFoundException(Long id) {
    super("Could not find the user with ID " + id);
  }
}
