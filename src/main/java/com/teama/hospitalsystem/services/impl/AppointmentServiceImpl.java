package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.AppointmentDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.AppointmentService;
import com.teama.hospitalsystem.util.AppointmentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

@Service
public class AppointmentServiceImpl implements AppointmentService  {
    private final AppointmentDAO appointmentDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImpl.class);
    public AppointmentServiceImpl(AppointmentDAO appointmentDAO){
        this.appointmentDAO = appointmentDAO;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) throws DAOException {
        try{
            return appointmentDAO.createAppointment(appointment);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentServiceImpl error. Appointment was not created");
        }
    }

    @Override
    public Appointment getAppointmentById(BigInteger id) throws DAOException {
        try{
            return appointmentDAO.getAppointmentById(id);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentServiceImpl error. Failed to get information by id.");
        }
    }

    @Override
    public void editAppointment(Appointment appointment) throws DAOException {
        try{
            appointmentDAO.editAppointment(appointment);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentServiceImpl error. Appointment was not edited");
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientId(BigInteger patientId) throws DAOException {
        return appointmentDAO.getAppointmentByPatientId(patientId);
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date) throws DAOException {
        return appointmentDAO.getAppointmentByPatientIdForADate(patientId, date);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId) throws DAOException {
        return appointmentDAO.getAppointmentByDoctorId(doctorId);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year) throws DAOException {
        return appointmentDAO.getAppointmentByDoctorIdForAMonth(doctorId, month, year);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day) throws DAOException {
        return appointmentDAO.getAppointmentByDoctorIdForADay(doctorId, day);
    }

    @Override
    public Collection<Appointment> getAppointmentByWorkDay(WorkDay day) throws DAOException {
        return appointmentDAO.getAppointmentByWorkDay(day);
    }
    @Override
    public Collection<Appointment> getAppointmentByWorkDay(BigInteger workDayId) throws DAOException {
        return appointmentDAO.getAppointmentByWorkDay(workDayId);
    }

    @Override
    public void startAppointment(BigInteger appointmentId) throws DAOException {
        try{
            appointmentDAO.startAppointment(appointmentId);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentServiceImpl error. Failed to start appointment.");
        }
    }

    @Override
    public void endAppointment(BigInteger appointmentId) throws DAOException {
        try{
            appointmentDAO.endAppointment(appointmentId);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentServiceImpl error. Failed to end appointment.");
        }
    }

    @Override
    public void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status) throws DAOException {
        try{
            appointmentDAO.changeAppointmentStatus(appointmentId, status);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentServiceImpl error. Failed to change appointment status.");
        }
    }
}
