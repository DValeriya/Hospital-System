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
import java.util.HashMap;
import java.util.Map;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDAO appointmentDAO;

    public AppointmentServiceImpl(AppointmentDAO appointmentDAO){
        this.appointmentDAO = appointmentDAO;
    }

    @Override
    public void createAppointment(Appointment appointment) {
        appointmentDAO.createAppointment(appointment);
    }

    @Override
    public Appointment getAppointmentById(BigInteger id) {
        return appointmentDAO.getAppointmentById(id);
    }
//    TODO: simplify Map formation in one method
    @Override
    public void editAppointment(Appointment appointment) {
        Map<String, Object> params = new HashMap<>();
        params.put("EXPSTART", appointment.getExpectedStart());
        params.put("EXPEND", appointment.getExpectedEnd());
        params.put("ACTSTART", (appointment.getActualStart()));
        params.put("ACTEND", (appointment.getActualEnd()));
        params.put("DOCTORID", appointment.getDoctorId());
        params.put("PATIENTID", appointment.getPatientId());
        params.put("DIAGNOSIS", appointment.getDiagnosis());
        params.put("REFFERAL", appointment.getReferral());
        params.put("NXTAPP", appointment.getNextAppointment());
        params.put("STATUS", String.valueOf(appointment.getStatus().getId()));
        appointmentDAO.editAppointment(params);
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
