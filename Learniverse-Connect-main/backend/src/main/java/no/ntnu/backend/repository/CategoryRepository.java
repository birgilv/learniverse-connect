package no.ntnu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.backend.model.Category;

/**
 * Repository interface for performing CRUD operations on Category entities.
 * This interface extends JpaRepository, providing methods for basic CRUD
 * operations
 * and querying capabilities.
 * 
 * @version 29.03.2024
 * @author Group 01
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}