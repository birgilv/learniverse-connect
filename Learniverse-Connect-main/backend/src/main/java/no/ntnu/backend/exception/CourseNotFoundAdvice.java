package no.ntnu.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller advice to handle CourseNotFoundException globally.
 * It returns a JSON response with a custom error message when
 * CourseNotFoundException is thrown.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@ControllerAdvice
public class CourseNotFoundAdvice {

  /**
   * Exception handler method for CourseNotFoundException.
   * Returns a JSON response with a custom error message when
   * CourseNotFoundException is thrown.
   *
   * @param exception The CourseNotFoundException instance.
   * @return A map containing the error message.
   */
  @ResponseBody
  @ExceptionHandler(CourseNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> exceptionHandler(CourseNotFoundException exception) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("errorMessage", exception.getMessage());
    return errorMap;
  }
}