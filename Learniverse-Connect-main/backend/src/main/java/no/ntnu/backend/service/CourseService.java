package no.ntnu.backend.service;

import java.util.List;
import java.util.Optional;

import no.ntnu.backend.exception.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import no.ntnu.backend.model.Course;
import no.ntnu.backend.repository.CourseRepository;

/**
 * Service class for managing Course entities.
 * 
 * @version 29.03.2024
 */
@Service
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  /**
   * Creates a new course.
   * 
   * @param course the course to be created
   * @return ResponseEntity with the created course string and HTTP status
   */
  public ResponseEntity<String> create(Course course) {
    ResponseEntity<String> response;

    try {
      this.addCourse(course);
      response = new ResponseEntity<>(course.toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException iae) {
      response = new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Retrieves all courses.
   * 
   * @return a list of all courses
   */
  public List<Course> readAll() {
    return this.courseRepository.findAll();
  }

  /**
   * Retrieves a course by its ID.
   * 
   * @param id the ID of the course
   * @return ResponseEntity with the course and HTTP status
   */
  public ResponseEntity<Course> readById(int id) {
    ResponseEntity<Course> response;

    Course course = this.getCourseById(id);
    if (course != null && course.isValid()) {
      response = new ResponseEntity<>(course, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Updates an existing course.
   * 
   * @param id the ID of the course to be updated
   * @param course the new course data
   * @return ResponseEntity with the updated course string and HTTP status
   */
  public ResponseEntity<String> update(@PathVariable int id, @RequestBody Course course) {
    ResponseEntity<String> response;

    try {
      this.updateCourse(id, course);
      response = new ResponseEntity<>(course.toString(), HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Deletes a course by its ID.
   * 
   * @param id the ID of the course to be deleted
   * @return ResponseEntity with a message and HTTP status
   */
  public ResponseEntity<String> delete(@PathVariable int id) {
    ResponseEntity<String> response;

    if (this.removeCourse(id)) {
      response = new ResponseEntity<>("Course with ID: " + id + " has been deleted", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Retrieves a course by its ID.
   * 
   * @param id the ID of the course
   * @return the course entity
   * @throws CourseNotFoundException if the course is not found
   */
  private Course getCourseById(int id) {
    if (!courseRepository.findById(id).isPresent()) {
      throw new CourseNotFoundException(id);
    }
    return this.courseRepository.findById(id).get();
  }

  /**
   * Adds a new course.
   * 
   * @param course the course to be added
   * @throws IllegalArgumentException if the course is invalid
   */
  private void addCourse(Course course) throws IllegalArgumentException {
    if (course == null || !course.isValid()) {
      throw new IllegalArgumentException("Course is invalid");
    }

    this.courseRepository.save(course);
  }

  /**
   * Updates an existing course.
   * 
   * @param id the ID of the course to be updated
   * @param course the new course data
   * @throws IllegalArgumentException if the course is invalid or ID mismatch
   */
  private void updateCourse(int id, Course course) throws IllegalArgumentException {
    Course existingCourse = this.getCourseById(id);

    if (existingCourse == null) {
      throw new IllegalArgumentException("No course with ID: " + id + " was found");
    }
    if (course == null || !course.isValid()) {
      throw new IllegalArgumentException("wrong data in request body");
    }
    if ((course.getId()) != id) {
      throw new IllegalArgumentException("Course ID in URL does not match the ID in JSON data");
    }
    
    course.setId(existingCourse.getId());
    this.courseRepository.save(course);
  }

  /**
   * Removes a course by its ID.
   * 
   * @param id the ID of the course to be removed
   * @return true if the course was successfully removed, false otherwise
   * @throws CourseNotFoundException if the course is not found
   */
  private boolean removeCourse(int id) {
    try {
      Optional<Course> courseToRemove = this.courseRepository.findById(id);
      if (!courseToRemove.isPresent()) {
        throw new CourseNotFoundException(id);
      }
      this.courseRepository.deleteById(id);
      return courseToRemove.isPresent();
    } catch (Exception e) {
      throw new CourseNotFoundException(id);
    }
  }
}