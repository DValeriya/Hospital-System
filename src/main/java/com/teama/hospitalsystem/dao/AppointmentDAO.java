package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.util.AppointmentStatus;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.Month;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AppointmentDAO {

    void createAppointment(Appointment appointment) throws SQLException;

    Appointment getAppointmentById(BigInteger id);

    void editAppointment(Map<String, Object> params);

    Collection<Appointment> getAppointmentByPatientId(BigInteger patientId);

    Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date);

    Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId);

    Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year);

    Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day);

    void startAppointment(BigInteger appointmentId);

    void endAppointment(BigInteger appointmentId);

    void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status);
}
