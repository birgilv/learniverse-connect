package no.ntnu.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import no.ntnu.backend.model.Course;
import no.ntnu.backend.model.FavoriteCourse;
import no.ntnu.backend.model.User;
import no.ntnu.backend.repository.FavoriteCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing favorite courses of users.
 * 
 * @version 23.05.2024
 * @autho Group 01
 */
@Service
public class FavoriteCourseService {

  private static final Logger logger = LoggerFactory.getLogger(FavoriteCourseService.class);

  private final FavoriteCourseRepository favoriteCourseRepository;

  @Autowired
  public FavoriteCourseService(FavoriteCourseRepository favoriteCourseRepository) {
    this.favoriteCourseRepository = favoriteCourseRepository;
  }

  /**
   * Retrieves all favorite courses for a specific user.
   * 
   * @param userId the ID of the user
   * @return a list of favorite courses for the user
   */
  public List<FavoriteCourse> getAllFavoriteCoursesByUserId(int userId) {
    logger.info("Fetching favorite courses for user with ID: {}", userId);
    List<FavoriteCourse> favoriteCourses = favoriteCourseRepository.findByUserId(userId);
    logger.info("Found {} favorite courses for user with ID: {}", favoriteCourses.size(), userId);
    return favoriteCourses;
  }

  /**
   * Adds a course to the user's list of favorite courses.
   * 
   * @param userId   the ID of the user
   * @param courseId the ID of the course to be added to favorites
   * @return true if the course was successfully added to favorites, false if the
   *         course was already in favorites
   */
  public boolean addFavoriteCourse(int userId, int courseId) {
    logger.info("Adding course with ID {} to favorites for user with ID: {}", courseId, userId);
    FavoriteCourse existingFavorite = favoriteCourseRepository.findByUserIdAndCourseId(userId, courseId);
    if (existingFavorite != null) {
      logger.warn("Course with ID {} is already favorited by user with ID: {}", courseId, userId);
      return false; // Course already favorited
    }
    FavoriteCourse favoriteCourse = new FavoriteCourse();
    favoriteCourse.setUser(new User(userId)); // You'll need to define a User class with an appropriate constructor
    favoriteCourse.setCourse(new Course(courseId)); // You'll need to define a Course class with an appropriate
                                                    // constructor
    favoriteCourseRepository.save(favoriteCourse);
    logger.info("Course with ID {} added to favorites for user with ID: {}", courseId, userId);
    return true;
  }

  /**
   * Removes a course from the user's list of favorite courses.
   * 
   * @param userId   the ID of the user
   * @param courseId the ID of the course to be removed from favorites
   * @return true if the course was successfully removed from favorites, false if
   *         the course was not found in favorites
   */
  public boolean removeFavoriteCourse(int userId, int courseId) {
    logger.info("Removing course with ID {} from favorites for user with ID: {}", courseId, userId);
    FavoriteCourse favoriteCourse = favoriteCourseRepository.findByUserIdAndCourseId(userId, courseId);
    if (favoriteCourse != null) {
      favoriteCourseRepository.delete(favoriteCourse);
      logger.info("Course with ID {} removed from favorites for user with ID: {}", courseId, userId);
      return true;
    }
    logger.warn("Course with ID {} not found in favorites for user with ID: {}", courseId, userId);
    return false; // Course not found in favorites
  }
}