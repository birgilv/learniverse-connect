package no.ntnu.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

/**
 * Represents an image with its metadata.
 * This class is mapped to a database table using JPA annotations.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Entity
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Lob
  private byte[] data;

  private String fileName;
  private String contentType;
  private String alt;

  /**
   * Default constructor.
   */
  public Image() {
  }

  /**
   * Gets the ID of the image.
   * 
   * @return the ID of the image
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the ID of the image.
   * 
   * @param id the new ID of the image
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the binary data of the image.
   * 
   * @return the binary data of the image
   */
  public byte[] getData() {
    return data;
  }

  /**
   * Sets the binary data of the image.
   * 
   * @param data the new binary data of the image
   */
  public void setData(byte[] data) {
    this.data = data;
  }

  /**
   * Gets the content type of the image.
   * 
   * @return the content type of the image
   */
  public String getContentType() {
    return contentType;
  }

  /**
   * Sets the content type of the image.
   * 
   * @param contentType the new content type of the image
   */
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /**
   * Gets the file name of the image.
   * 
   * @return the file name of the image
   */
  public String getFileName() {
    return this.fileName;
  }

  /**
   * Sets the file name of the image.
   * 
   * @param fileName the new file name of the image
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Gets the alternative text for the image.
   * 
   * @return the alternative text for the image
   */
  public String getAlt() {
    return this.alt;
  }

  /**
   * Sets the alternative text for the image.
   * 
   * @param alt the new alternative text for the image
   */
  public void setAlt(String alt) {
    this.alt = alt;
  }

  /**
   * Validates the image object.
   * 
   * @return true if the image is valid, false otherwise
   */
  public boolean isValid() {
    return this.contentType != null && !this.contentType.isBlank()
        && this.id > 0;
  }
}