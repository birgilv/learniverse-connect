package no.ntnu.backend.repository;

import java.util.List;

import no.ntnu.backend.model.CourseTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on CourseTags entities.
 * This interface extends JpaRepository, providing methods for basic CRUD operations
 * and querying capabilities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Repository
public interface CourseTagsRepository extends JpaRepository<CourseTags, Integer> {
    
    /**
     * Retrieves all CourseTags entities.
     * 
     * @return a list of all CourseTags entities
     */
    List<CourseTags> findAll();
}
