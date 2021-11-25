package com.teama.hospitalsystem.services;

import java.math.BigInteger;

public interface NotificationService {
    void notifyUserByEmailAboutAppointment(BigInteger id);
    void notifyUserByEmailAboutRegistration(BigInteger id);
}
