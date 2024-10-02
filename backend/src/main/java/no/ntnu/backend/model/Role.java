package no.ntnu.backend.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * Represents a role in the system, such as "admin" or "user".
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;

  /**
   * Default constructor for the Role class.
   */
  public Role() {
  }

  /**
   * Constructor for the Role class with a title.
   *
   * @param title the title/name of the role
   */
  public Role(String title) {
    this.title = title;
  }

  /**
   * Gets the unique identifier of the role.
   *
   * @return the unique identifier of the role
   */
  public int getId() {
    return this.id;
  }

  /**
   * Sets the unique identifier of the role.
   *
   * @param id the new unique identifier of the role
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the title/name of the role.
   *
   * @return the title/name of the role
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the title/name of the role.
   *
   * @param title the new title/name of the role
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Checks if the role object is valid.
   *
   * @return true if the role is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return this.title != null && !this.title.isBlank();
  }

  /**
   * Compares this role with the specified object for equality.
   *
   * @param obj the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (Role) obj;
    return this.id == that.id &&
        Objects.equals(this.title, that.title);
  }

  /**
   * Returns a hash code value for the role.
   *
   * @return a hash code value for the role
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.title);
  }

  /**
   * Returns a string representation of the role.
   *
   * @return a string representation of the role
   */
  @Override
  public String toString() {
    return "Role[" +
        "id=" + this.id + ", " +
        "title=" + this.title + ']';
  }
}