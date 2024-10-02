package no.ntnu.backend.model;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

/**
 * Represents a user in the system.
 *
 * @version 30.03.2024
 * @author Group 01
 */
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;
  private Date startDate;
  private String email;
  private String password;
  private boolean active = true;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
      @JoinColumn(name = "role_id") })
  private Set<Role> roles = new LinkedHashSet();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<FavoriteCourse> favoriteCourses;

  /**
   * Default constructor for the User class.
   */
  public User() {
  }

  /**
   * Constructor with email and password for the User class.
   * 
   * @param email    the email of the user
   * @param password the password of the user
   */
  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * Constructor with id for the User class.
   * 
   * @param id the ID of the user
   */
  public User(int id) {
    this.id = id;
  }

  /**
   * Gets the roles associated with the user.
   * 
   * @return the roles associated with the user
   */
  public Set<Role> getRoles() {
    return this.roles;
  }

  /**
   * Sets the roles associated with the user.
   * 
   * @param roles the roles to be associated with the user
   */
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  /**
   * Adds a role to the user.
   * 
   * @param role the role to be added
   */
  public void addRole(Role role) {
    this.roles.add(role);
  }

  /**
   * Gets the unique identifier of the user.
   * 
   * @return the unique identifier of the user
   */
  public int getId() {
    return this.id;
  }

  /**
   * Sets the unique identifier of the user.
   * 
   * @param id the new unique identifier of the user
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the username of the user.
   * 
   * @return the username of the user
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Sets the username of the user.
   * 
   * @param username the new username of the user
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the start date of the user.
   * 
   * @return the start date of the user
   */
  public Date getStartDate() {
    return this.startDate;
  }

  /**
   * Sets the start date of the user.
   * 
   * @param startDate the new start date of the user
   */
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  /**
   * Gets the email of the user.
   * 
   * @return the email of the user
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Sets the email of the user.
   * 
   * @param email the new email of the user
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the password of the user.
   * 
   * @return the password of the user
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets the password of the user.
   * 
   * @param password the new password of the user
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /*
   * @JsonIgnore
   * public boolean isValid() {
   * return //this.id > 0 &&
   * this.roleId > 0;
   * }
   */

  /**
   * Sets the active status of the user.
   * 
   * @param active the new active status of the user
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Returns the active state of the user.
   *
   * @return Returns {@code true} if the user is active. {@code false} otherwise
   */
  public boolean isActive() {
    return this.active;
  }

  /**
   * Compares this user with the specified object for equality.
   *
   * @param obj the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (User) obj;
    return this.id == that.id &&
        Objects.equals(this.username, that.username) &&
        Objects.equals(this.startDate, that.startDate) &&
        Objects.equals(this.email, that.email) &&
        Objects.equals(this.password, that.password) &&
        this.active == that.active &&
        this.roles.equals(that.roles);
  }

  /**
   * Returns a hash code value for the user.
   *
   * @return a hash code value for the user
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.username, this.startDate, this.email, this.password, this.active, this.roles);
  }

  /**
   * Returns a string representation of the user.
   *
   * @return a string representation of the user
   */
  @Override
  public String toString() {
    return "User[" +
        "id=" + this.id + ", " +
        "username=" + this.username + ", " +
        "startDate=" + this.startDate + ", " +
        "email=" + this.email + ", " +
        "password=" + this.password + ", " +
        "active=" + this.active + ", " +
        "roles=" + this.roles + "]";
  }
}
