package no.ntnu.backend.model;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a tag for a course.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Entity
public class Tags {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  private String tag;

  /**
   * Default constructor for the Tags class.
   */
  public Tags() {
  }

  /**
   * Gets the unique identifier of the tag.
   *
   * @return the unique identifier of the tag
   */
  public int getId() {
    return this.id;
  }

  /**
   * Sets the unique identifier of the tag.
   *
   * @param id the new unique identifier of the tag
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the tag string.
   *
   * @return the tag string
   */
  public String getTag() {
    return this.tag;
  }

  /**
   * Sets the tag string.
   *
   * @param tag the new tag string
   */
  public void setTag(String tag) {
    this.tag = tag;
  }

  /**
   * Checks if the tag object is valid.
   *
   * @return true if the tag is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return this.tag != null && !this.tag.isBlank();
  }

  /**
   * Compares this tag with the specified object for equality.
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
    var that = (Tags) obj;
    return this.id == that.id &&
           Objects.equals(this.tag, that.tag);
  }

  /**
   * Returns a hash code value for the tag.
   *
   * @return a hash code value for the tag
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.tag);
  }

  /**
   * Returns a string representation of the tag.
   *
   * @return a string representation of the tag
   */
  @Override
  public String toString() {
    return "Tags[" +
        "id=" + this.id + ", " +
        "tag=" + this.tag + "]";
  }
}
