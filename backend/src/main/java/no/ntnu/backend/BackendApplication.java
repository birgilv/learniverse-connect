package no.ntnu.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Main class to bootstrap and run the backend application.
 *
 * @author Group 01
 * @version 23.05.2024
 */

@SpringBootApplication(
		exclude = {SecurityAutoConfiguration.class }
)
public class BackendApplication {

	/**
	 * Main method to start the Spring Boot application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}