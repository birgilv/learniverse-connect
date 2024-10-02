package no.ntnu.backend.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Represents a category which can have multiple courses associated with it.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String subject;

  @OneToMany(mappedBy = "category")
  private List<Course> courses;

  /**
   * Default constructor for creating a new Category.
   */
  public Category() {
  }

  /**
   * Gets the ID of the category.
   * 
   * @return the ID of the category.
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the ID of the category.
   * 
   * @param id the new ID of the category.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the subject of the category.
   * 
   * @return the subject of the category.
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Sets the subject of the category.
   * 
   * @param subject the new subject of the category.
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * Checks if the category is valid. A category is considered valid if its
   * subject is not blank and not null.
   * 
   * @return true if the category is valid, false otherwise.
   */
  @JsonIgnore
  public boolean isValid() {
    return !this.subject.isBlank() && this.subject != null;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * 
   * @param obj the reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (Category) obj;
    return this.id == that.id &&
        Objects.equals(this.subject, that.subject) &&
        Objects.equals(this.courses, that.courses);
  }

  /**
   * Returns a hash code value for the object.
   * 
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.subject, this.courses);
  }

  /**
   * Returns a string representation of the object.
   * 
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "Category[" +
        "id=" + this.id +
        ", subject=" + this.subject +
        ", courses=" + this.courses + ']';
  }
}