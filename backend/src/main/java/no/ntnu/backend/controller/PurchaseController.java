package no.ntnu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.backend.model.PurchaseData;
import no.ntnu.backend.service.EmailSenderService;

/**
 * Controller class for handling purchase requests.
 * Handles HTTP requests/responses for purchasing courses.
 */
@RestController
@RequestMapping("/api/purchased")
@CrossOrigin
public class PurchaseController {

  @Autowired
  private EmailSenderService emailSenderService;

  /**
   * Handles a purchase request and sends a confirmation email.
   *
   * @param purchaseData The purchase data containing recipient email, subject,
   *                     and text
   */
  @Operation(summary = "Handle Purchase", description = "Handle purchase request and send confirmation email.")
  @PostMapping()
  public void handlePurchase(@RequestBody PurchaseData purchaseData) {
    String email = purchaseData.getToEmail();
    String subject = purchaseData.getSubject();
    String text = "Thank you for your purchase!\n\n" + purchaseData.getText();

    // System.out.println("Sending email to: " + email);
    // System.out.println("Subject: " + subject);
    // System.out.println("Text: " + text);

    emailSenderService.sendEmail(email, subject, text);
  }
}
