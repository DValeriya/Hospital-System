package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.UserDAO;
import com.teama.hospitalsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.util.ResourceUtils;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationServiceImpl implements NotificationService {
    Logger log = Logger.getLogger(NotificationService.class.getName());
    private final UserDAO userDAO;

    public NotificationServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @Override
    public void notifyUserByEmailAboutAppointment(BigInteger id) {
        ClassPathResource resource = new ClassPathResource("templates/greetings_template.html");
        try {
            sentMail(resource, "lotuszadrotus1@gmail.com", "Registration");
        }catch (MessagingException ex){
            log.log(Level.WARNING, ex.getMessage() + ex.toString());
        }
    }

    @Override
    public void notifyUserByEmailAboutRegistration(BigInteger id) {
        ClassPathResource resource = new ClassPathResource("templates/notification_template.html");
        try {
            sentMail(resource, "lotuszadrotus1@gmail.com", "Notification about Appointment");
        }catch (MessagingException ex){
            log.log(Level.WARNING, ex.getMessage() + ex.toString());
        }
    }

    private void sentMail(ClassPathResource resource, String to, String subject) throws MessagingException{
        String from = "hospitalsystem.fall.2021@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "Mph36kiL");
            }
        });
        try {
            Multipart multipart = new MimeMultipart();
            MimeMessage message = new MimeMessage(session);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(resource.getURL()));
            multipart.addBodyPart(messageBodyPart);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(multipart);
            Transport.send(message);
//            log.log(Level.INFO, "msg sent");
        }catch (IOException ex){
            log.log(Level.WARNING, "Can't find a template on path:" + ex.getLocalizedMessage());
        }
    }
}
