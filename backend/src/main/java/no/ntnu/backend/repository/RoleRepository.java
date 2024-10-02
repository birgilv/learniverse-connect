package no.ntnu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.backend.model.Role;

/**
 * Repository interface for performing CRUD operations on Role entities.
 * This interface extends JpaRepository, providing methods for basic CRUD operations
 * and querying capabilities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    
    /**
     * Retrieves a role by its title.
     * 
     * @param title the title of the role
     * @return the role with the specified title, or null if not found
     */
    Role findOneByTitle(String title);

    /**
     * Retrieves a role by its ID.
     * 
     * @param id the ID of the role
     * @return the role with the specified ID, or null if not found
     */
    Role findOneById(int id);
}