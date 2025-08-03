package com.email.email_service.core;

public record EmailRequest(String to, String subject, String body) {
    // This record class represents an email request with recipient, subject, and body.
    // It is used to encapsulate the data needed to send an email.
}
