package no.ntnu.backend.model;

/**
 * Represents data for a purchase, including information about the recipient
 * email, subject, and text.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
public class PurchaseData {

  private String toEmail;
  private String subject;
  private String text;

  /**
   * Gets the email address of the recipient.
   *
   * @return the email address of the recipient
   */
  public String getToEmail() {
    return toEmail;
  }

  /**
   * Sets the email address of the recipient.
   *
   * @param toEmail the email address of the recipient
   */
  public void setToEmail(String toEmail) {
    this.toEmail = toEmail;
  }

  /**
   * Gets the subject of the purchase email.
   *
   * @return the subject of the purchase email
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Sets the subject of the purchase email.
   *
   * @param subject the subject of the purchase email
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * Gets the text content of the purchase email.
   *
   * @return the text content of the purchase email
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text content of the purchase email.
   *
   * @param text the text content of the purchase email
   */
  public void setText(String text) {
    this.text = text;
  }
}
