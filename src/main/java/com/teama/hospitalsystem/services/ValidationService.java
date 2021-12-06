package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.exceptions.ValidationException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.models.WorkDay;

public interface ValidationService {

    <T> void validateBean(T object) throws ValidationException;
}
