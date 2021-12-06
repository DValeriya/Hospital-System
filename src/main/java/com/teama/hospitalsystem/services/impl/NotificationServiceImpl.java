package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.UserDAO;
import com.teama.hospitalsystem.exceptions.FileNotFoundException;
import com.teama.hospitalsystem.exceptions.MessageNotSendException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.NotificationService;
import com.teama.hospitalsystem.util.MailConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Properties;


@Service
public class NotificationServiceImpl implements NotificationService {
    Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final UserDAO userDAO;
    private final JavaMailSender javaMailSender;

    public NotificationServiceImpl(UserDAO userDAO,MailConfig mailConfig){
        this.userDAO = userDAO;
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailConfig.getHost());
        javaMailSender.setPort(mailConfig.getPort());
        javaMailSender.setUsername(mailConfig.getUsername());
        javaMailSender.setPassword(mailConfig.getPassword());
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", mailConfig.getAuth());
        props.put("mail.smtp.starttls.enable", mailConfig.getTtls());
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void notifyUserByEmailAboutAppointment(BigInteger userId, Appointment appointment) throws MessageNotSendException {
        ClassPathResource resource = new ClassPathResource(NOTIFICATION_TEMPLATE);
        User user = userDAO.getUserById(userId);
        try {
            sentMail(resource, user, NOTIFICATION, appointment);
        }catch (SendFailedException ex){
            log.error(ex.getMessage());
            throw new MessageNotSendException(ex.getInvalidAddresses());
        }catch (MessagingException ex){
            log.error(ex.getMessage());
            throw new MessageNotSendException(ex.getMessage());
        }
    }

    @Override
    public void notifyUserByEmailAboutRegistration(BigInteger userId) throws MessageNotSendException {
        ClassPathResource resource = new ClassPathResource(REGISTRATION_TEMPLATE);
        User user = userDAO.getUserById(userId);
        try {
            sentMail(resource, user, REGISTRATION, null);
        }catch (SendFailedException ex){
            log.error(ex.getMessage());
            throw new MessageNotSendException(ex.getInvalidAddresses());
        }catch (MessagingException ex){
            log.error(ex.getMessage());
            throw new MessageNotSendException(ex.getMessage());
        }
    }

    private void sentMail(ClassPathResource resource, User to, String subject, Appointment appointment) throws MessagingException, FileNotFoundException{
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setFrom("noreply@hospital.com");
            helper.setTo(to.getEmail());
            String actual = readFile(resource.getFile());
            switch (subject){
                case REGISTRATION:
                    helper.setSubject(subject);
                    helper.setText(String.format(actual, to.getName()), true);
                    break;
                case NOTIFICATION:
                    helper.setSubject("Notification about Appointment");
                    helper.setText(String.format(actual, to.getName(), appointment.getExpectedStart(), appointment.getExpectedEnd()), true);
                    break;
            }
            javaMailSender.send(message);
        }catch (IOException ex){
            log.error(ex.getLocalizedMessage());
            throw new FileNotFoundException(resource.getFilename(), resource.getPath());
        }
    }
    static String readFile(File path)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path.getPath()));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
