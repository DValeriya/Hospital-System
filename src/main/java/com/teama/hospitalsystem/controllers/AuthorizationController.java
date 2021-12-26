package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.ResetPasswordToken;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.request.LoginRequest;
import com.teama.hospitalsystem.request.ForgotPasswordRequest;
import com.teama.hospitalsystem.request.ResetPasswordRequest;
import com.teama.hospitalsystem.services.NotificationService;
import com.teama.hospitalsystem.services.ResetPasswordTokenService;
import com.teama.hospitalsystem.services.UsersService;
import com.teama.hospitalsystem.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@Validated
@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);
    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final NotificationService notificationService;
    private final ResetPasswordTokenService resetPasswordTokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationController(UsersService usersService, AuthenticationManager authenticationManager,
                                   JwtTokenUtil jwtTokenUtil, NotificationService notificationService,
                                   ResetPasswordTokenService resetPasswordTokenService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.notificationService = notificationService;
        this.resetPasswordTokenService = resetPasswordTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin().toString(),
                            loginRequest.getPassword()));
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            String jwtToken = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("token", jwtToken);
            }});
        } catch (IllegalStateException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        try {
            User user = usersService.getUserByEmail(forgotPasswordRequest.getEmail()); // Емаил сохраняется
            String generatedToken = jwtTokenUtil.generateResetPasswordToken(user.getEmail());  //По емайлу генериркется токен
            ResetPasswordToken token = null;

            notificationService.notifyUserByEmailAboutResetPassword(user.getEmail(), generatedToken);

            try {
                token = resetPasswordTokenService.getTokenByUserId(user.getId());
            } catch (DAOException daoException) {
                LOGGER.error(daoException.getMessage());
            }

            if (token == null) {
                resetPasswordTokenService.saveToken(generatedToken, user.getId());
            } else {
                token.setToken(generatedToken);
                resetPasswordTokenService.updateToken(token);
            }

            return ResponseEntity.ok().build();
        } catch (DAOException | MailSendException exception) {
            LOGGER.error(exception.getMessage());
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            String token = resetPasswordRequest.getToken();
            String newPassword = resetPasswordRequest.getPassword();

            ResetPasswordToken resetPasswordToken = resetPasswordTokenService.getTokenByValue(token);

            User user = usersService.getUserById(resetPasswordToken.getUserId());
            user.setPassword(newPassword);
            usersService.editUser(user);
            
            resetPasswordTokenService.deleteToken(resetPasswordToken.getId());
            return ResponseEntity.ok().build();
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getMessage());
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }
}
