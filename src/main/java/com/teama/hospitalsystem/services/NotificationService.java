package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.util.MailConfig;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigInteger;

public interface NotificationService {
    void notifyUserByEmailAboutAppointment(BigInteger userId, Appointment appointment) throws MessagingException;
    void notifyUserByEmailAboutRegistration(BigInteger userId) throws MessagingException;
    void notifyUserByEmailAboutResetPassword(String email, String token);
    String NOTIFICATION_TEMPLATE = "templates/notification_template.html";
    String REGISTRATION_TEMPLATE = "templates/greetings_template.html";
    String REGISTRATION = "REGISTRATION";
    String NOTIFICATION = "NOTIFICATION";
}
