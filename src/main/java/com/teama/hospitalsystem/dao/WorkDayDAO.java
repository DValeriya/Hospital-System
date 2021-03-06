package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.util.EAVAttrTypesID;
import com.teama.hospitalsystem.util.EAVObjTypesID;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public interface WorkDayDAO {

    WorkDay createWorkDay(WorkDay workDay);

    String CREATE_WORK_DAY_PROCEDURE_NAME = "CREATE_WORK_DAY";

    String DELETE_WORK_DAY_PROCEDURE_NAME = "DELETE_WORK_DAY";

    String SELECT_WORK_DAYS_BY_EMPLOYERID = "SELECT WORKDAYS.OBJECT_ID AS WORK_DAY_ID, WORKDAYS.PARENT_ID AS EMPLOYER_ID, DATES.DATE_VALUE AS WORK_DATE " +
            "FROM OBJECTS WORKDAYS " +
            "JOIN ATTRIBUTES DATES ON DATES.OBJECT_ID = WORKDAYS.OBJECT_ID " +
            "AND DATES.ATTR_ID = " + EAVAttrTypesID.DATE +
            "AND WORKDAYS.OBJECT_TYPE_ID = " + EAVObjTypesID.WORKDAY +
            "AND WORKDAYS.PARENT_ID = ?";

    String SELECT_WORK_DAYS_BY_DATE = "SELECT WORKDAYS.OBJECT_ID AS WORK_DAY_ID, WORKDAYS.PARENT_ID AS EMPLOYER_ID, DATES.DATE_VALUE AS WORK_DATE " +
            "FROM OBJECTS WORKDAYS " +
            "JOIN ATTRIBUTES DATES ON DATES.OBJECT_ID = WORKDAYS.OBJECT_ID " +
            "AND DATES.ATTR_ID = " + EAVAttrTypesID.DATE +
            "AND WORKDAYS.OBJECT_TYPE_ID = " + EAVObjTypesID.WORKDAY +
            "AND DATES.DATE_VALUE = ?";

    void deleteWorkDay(BigInteger workDayId);

    Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId);

    Collection<WorkDay> getWorkDaysByDate(Date date);
}
