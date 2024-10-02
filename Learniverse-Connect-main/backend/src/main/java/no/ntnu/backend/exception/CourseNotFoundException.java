package no.ntnu.backend.exception;

/**
 * Exception indicating that a course with a specific ID was not found.
 * Thrown to indicate that a requested course does not exist in the system.
 *
 * @author Group 01
 * @version 23.05.2024
 */
public class CourseNotFoundException extends RuntimeException {

  /**
   * Constructs a new CourseNotFoundException with the specified ID.
   *
   * @param id The ID of the course that was not found.
   */
  public CourseNotFoundException(Integer id) {
    super("Could not find the course with ID " + id);
  }
}
