package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public interface WorkDayService {

    WorkDay createWorkDay(WorkDay workDay);

    void deleteWorkDay(BigInteger workDay);

    Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId);

    Collection<WorkDay> getWorkDaysByDate(Date date);
    Collection<Appointment> getAppointments(BigInteger workDayId);
//    Collection<Appointment> getAppointmentsByDate(Date date);
}
