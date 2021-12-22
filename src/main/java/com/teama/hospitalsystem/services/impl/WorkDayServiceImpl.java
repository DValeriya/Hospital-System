package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.WorkDayDAO;
import com.teama.hospitalsystem.dao.impl.AppointmentDAOImpl;
import com.teama.hospitalsystem.dao.impl.WorkDayDAOImpl;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.AppointmentService;
import com.teama.hospitalsystem.services.WorkDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class WorkDayServiceImpl implements WorkDayService {
    private final WorkDayDAO workDayDAO;
    private final AppointmentService appointmentService;
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkDayServiceImpl.class);
    public WorkDayServiceImpl(WorkDayDAO workDayDAO, AppointmentService appointmentService) {
        this.workDayDAO = workDayDAO;
        this.appointmentService = appointmentService;
    }

    @Override
    public WorkDay createWorkDay(WorkDay workDay) throws DAOException {
        try {
            return workDayDAO.createWorkDay(workDay);
        }catch (DAOException ex){
            String error = String.format("Failed to create workDay in %s method: createWorkDay\nGot parameters:\n\tid: %s\n",
                    WorkDayDAOImpl.class, workDay.toString());
            LOGGER.error(error, ex);
            throw new DAOException(error, ex);
        }
    }

    @Override
    public void deleteWorkDay(BigInteger workDayId) throws DAOException {
        try {
            workDayDAO.deleteWorkDay(workDayId);
        }catch (DAOException ex){
            String error = String.format("Failed to delete workDay in %s method: deleteWorkDay\nGot parameters:\n\tworkDay: %d\n",
                    WorkDayDAOImpl.class, workDayId);
            LOGGER.error(error, ex);
            throw new DAOException(error, ex);
        }
    }

    @Override
    public Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId) throws DAOException {
        try {
            return workDayDAO.getWorkDaysByEmployerId(employerId);
        }catch (DAOException ex){
            String error = String.format("Failed to get workDay in %s method: getWorkDaysByEmployerId\nGot parameters:\n\temployerId: %d\n",
                    WorkDayDAOImpl.class,employerId);
            LOGGER.error(error, ex);
            throw new DAOException(error, ex);
        }
    }

    @Override
    public Collection<WorkDay> getWorkDaysByDate(Date date) throws DAOException {
        try {
            return workDayDAO.getWorkDaysByDate(date);
        }catch (DAOException ex){
            String error = String.format("Failed to get workDay in %s method: getWorkDaysByDate\nGot parameters:\n\tdate: %s\n",
                    WorkDayDAOImpl.class,date);
            LOGGER.error(error, ex);
            throw new DAOException(error, ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointments(BigInteger workDayId) {
        try {
            return appointmentService.getAppointmentByWorkDay(workDayId);
        }catch (DAOException ex){
            String error = String.format("Failed to get Appointments in %s method: getAppointments\nGot parameters:\n\tworkDayId: %s\n",
                    WorkDayDAOImpl.class,workDayId);
            LOGGER.error(error, ex);
            throw new DAOException(error, ex);
        }
    }


}
