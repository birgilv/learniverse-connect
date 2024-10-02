package no.ntnu.backend.dto;

/**
 * Data transfer object for authentication requests.
 * Contains user ID, email, and password for authentication purposes.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
public class AuthenticationRequest {

  private int id;
  private String email;
  private String password;

  public AuthenticationRequest() {
  }

  /**
   * Constructs an AuthenticationRequest object with the specified parameters.
   * 
   * @param id The ID of the user.
   * @param email The email of the user.
   * @param password The password of the user.
   */
  public AuthenticationRequest(int id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  /**
   * Retrieves the ID of the user.
   * 
   * @return The ID of the user.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Sets the ID of the user.
   * 
   * @param id The ID of the user.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Retrieves the email of the user.
   * 
   * @return The email of the user.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Sets the email of the user.
   * 
   * @param email The email of the user.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Retrieves the password of the user.
   * 
   * @return The password of the user.
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets the password of the user.
   * 
   * @param password The password of the user.
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
