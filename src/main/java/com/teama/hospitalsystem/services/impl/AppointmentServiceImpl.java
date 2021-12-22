package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.AppointmentDAO;
import com.teama.hospitalsystem.dao.impl.AppointmentDAOImpl;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.AppointmentService;
import com.teama.hospitalsystem.util.AppointmentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Service
public class AppointmentServiceImpl implements AppointmentService  {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
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
            String error = String.format("Failed to create appointment in %s method: createAppointment\nGot parameters:\n\tappointment: %s\n",
                    AppointmentServiceImpl.class, appointment.toString());
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public Appointment getAppointmentById(BigInteger id) throws DAOException {
        try{
            return appointmentDAO.getAppointmentById(id);
        }catch (DAOException ex){
            String error = String.format(GET_APPOINTMENT_BY_ID_ERROR,
                    AppointmentServiceImpl.class, id);
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public void editAppointment(Appointment appointment) throws DAOException {
        try{
            appointmentDAO.editAppointment(appointment);
        }catch (DAOException ex){
            String error = String.format(EDIT_APPOINTMENT_ERROR,
                    AppointmentServiceImpl.class, appointment.toString());
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientId(BigInteger patientId) throws DAOException {
        try{
            return appointmentDAO.getAppointmentByPatientId(patientId);
        }catch (DAOException ex){
            String error = String.format(GET_APPOINTMENT_BY_PATIENT_ID_ERROR,
                    AppointmentServiceImpl.class, patientId);
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date) throws DAOException {
        try {
            return appointmentDAO.getAppointmentByPatientIdForADate(patientId, date);
        }catch (DAOException ex){
            String error = String.format(GET_APPOINTMENT_BY_PATIENT_FOR_A_DATE_ID_ERROR,
                    AppointmentServiceImpl.class, patientId, sdf.format(date));
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId) throws DAOException {
        try{
            return appointmentDAO.getAppointmentByDoctorId(doctorId);
        }catch (DAOException ex){
            String error = String.format(GET_APPOINTMENT_BY_DOCTOR_ID_ERROR,
                    AppointmentServiceImpl.class, doctorId);
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year) throws DAOException {
        try{
            return appointmentDAO.getAppointmentByDoctorIdForAMonth(doctorId, month, year);
        }catch (DAOException ex){
            String error = String.format(GET_APPOINTMENT_BY_DOCTOR_ID_FOR_A_MONTH_ERROR,
                    AppointmentServiceImpl.class, doctorId, month, year);
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day) throws DAOException {
        try {
            return appointmentDAO.getAppointmentByDoctorIdForADay(doctorId, day);
        }catch (DAOException ex){
            String error = String.format(GET_APPOINTMENT_BY_DOCTOR_ID_FOR_A_DAY_ERROR,
                    AppointmentServiceImpl.class, doctorId, day.toString());
            LOGGER.error(error, ex);
            throw new DAOException(error, ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByWorkDay(WorkDay day) throws DAOException {
        try{
            return appointmentDAO.getAppointmentByWorkDay(day);
        }catch (DAOException ex){
            String error =
                    String.format(GET_APPOINTMENT_BY_WORK_DAY_ERROR,
                            AppointmentServiceImpl.class,day.toString());
            LOGGER.error(error);
            throw new DAOException(error, ex);
        }
    }
    @Override
    public Collection<Appointment> getAppointmentByWorkDay(BigInteger workDayId) throws DAOException {
        try{
            return appointmentDAO.getAppointmentByWorkDay(workDayId);
        }catch (DAOException ex){
            String error =
                    String.format(GET_APPOINTMENT_BY_WORK_DAY_ID_ERROR,
                            AppointmentServiceImpl.class,workDayId.toString());
            LOGGER.error(error);
            throw new DAOException(error, ex);
        }
    }

    @Override
    public void startAppointment(BigInteger appointmentId) throws DAOException {
        try{
            appointmentDAO.startAppointment(appointmentId);
        }catch (DAOException ex){
            String error = String.format(START_APPOINTMENT_ERROR,
                    AppointmentServiceImpl.class, appointmentId);
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public void endAppointment(BigInteger appointmentId) throws DAOException {
        try{
            appointmentDAO.endAppointment(appointmentId);
        }catch (DAOException ex){
            String error = String.format(END_APPOINTMENT_ERROR,
                    AppointmentServiceImpl.class, appointmentId);
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

    @Override
    public void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status) throws DAOException {
        try{
            appointmentDAO.changeAppointmentStatus(appointmentId, status);
        }catch (DAOException ex){
            String error = String.format(CHANGE_APPOINTMENT_STATUS_ERROR,
                    AppointmentServiceImpl.class, appointmentId, status.toString());
            LOGGER.error(error,ex);
            throw new DAOException(error,ex);
        }
    }

}
