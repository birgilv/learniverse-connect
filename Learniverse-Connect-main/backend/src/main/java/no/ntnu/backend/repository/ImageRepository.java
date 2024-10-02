package no.ntnu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.backend.model.Image;

/**
 * Repository interface for performing CRUD operations on Image entities.
 * This interface extends JpaRepository, providing methods for basic CRUD operations
 * and querying capabilities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
