package no.ntnu.backend.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a provider of courses.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Entity
public class Provider {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;

  /**
   * Default constructor.
   */
  public Provider() {
  }

  /**
   * Gets the ID of the provider.
   * 
   * @return the ID of the provider
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the ID of the provider.
   * 
   * @param id the new ID of the provider
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the name of the provider.
   * 
   * @return the name of the provider
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the provider.
   * 
   * @param name the new name of the provider
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Validates the provider object.
   * 
   * @return true if the provider is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return this.name != null && !this.name.isBlank();
  }

  /**
   * Checks if this provider is equal to another object.
   * 
   * @param obj the object to compare to
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (Provider) obj;
    return this.id == that.id &&
           Objects.equals(this.name, that.name);
  }

  /**
   * Returns a hash code for this provider.
   * 
   * @return a hash code for this provider
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  /**
   * Returns a string representation of this provider.
   * 
   * @return a string representation of this provider
   */
  @Override
  public String toString() {
    return "Provider[" +
        "id=" + this.id + ", " +
        "name=" + this.name + ']';
  }
}