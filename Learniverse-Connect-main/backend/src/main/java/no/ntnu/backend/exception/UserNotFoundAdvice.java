package no.ntnu.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller advice to handle exceptions when a user is not found.
 * Provides custom error responses when a UserNotFoundException is thrown.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@ControllerAdvice
public class UserNotFoundAdvice {

    /**
     * Exception handler for UserNotFoundException.
     * Returns a custom error response with the error message.
     *
     * @param exception The UserNotFoundException to handle.
     * @return A Map containing the error message.
     */
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(UserNotFoundException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }
}
