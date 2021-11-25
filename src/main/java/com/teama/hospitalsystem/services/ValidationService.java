package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.models.WorkDay;

public interface ValidationService {

    boolean validateEmail(String email);
    boolean validatePhone(String phone);
    boolean validatePassword(String password);
    boolean validateAppointment(Appointment appointment);
    boolean validateName(String name);
    boolean validateWorkDay(WorkDay workDay);
    boolean validateEmployerData(EmployerData employerData);
}
