package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validateEmail(String email) {
        return false;
    }

    @Override
    public boolean validatePhone(String phone) {
        return false;
    }

    @Override
    public boolean validatePassword(String password) {
        return false;
    }

    @Override
    public boolean validateAppointment(Appointment appointment) {
        return false;
    }

    @Override
    public boolean validateName(String name) {
        return false;
    }

    @Override
    public boolean validateWorkDay(WorkDay workDay) {
        return false;
    }

    @Override
    public boolean validateEmployerData(EmployerData employerData) {
        return false;
    }
}
