package no.ntnu.backend.repository;

import no.ntnu.backend.model.FavoriteCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on FavoriteCourse entities.
 * This interface extends JpaRepository, providing methods for basic CRUD operations
 * and querying capabilities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Repository
public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Integer> {

    /**
     * Retrieves all FavoriteCourse entities associated with the given user ID.
     * 
     * @param userId the ID of the user
     * @return a list of FavoriteCourse entities associated with the given user ID
     */
    List<FavoriteCourse> findByUserId(int userId);

    /**
     * Retrieves the FavoriteCourse entity associated with the given user ID and course ID.
     * 
     * @param userId the ID of the user
     * @param courseId the ID of the course
     * @return the FavoriteCourse entity associated with the given user ID and course ID, or null if not found
     */
    FavoriteCourse findByUserIdAndCourseId(int userId, int courseId);
}
