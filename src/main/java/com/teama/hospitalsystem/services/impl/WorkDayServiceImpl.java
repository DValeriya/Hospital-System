package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.WorkDayDAO;
import com.teama.hospitalsystem.dao.impl.AppointmentDAOImpl;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.AppointmentService;
import com.teama.hospitalsystem.services.WorkDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class WorkDayServiceImpl implements WorkDayService {
    private final WorkDayDAO workDayDAO;
    private final AppointmentService appointmentService;
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkDayServiceImpl.class);
    public WorkDayServiceImpl(WorkDayDAO workDayDAO, AppointmentService appointmentService) {
        this.workDayDAO = workDayDAO;
        this.appointmentService = appointmentService;
    }

    @Override
    public void createWorkDay(WorkDay workDay) throws DAOException {
        try {
            workDayDAO.createWorkDay(workDay);
        }catch (DAOException ex){
            LOGGER.error(ex.getLocalizedMessage(), ex);
            throw new DAOException(ex.getLocalizedMessage(),ex);
        }
    }

    @Override
    public void deleteWorkDay(WorkDay workDay) throws DAOException {
        try {
            workDayDAO.deleteWorkDay(workDay);
        }catch (DAOException ex){
            LOGGER.error(ex.getLocalizedMessage(), ex);
            throw new DAOException(ex.getLocalizedMessage(),ex);
        }
    }

    @Override
    public Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId) throws DAOException {
        try {
            return workDayDAO.getWorkDaysByEmployerId(employerId);
        }catch (DAOException ex){
            LOGGER.error(ex.getLocalizedMessage(), ex);
            throw new DAOException(ex.getLocalizedMessage(),ex);
        }
    }

    @Override
    public Collection<WorkDay> getWorkDaysByDate(Date date) throws DAOException {
        try {
            return workDayDAO.getWorkDaysByDate(date);
        }catch (DAOException ex){
            LOGGER.error(ex.getLocalizedMessage(),ex);
            throw new DAOException(ex.getLocalizedMessage(),ex);
        }
    }
}
