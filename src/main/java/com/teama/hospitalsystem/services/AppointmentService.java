package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.util.AppointmentStatus;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment);

    Appointment getAppointmentById(BigInteger id);

    void editAppointment(Appointment appointment);

    Collection<Appointment> getAppointmentByPatientId(BigInteger patientId);

    Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date);

    Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId);

    Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year);

    Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day);

    Collection<Appointment> getAppointmentByWorkDay(WorkDay day);

    Collection<Appointment> getAppointmentByWorkDay(BigInteger workDayId);

    void startAppointment(BigInteger appointmentId);

    void endAppointment(BigInteger appointmentId);

    void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status);
}
