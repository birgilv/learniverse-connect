package no.ntnu.backend.dto;

import java.sql.Date;

/**
 * Data transfer object for sign up requests.
 * Contains user sign up information such as email, password, registration date,
 * and username.
 * Used for transferring sign up details between the frontend and backend.
 *
 * @author Group 01
 * @version 23.05.2024
 */
public class SignupDTO {
  private final String email;
  private final String password;
  private final Date date;
  private final String username;

  /**
   * Constructs a SignupDTO object with the specified email, password,
   * registration date, and username.
   *
   * @param email    The email of the user signing up.
   * @param password The password of the user signing up.
   * @param date     The registration date of the user.
   * @param username The username of the user signing up.
   */
  public SignupDTO(String email, String password, Date date, String username) {
    this.email = email;
    this.password = password;
    this.date = date;
    this.username = username;
  }

  /**
   * Retrieves the email of the user signing up.
   *
   * @return The email of the user signing up.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Retrieves the password of the user signing up.
   *
   * @return The password of the user signing up.
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Retrieves the registration date of the user.
   *
   * @return The registration date of the user.
   */
  public Date getDate() {
    return date;
  }

  /**
   * Retrieves the username of the user signing up.
   *
   * @return The username of the user signing up.
   */
  public String getUsername() {
    return username;
  }
}
