package no.ntnu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import no.ntnu.backend.model.Provider;
import no.ntnu.backend.repository.ProviderRepository;

/**
 * Service class for managing providers.
 */
@Service
public class ProviderService {

  @Autowired
  private ProviderRepository providerRepository;

  /**
   * Creates a new provider and adds it to the repository.
   *
   * @param provider the provider to be created
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> create(Provider provider) {
    ResponseEntity<String> response;

    try {
      this.addProvider(provider);
      response = new ResponseEntity<>(provider.toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException iae) {
      response = new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Retrieves all providers from the repository.
   *
   * @return a list of all providers
   */
  public List<Provider> readAll() {
    return this.providerRepository.findAll();
  }

  /**
   * Retrieves a provider by its ID.
   *
   * @param id the ID of the provider to be retrieved
   * @return a ResponseEntity containing the provider
   */
  public ResponseEntity<Provider> readById(int id) {
    ResponseEntity<Provider> response;

    Provider provider = this.getProviderById(id);
    if (provider != null && provider.isValid()) {
      response = new ResponseEntity<>(provider, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  /**
   * Updates an existing provider in the repository.
   *
   * @param id the ID of the provider to be updated
   * @param provider the new provider data
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> update(int id, Provider provider) {
    ResponseEntity<String> response;

    try {
      this.updateProvider(id, provider);
      response = new ResponseEntity<>(provider.toString(), HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Deletes a provider by its ID.
   *
   * @param id the ID of the provider to be deleted
   * @return a ResponseEntity containing the result of the operation
   */
  public ResponseEntity<String> delete(int id) {
    ResponseEntity<String> response;

    if (this.removeProvider(id)) {
      response = new ResponseEntity<>("Provider with ID: " + id + " has been deleted", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return response;
  } 

  /**
   * Retrieves a provider by its ID.
   *
   * @param id the ID of the provider to be retrieved
   * @return the provider with the specified ID
   */
  private Provider getProviderById(int id) {
    return this.providerRepository.findById(id).orElse(null);
  }

  /**
   * Adds a new provider to the repository.
   *
   * @param provider the provider to be added
   * @throws IllegalArgumentException if the provider is invalid
   */
  private void addProvider(Provider provider) throws IllegalArgumentException {
    if (provider == null || !provider.isValid()) {
      throw new IllegalArgumentException("Provider is invalid");
    }
    this.providerRepository.save(provider);
  }

  /**
   * Updates an existing provider in the repository.
   *
   * @param id the ID of the provider to be updated
   * @param provider the new provider data
   * @throws IllegalArgumentException if the provider is invalid
   */
  private void updateProvider(int id, Provider provider) throws IllegalArgumentException {
    Provider existingProvider = this.getProviderById(id);

    if (existingProvider == null) {
      throw new IllegalArgumentException("No provider with ID: " + id + " was found");
    }
    if (provider == null || !provider.isValid()) {
      throw new IllegalArgumentException("Invalid data in request body");
    }
    if (provider.getId() != id) {
      throw new IllegalArgumentException("Provider ID in URL does not match the ID in JSON data");
    }

    provider.setId(existingProvider.getId());
    this.providerRepository.save(provider);
  }

  /**
   * Removes a provider by its ID.
   *
   * @param id the ID of the provider to be removed
   * @return true if the provider was successfully removed, false otherwise
   * @throws IllegalArgumentException if the ID is invalid
   */
  private boolean removeProvider(int id) {
    boolean result = false;

    try {
      Provider provider = this.getProviderById(id);
      if (provider != null) {
        this.providerRepository.delete(provider);
        result = true;
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID", e);
    }

    return result;
  }
}