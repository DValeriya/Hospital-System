package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.WorkDayDAO;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.WorkDayService;
import org.springframework.dao.DataAccessException;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public class WorkDayServiceImpl implements WorkDayService {
    private final WorkDayDAO workDayDAO;

    public WorkDayServiceImpl(WorkDayDAO workDayDAO) {
        this.workDayDAO = workDayDAO;
    }

    @Override
    public void createWorkDay(WorkDay workDay) {
        workDayDAO.createWorkDay(workDay);
    }

    @Override
    public void deleteWorkDay(WorkDay workDay) {
        workDayDAO.deleteWorkDay(workDay);
    }

    @Override
    public Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId) throws DataAccessException {
        return workDayDAO.getWorkDaysByEmployerId(employerId);
    }

    @Override
    public Collection<WorkDay> getWorkDaysByDate(Date date) throws DataAccessException {
        return workDayDAO.getWorkDaysByDate(date);
    }
}
