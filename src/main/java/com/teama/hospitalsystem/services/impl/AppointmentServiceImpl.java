package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.AppointmentDAO;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.AppointmentService;
import com.teama.hospitalsystem.util.AppointmentStatus;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDAO appointmentDAO;
    public AppointmentServiceImpl(AppointmentDAO appointmentDAO){
        this.appointmentDAO = appointmentDAO;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentDAO.createAppointment(appointment);
    }

    @Override
    public Appointment getAppointmentById(BigInteger id) {
        return appointmentDAO.getAppointmentById(id);
    }

    @Override
    public void editAppointment(Appointment appointment) {
        appointmentDAO.editAppointment(appointment);
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientId(BigInteger patientId) {
        return appointmentDAO.getAppointmentByPatientId(patientId);
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date) {
        return appointmentDAO.getAppointmentByPatientIdForADate(patientId, date);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId) {
        return appointmentDAO.getAppointmentByDoctorId(doctorId);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year) {
        return appointmentDAO.getAppointmentByDoctorIdForAMonth(doctorId, month, year);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day) {
        return appointmentDAO.getAppointmentByDoctorIdForADay(doctorId, day);
    }

    @Override
    public Collection<Appointment> getAppointmentByWorkDay(WorkDay day) {
        return appointmentDAO.getAppointmentByWorkDay(day);
    }

    @Override
    public void startAppointment(BigInteger appointmentId) {
        appointmentDAO.startAppointment(appointmentId);
    }

    @Override
    public void endAppointment(BigInteger appointmentId) {
        appointmentDAO.endAppointment(appointmentId);
    }

    @Override
    public void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status) {
        appointmentDAO.changeAppointmentStatus(appointmentId, status);
    }
}
