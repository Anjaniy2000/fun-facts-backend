package com.anjaniy.funfactsbackend.services;

import com.anjaniy.funfactsbackend.api.MailAPI;
import com.anjaniy.funfactsbackend.models.dto.EmailNotification;
import com.anjaniy.funfactsbackend.models.enums.EmailNotificationType;
import com.anjaniy.funfactsbackend.security.CustomAccessDeniedHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService implements MailAPI{
    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void send(EmailNotification emailNotification) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(emailNotification.getRecipient());
            mimeMessageHelper.setSubject(emailNotification.getSubject());
            mimeMessageHelper.setText(emailNotification.getBody());
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            if(emailNotification.getEmailNotificationType().equals(EmailNotificationType.ACCOUNT_ACTIVATION)) {
                logger.info("Account activation link has been sent to: " + emailNotification.getRecipient());
            }
            if(emailNotification.getEmailNotificationType().equals(EmailNotificationType.RESET_PASSWORD)) {
                logger.info("Reset password link has been sent to: " + emailNotification.getRecipient());
            }
        } catch(MailException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error occurred while sending the email notification to: " + emailNotification.getRecipient());
        }
    }
}
