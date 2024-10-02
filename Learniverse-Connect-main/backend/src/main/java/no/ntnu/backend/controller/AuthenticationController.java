package no.ntnu.backend.controller;
import no.ntnu.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import no.ntnu.backend.dto.AuthenticationRequest;
import no.ntnu.backend.dto.AuthenticationResponse;
import no.ntnu.backend.dto.SignupDTO;
import no.ntnu.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication-related HTTP requests.
 * This class provides endpoints for user authentication and signup.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@CrossOrigin
@RestController
@RequestMapping ("/api/authenticate")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * HTTP POST request to /authenticate.
     *
     * @param authenticationRequest The request JSON object containing username and password
     * @return OK + JWT token; Or UNAUTHORIZED
     */
    @PostMapping()
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        logger.info("Received authentication request for email: {}", authenticationRequest.getEmail());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(

                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
            logger.info("Authentication successful for email: {}", authenticationRequest.getEmail());
        } catch (BadCredentialsException e) {
            logger.error("Authentication failed for email: {}", authenticationRequest.getEmail(), e);
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        } catch(Exception e){
            logger.error("E");
        }
        final UserDetails userDetails = userService.loadUserByUsername(
                authenticationRequest.getEmail());
        logger.info("USERDETAILS :" + String.valueOf(userDetails.getUsername()));
        final String jwt = jwtUtil.generateToken(userDetails);
        logger.info("JWT token generated successfully for email: {}", authenticationRequest.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /**
     * HTTP POST request to /authenticate/signup.
     * Registers a new user with the provided details.
     * 
     * @param signupData The request JSON object containing user signup details
     * @return ResponseEntity indicating successful user creation or error message if failed
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signupProcess(@RequestBody SignupDTO signupData) {
        logger.info("Received signup request for email: {}", signupData.getEmail());
        String errorMessage = userService.tryCreateNewUser(signupData.getEmail(), signupData.getPassword(), signupData.getUsername());
        if (errorMessage == null) {
            logger.info("User created successfully for email: {}", signupData.getEmail());
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } else {
            logger.error("Error creating user for email: {}: {}", signupData.getEmail(), errorMessage);
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
