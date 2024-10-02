package no.ntnu.backend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

/**
 * Represents the association between a Course and a Tag.
 * This class is mapped to a database table using JPA annotations.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Entity
public class CourseTags {

  @EmbeddedId
  private CourseTagsId id;

  /**
   * Default constructor.
   */
  public CourseTags() {
  }

  /**
   * Constructor with course ID and tag ID.
   * 
   * @param courseId the ID of the course
   * @param tagId    the ID of the tag
   */
  public CourseTags(int courseId, int tagId) {
    this.id = new CourseTagsId(courseId, tagId);
  }

  /**
   * Gets the composite ID of the course-tag association.
   * 
   * @return the composite ID
   */
  public CourseTagsId getId() {
    return id;
  }

  /**
   * Sets the composite ID of the course-tag association.
   * 
   * @param id the composite ID to set
   */
  public void setId(CourseTagsId id) {
    this.id = id;
  }

  /**
   * Gets the course ID.
   * 
   * @return the course ID
   */
  public int getCourseId() {
    return id.getCourse_id();
  }

  /**
   * Sets the course ID.
   * 
   * @param courseId the course ID to set
   */
  public void setCourseId(int courseId) {
    id.setCourse_id(courseId);
  }

  /**
   * Gets the tag ID.
   * 
   * @return the tag ID
   */
  public int getTagId() {
    return id.getTag_id();
  }

  /**
   * Sets the tag ID.
   * 
   * @param tagId the tag ID to set
   */
  public void setTagId(int tagId) {
    id.setTag_id(tagId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    CourseTags that = (CourseTags) obj;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "CourseTags[" +
        "courseId=" + id.getCourse_id() + ", " +
        "tagId=" + id.getTag_id() + "]";
  }
}

/**
 * Represents the composite primary key for the CourseTags entity.
 */
@Embeddable
class CourseTagsId implements Serializable {
  private int course_id;
  private int tag_id;

  /**
   * Default constructor.
   */
  public CourseTagsId() {
  }

  /**
   * Constructor with course ID and tag ID.
   * 
   * @param courseId the ID of the course
   * @param tagId    the ID of the tag
   */
  public CourseTagsId(int courseId, int tagId) {
    this.course_id = courseId;
    this.tag_id = tagId;
  }

  /**
   * Gets the course ID.
   * 
   * @return the course ID
   */
  public int getCourse_id() {
    return course_id;
  }

  /**
   * Sets the course ID.
   * 
   * @param courseId the course ID to set
   */
  public void setCourse_id(int courseId) {
    this.course_id = courseId;
  }

  /**
   * Gets the tag ID.
   * 
   * @return the tag ID
   */
  public int getTag_id() {
    return tag_id;
  }

  /**
   * Sets the tag ID.
   * 
   * @param tagId the tag ID to set
   */
  public void setTag_id(int tagId) {
    this.tag_id = tagId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CourseTagsId that = (CourseTagsId) o;
    return course_id == that.course_id &&
        tag_id == that.tag_id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(course_id, tag_id);
  }

  @Override
  public String toString() {
    return "CourseTagsId[" +
        "course_id=" + course_id +
        ", tag_id=" + tag_id +
        ']';
  }
}
