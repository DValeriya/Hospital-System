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

    String GET_APPOINTMENT_BY_ID_ERROR = "Failed to get appointment in %s method: getAppointmentById\nGot parameters:\n\tid: %d\n";
    String GET_APPOINTMENT_BY_DOCTOR_ID_ERROR = "Failed to get appointment in %s method: getAppointmentByDoctorId\nGot parameters:\n\tdoctorId: %d\n";
    String GET_APPOINTMENT_BY_DOCTOR_ID_FOR_A_DAY_ERROR = "Failed to get appointment in %s method: getAppointmentByDoctorIdForADay\nGot parameters:\n\tdoctorId: %d\n\tWorkDay: %s\n";
    String GET_APPOINTMENT_BY_DOCTOR_ID_FOR_A_MONTH_ERROR = "Failed to get appointment in %s method: getAppointmentByDoctorIdForAMonth\nGot parameters:\n\tdoctorId: %d\n\tMonth: %s\n\tYear: %s\n";
    String GET_APPOINTMENT_BY_PATIENT_ID_ERROR = "Failed to get appointment in %s method: getAppointmentByPatientId\nGot parameters:\n\tpatientId: %d\n";
    String GET_APPOINTMENT_BY_PATIENT_FOR_A_DATE_ID_ERROR = "Failed to get appointment in %s method: getAppointmentByPatientIdForADate\nGot parameters:\n\tpatientId: %d\n\tDate: %s\n";
    String GET_APPOINTMENT_BY_WORK_DAY_ERROR = "Failed to get appointment in %s method: getAppointmentByWorkDay\nGot parameters:\n\tWorkDay: %s\n";
    String GET_APPOINTMENT_BY_WORK_DAY_ID_ERROR = "Failed to get appointment in %s method: getAppointmentByWorkDay\nGot parameters:\n\tworkDayId: %s\n";
    String CHANGE_APPOINTMENT_STATUS_ERROR = "Failed to edit appointment in %s method: changeAppointmentStatus\nGot parameters:\n\tappointmentId: %d\n\tstatus:%s\n";
    String EDIT_APPOINTMENT_ERROR = "Failed to edit appointment in %s method: editAppointment\nGot parameters:\n\tappointment: %s";
    String START_APPOINTMENT_ERROR = "Failed to start appointment in %s method: startAppointment\nGot parameters:\n\tappointmentId: %d\n";
    String END_APPOINTMENT_ERROR = "Failed to end appointment in %s method: endAppointment\nGot parameters:\n\tappointmentId: %d\n      ";
}
