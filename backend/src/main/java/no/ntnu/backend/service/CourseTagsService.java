package no.ntnu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.backend.model.CourseTags;
import no.ntnu.backend.repository.CourseTagsRepository;

/**
 * Service class for managing CourseTags entities.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Service
public class CourseTagsService {

  @Autowired
  private CourseTagsRepository courseTagsRepository;

  /**
   * Retrieves all distinct CourseTags.
   * 
   * @return a list of all distinct CourseTags
   */
  public List<CourseTags> findAllDistinct3() {
    return courseTagsRepository.findAll();
  }
}