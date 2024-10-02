package no.ntnu.backend.dto;

/**
 * Data transfer object for representing course details by each provider.
 * Contains information such as price, currency, course ID, and provider name.
 * Used for transferring course details between the backend and frontend.
 *
 * @author Group 01
 * @version 23.05.2024
 */
public class CourseByEachProviderDTO {

  private double price;
  private String currency;
  private Long courseId;
  private String providerName;

  /**
   * Retrieves the price of the course.
   *
   * @return The price of the course.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Retrieves the currency of the price.
   *
   * @return The currency of the price.
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Retrieves the ID of the course.
   *
   * @return The ID of the course.
   */
  public Long getCourseId() {
    return courseId;
  }

  /**
   * Retrieves the name of the provider offering the course.
   *
   * @return The name of the provider offering the course.
   */
  public String getProviderName() {
    return providerName;
  }

  /**
   * Sets the price of the course.
   *
   * @param price The price of the course.
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Sets the currency of the price.
   *
   * @param currency The currency of the price.
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Sets the ID of the course.
   *
   * @param courseId The ID of the course.
   */
  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  /**
   * Sets the name of the provider offering the course.
   *
   * @param providerName The name of the provider offering the course.
   */
  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }
}
