package com.email.email_service.conrtrollers;

import com.email.email_service.application.EmailSenderService;
import com.email.email_service.core.EmailRequest;
import com.email.email_service.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try{
            this.emailSenderService.SendEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("Email sent successfully");
        }catch (EmailServiceException ex){
            return ResponseEntity.status(500).body("Failed to send email: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
