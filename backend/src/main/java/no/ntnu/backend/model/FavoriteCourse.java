package no.ntnu.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a favorite course for a user.
 * This class is mapped to the "favorite_courses" table in the database.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Entity
@Table(name = "favorite_courses") // Define the name of the table
public class FavoriteCourse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;

  /**
   * Default constructor.
   */
  public FavoriteCourse() {
  }

  /**
   * Gets the ID of the favorite course record.
   * 
   * @return the ID of the favorite course record
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the ID of the favorite course record.
   * 
   * @param id the new ID of the favorite course record
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the user who favorited the course.
   * 
   * @return the user who favorited the course
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the user who favorited the course.
   * 
   * @param user the new user who favorited the course
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Gets the course that was favorited.
   * 
   * @return the course that was favorited
   */
  public Course getCourse() {
    return course;
  }

  /**
   * Sets the course that was favorited.
   * 
   * @param course the new course that was favorited
   */
  public void setCourse(Course course) {
    this.course = course;
  }
}
