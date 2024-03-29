package com.anjaniy.funfactsbackend.models.dto;

import com.anjaniy.funfactsbackend.models.enums.EmailNotificationType;

public class EmailNotification {
    private String subject;
    private String recipient;
    private String body;
    private EmailNotificationType emailNotificationType;

    public EmailNotification() {
    }

    public EmailNotification(String subject, String recipient, String body, EmailNotificationType emailNotificationType) {
        this.subject = subject;
        this.recipient = recipient;
        this.body = body;
        this.emailNotificationType = emailNotificationType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailNotificationType getEmailNotificationType() {
        return emailNotificationType;
    }

    public void setEmailNotificationType(EmailNotificationType emailNotificationType) {
        this.emailNotificationType = emailNotificationType;
    }

    @Override
    public String toString() {
        return "EmailNotification{" +
            "subject='" + subject + '\'' +
            ", recipient='" + recipient + '\'' +
            ", body='" + body + '\'' +
            ", emailNotificationType=" + emailNotificationType +
            '}';
    }
}
