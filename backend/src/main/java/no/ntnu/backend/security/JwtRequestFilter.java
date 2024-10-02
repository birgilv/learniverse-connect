package no.ntnu.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.JwtException;
import java.io.IOException;

/**
 * Filter class to intercept incoming requests and validate JWT tokens.
 * Extends OncePerRequestFilter to ensure it is only applied once per request.
 * Retrieves user details from the UserDetailsService and validates JWT tokens
 * using JwtUtil.
 * Sets authentication in the SecurityContextHolder for authenticated requests.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
  @Autowired
  private final UserDetailsService userDetailsService;
  @Autowired
  private final JwtUtil jwtUtil;

  @Autowired
  public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }

  /**
   * Method to filter incoming HTTP requests and validate JWT tokens.
   * Retrieves JWT token from the Authorization header, extracts email from token,
   * loads user details, and validates the token. If valid, sets authentication in
   * SecurityContextHolder.
   * Otherwise, logs error messages for invalid tokens.
   *
   * @param request     HTTP request
   * @param response    HTTP response
   * @param filterChain Filter chain
   * @throws javax.servlet.ServletException if servlet exception occurs
   * @throws IOException                    if an I/O error occurs
   */
  @Override
  protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
      jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
      throws jakarta.servlet.ServletException, IOException {
    System.out.println("Request " + request);
    String authorizationHeader = request.getHeader("Authorization");
    String email = null;
    String jwt = null;

    try {
      System.out.println("Header: " + authorizationHeader);
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        jwt = authorizationHeader.substring(7);
        email = this.jwtUtil.extractUsername(jwt);
      }

      if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
        System.out.println(
            "userdetails: " + userDetails.getUsername() + userDetails.getPassword() + userDetails.getAuthorities());
        if (this.jwtUtil.validateToken(jwt, userDetails)) {
          System.out.println("Valid");
          UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, (Object) null,
              userDetails.getAuthorities());
          upat.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(upat);
        }
      }
    } catch (JwtException var9) {
      logger.info("Error while parsing JWT token: " + var9.getMessage());
    }
    filterChain.doFilter(request, response);
  }
}