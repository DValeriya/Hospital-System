package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.WorkDay;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public interface WorkDayDAO {

    void createWorkDay(WorkDay workDay);

    String CREATE_WORK_DAY_PROCEDURE_NAME = "CREATE_WORK_DAY";

    String DELETE_WORK_DAY = "DELETE_WORK_DAY";

    String SELECT_WORK_DAYS_BY_EMPLOYERID = "SELECT WORKDAYS.OBJECT_ID AS workDayId, EMPLOYERID.OBJECT_ID AS employerId, DATES.DATE_VALUE AS work_date " +
            "FROM OBJECTS WORKDAYS " +
            "JOIN OBJREFERENCE EMPLOYERID ON EMPLOYERID.REFERENCE = WORKDAYS.OBJECT_ID " +
            "AND EMPLOYERID.ATTR_ID = 14 " +
            "JOIN ATTRIBUTES DATES ON DATES.OBJECT_ID = WORKDAYS.OBJECT_ID " +
            "WHERE WORKDAYS.OBJECT_TYPE_ID = 3 " +
            "AND DATES.ATTR_ID = 15 " +
            "AND EMPLOYERID.OBJECT_ID = ?";

    String SELECT_WORK_DAY_BY_DATE = "SELECT WORKDAYS.OBJECT_ID AS workDayId, EMPLOYERID.OBJECT_ID AS employerId, DATES.DATE_VALUE AS work_date " +
            "FROM OBJECTS WORKDAYS " +
            "JOIN OBJREFERENCE EMPLOYERID ON EMPLOYERID.REFERENCE = WORKDAYS.OBJECT_ID " +
            "AND EMPLOYERID.ATTR_ID = 14 " +
            "JOIN ATTRIBUTES DATES ON DATES.OBJECT_ID = WORKDAYS.OBJECT_ID " +
            "WHERE WORKDAYS.OBJECT_TYPE_ID = 3 " +
            "AND DATES.ATTR_ID = 15 " +
            "AND DATES.DATE_VALUE = ?";

    void deleteWorkDay(WorkDay workDay);

    Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId);

    Collection<WorkDay> getWorkDayByDate(Date date);
}
