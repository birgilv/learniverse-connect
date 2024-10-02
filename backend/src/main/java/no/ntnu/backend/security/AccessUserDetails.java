package no.ntnu.backend.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import no.ntnu.backend.model.Role;
import no.ntnu.backend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserDetails implementation for custom user authentication and authorization.
 * Wraps the User entity to provide user details to Spring Security.
 *
 * @author Group 01
 * @version 23.05.2024
 */
public class AccessUserDetails implements UserDetails {
  private final String email;
  private final String password;
  private final boolean isActive;
  private final Set<GrantedAuthority> authorities = new HashSet<>();

  /**
   * Constructs an AccessUserDetails object using the provided User entity.
   *
   * @param user The User entity containing user details.
   */
  public AccessUserDetails(User user) {
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.isActive = user.isActive();
    this.convertRoles(user.getRoles());
  }

  /**
   * Converts the roles associated with the user to Spring Security
   * GrantedAuthority objects.
   *
   * @param roles The set of roles associated with the user.
   */
  private void convertRoles(Set<Role> roles) {
    this.authorities.clear();
    for (Role role : roles) {
      this.authorities.add(new SimpleGrantedAuthority(role.getTitle()));
    }
  }

  /**
   * Retrieves the authorities granted to the user.
   *
   * @return A collection of granted authorities.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  /**
   * Retrieves the password used to authenticate the user.
   *
   * @return The user's password.
   */
  @Override
  public String getPassword() {
    return this.password;
  }

  /**
   * Retrieves the username used to authenticate the user.
   *
   * @return The user's username.
   */
  @Override
  public String getUsername() {
    return this.email;
  }

  /**
   * Indicates whether the user's account has expired.
   *
   * @return true if the user's account is valid (i.e., not expired), false
   *         otherwise.
   */
  @Override
  public boolean isAccountNonExpired() {
    return this.isActive;
  }

  /**
   * Indicates whether the user is locked or unlocked.
   *
   * @return true if the user is not locked, false otherwise.
   */
  @Override
  public boolean isAccountNonLocked() {
    return this.isActive;
  }

  /**
   * Indicates whether the user's credentials (password) has expired.
   *
   * @return true if the user's credentials are valid (i.e., not expired), false
   *         otherwise.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return this.isActive;
  }

  /**
   * Indicates whether the user is enabled or disabled.
   *
   * @return true if the user is enabled, false otherwise.
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}