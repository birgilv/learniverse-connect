package no.ntnu.backend.repository;

import no.ntnu.backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on User entities.
 * This interface extends JpaRepository, providing methods for basic CRUD
 * operations
 * and querying capabilities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  /**
   * Retrieves an Optional<User> based on the provided username.
   * 
   * @param username the username to search for
   * @return an Optional containing the User if found, otherwise empty
   */
  Optional<User> findByUsername(String username);

  /**
   * Retrieves an Optional<User> based on the provided email.
   * 
   * @param email the email to search for
   * @return an Optional containing the User if found, otherwise empty
   */
  Optional<User> findByEmail(String email);
}