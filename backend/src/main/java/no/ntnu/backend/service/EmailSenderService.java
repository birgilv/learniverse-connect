package no.ntnu.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class for sending emails.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Service
public class EmailSenderService {

  @Autowired
  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String senderEmail;

  /**
   * Sends an email with the given details.
   * 
   * @param toEmail the recipient's email address
   * @param subject the subject of the email
   * @param text the text content of the email
   */
  public void sendEmail(String toEmail, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(senderEmail);
    message.setTo(toEmail);
    message.setSubject(subject);
    message.setText(text);

    System.out.println("Sending email to: " + toEmail);
    System.out.println("Subject: " + subject);
    System.out.println("Email content: " + text);

    System.out.println("Email message details: " + message);

    mailSender.send(message);
  }
}
