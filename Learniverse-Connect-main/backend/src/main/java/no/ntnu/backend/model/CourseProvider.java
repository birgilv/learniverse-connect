package no.ntnu.backend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

/**
 * Represents the association between a Course and a Provider with additional
 * attributes like price and currency.
 * This class is mapped to a database table using JPA annotations.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Entity
public class CourseProvider {

    @EmbeddedId
    private CourseProviderId id;

    private double price;
    private String currency;

    /**
     * Default constructor.
     */
    public CourseProvider() {
    }

    /**
     * Constructor with course ID, provider ID, price, and currency.
     * 
     * @param courseId   the ID of the course
     * @param providerId the ID of the provider
     * @param price      the price of the course provided
     * @param currency   the currency of the price
     */
    public CourseProvider(Long courseId, int providerId, double price, String currency) {
        this.id = new CourseProviderId(courseId, providerId);
        this.price = price;
        this.currency = currency;
    }

    /**
     * Gets the price of the course.
     * 
     * @return the price of the course
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the course.
     * 
     * @param price the new price of the course
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the currency of the price.
     * 
     * @return the currency of the price
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * Sets the currency of the price.
     * 
     * @param currency the new currency of the price
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets the ID of the course.
     * 
     * @return the course ID
     */
    public Long getCourseId() {
        return this.id.getCourseId();
    }

    /**
     * Gets the ID of the provider.
     * 
     * @return the provider ID
     */
    public int getProviderId() {
        return this.id.getProviderId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        CourseProvider that = (CourseProvider) obj;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, currency);
    }

    @Override
    public String toString() {
        return "CourseProvider{" +
                "courseId=" + id.getCourseId() +
                ", providerId=" + id.getProviderId() +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

    /**
     * Represents the composite primary key for the CourseProvider entity.
     */
    @Embeddable
    public static class CourseProviderId implements Serializable {

        @Column(name = "course_id", nullable = false)
        private Long courseId;

        @Column(name = "provider_id", nullable = false)
        private int providerId;

        /**
         * Default constructor.
         */
        public CourseProviderId() {
        }

        /**
         * Constructor with course ID and provider ID.
         * 
         * @param courseId   the ID of the course
         * @param providerId the ID of the provider
         */
        public CourseProviderId(Long courseId, int providerId) {
            this.courseId = courseId;
            this.providerId = providerId;
        }

        /**
         * Gets the course ID.
         * 
         * @return the course ID
         */
        public Long getCourseId() {
            return courseId;
        }

        /**
         * Gets the provider ID.
         * 
         * @return the provider ID
         */
        public int getProviderId() {
            return providerId;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            CourseProviderId that = (CourseProviderId) obj;
            return providerId == that.providerId &&
                    Objects.equals(courseId, that.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(courseId, providerId);
        }

        @Override
        public String toString() {
            return "CourseProviderId{" +
                    "courseId=" + courseId +
                    ", providerId=" + providerId +
                    '}';
        }
    }
}
