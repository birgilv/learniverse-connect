package no.ntnu.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import no.ntnu.backend.model.CourseProvider;

/**
 * Repository interface for performing CRUD operations on CourseProvider
 * entities.
 * This interface extends JpaRepository, providing methods for basic CRUD
 * operations
 * and querying capabilities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
public interface CourseProviderRepository extends JpaRepository<CourseProvider, Long> {

  /**
   * Retrieves all CourseProvider entities.
   * 
   * @return a list of all CourseProvider entities
   */
  List<CourseProvider> findAll();

  /**
   * Retrieves CourseProvider entities by the given courseId.
   * 
   * @param courseId the ID of the course
   * @return a list of CourseProvider entities associated with the given courseId
   */
  @Query("SELECT cp FROM CourseProvider cp WHERE cp.id.courseId = :courseId")
  List<CourseProvider> findByCourseId(@Param("courseId") Long courseId);
}
