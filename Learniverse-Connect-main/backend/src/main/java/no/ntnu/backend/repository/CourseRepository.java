package no.ntnu.backend.repository;

import no.ntnu.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Course entities.
 * This interface extends JpaRepository, providing methods for basic CRUD operations
 * and querying capabilities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> { 
}
