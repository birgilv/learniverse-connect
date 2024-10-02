package no.ntnu.backend.dto;

import java.io.Serializable;

/**
 * Data transfer object for authentication responses.
 * Contains a JWT token.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
public class AuthenticationResponse implements Serializable {
    private final String jwt;

    /**
     * Constructs an AuthenticationResponse object with the specified JWT token.
     * 
     * @param jwt The JWT token.
     */
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Retrieves the JWT token.
     * 
     * @return The JWT token.
     */
    public String getJwt() {
        return this.jwt;
    }
}
