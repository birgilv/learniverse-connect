package no.ntnu.backend.security;

//import no.ntnu.backend.config.CorsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for security settings in the backend application.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

  private final UserDetailsService userDetailsService;
  private final JwtRequestFilter jwtRequestFilter;

  public SecurityConfiguration(UserDetailsService userDetailsService,
      JwtRequestFilter jwtRequestFilter) {
    this.userDetailsService = userDetailsService;
    this.jwtRequestFilter = jwtRequestFilter;
  }

  /**
   * This method will be called automatically by the framework to find the
   * authentication to use.
   * Here we tell that we want to load users from a database
   *
   * @param auth Authentication builder
   * @throws Exception When user service is not found
   */

  // private static final Logger logger =
  // LoggerFactory.getLogger(CorsConfig.class);
  @Autowired
  protected void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  /**
   * This method will be called automatically by the framework to find the
   * authentication to use.
   *
   * @param http HttpSecurity setting builder
   * @throws Exception When security configuration fails
   */
  @Bean
  public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
    // logger.info("Using SecurityFilterChain...");
    http
        // Disable CSRF and CORS checks. Without this it will be hard to make automated
        // tests.
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        // Allow HTTP OPTIONS requests - CORS pre-flight requests
        .authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.OPTIONS).permitAll())
        // Admin URL is accessible only for Admin role
        .authorizeHttpRequests((auth) -> auth.requestMatchers("api/admin/**").hasRole("ADMIN"))
        // Users URL is accessible only for User role
        // .authorizeHttpRequests((auth) ->
        // auth.requestMatchers("api/users").hasAnyRole("USER", "ADMIN"))
        .authorizeHttpRequests((auth) -> auth.requestMatchers("api/users/**").hasAnyRole("USER", "ADMIN"))
        // Other URLs are accessible by everyone.

        .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())

        // Enable stateless session policy
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // Enable our JWT authentication filter
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  /**
   * Provides an instance of PasswordEncoder bean.
   *
   * @return BCryptPasswordEncoder instance
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Provides an instance of AuthenticationManager bean.
   *
   * @param config AuthenticationConfiguration instance
   * @return AuthenticationManager instance
   * @throws Exception If there's an issue getting the authentication manager
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }
}