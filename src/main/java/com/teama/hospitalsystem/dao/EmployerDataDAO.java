package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.EmployerStatus;

import java.math.BigInteger;
import java.util.Collection;

public interface EmployerDataDAO {

    String CREATE_EMP_DATA_FUNCTION= "CREATE_EMPLOYER_DATA";

    String EDIT_EMPLOYER_DATA_PROCEDURE = "EDIT_EMPLOYER_DATA";

    String GET_EMP_LIST = "SELECT EMP.OBJECT_ID AS EMPLOYER_ID, HIRING_DATE.DATE_VALUE AS HIRING_DATE," +
            "STATUS.LIST_VALUE_ID AS STATUS, START_WORKING_TIME.VALUE AS START_WORKING_TIME," +
            "END_WORKING_TIME.VALUE AS END_WORKING_TIME, DOCTOR_DATA.VALUE AS DOCTOR_ID " +
            "FROM OBJECTS EMP JOIN ATTRIBUTES HIRING_DATE ON HIRING_DATE.OBJECT_ID = EMP.OBJECT_ID " +
            "AND HIRING_DATE.ATTR_ID = 9 " +
            "JOIN ATTRIBUTES STATUS ON STATUS.OBJECT_ID = EMP.OBJECT_ID " +
            "AND STATUS.ATTR_ID = 10 " +
            "JOIN ATTRIBUTES START_WORKING_TIME ON START_WORKING_TIME.OBJECT_ID = EMP.OBJECT_ID " +
            "AND START_WORKING_TIME.ATTR_ID = 11 " +
            "JOIN ATTRIBUTES END_WORKING_TIME ON END_WORKING_TIME.OBJECT_ID = EMP.OBJECT_ID " +
            "AND END_WORKING_TIME.ATTR_ID = 12 " +
            "LEFT JOIN ATTRIBUTES DOCTOR_DATA ON DOCTOR_DATA.OBJECT_ID = EMP.OBJECT_ID " +
            "AND DOCTOR_DATA.ATTR_ID = 13 " +
            "WHERE EMP.OBJECT_TYPE_ID = 2 AND DOCTOR_DATA.VALUE IS NULL";

    String GET_EMP_DATA_BY_USER_ID = GET_EMP_LIST + " AND EMP.PARENT_ID = ?";


    String EDIT_EMP_STATUS = "UPDATE ATTRIBUTES STATUS SET STATUS.LIST_VALUE_ID = ?" +
            "WHERE STATUS.ATTR_ID = 10 AND STATUS.OBJECT_ID = ?";

    String GET_EMP_LIST_BY_STATUS = GET_EMP_LIST + " AND STATUS.LIST_VALUE_ID = ?";

    String GET_EMP_DATA_ID_BY_USER_ID = "SELECT OBJECT_ID FROM OBJECTS WHERE PARENT_ID = ? AND OBJECT_TYPE_ID = 2";

    String HIRING_DATE = "HIRING_DATE";
    String STATUS = "STATUS";
    String START_WORKING_TIME = "START_WORKING_TIME";
    String END_WORKING_TIME = "END_WORKING_TIME";
    String EMP_PARENT_ID = "EMP_PARENT_ID";
    String EMPLOYER_ID = "EMPLOYER_ID";

    BigInteger createEmployerData(EmployerData employerData, BigInteger userId);
    void editEmployerData(EmployerData employerData);
    void changeEmployerStatus(EmployerData employerData);
    EmployerData getEmployerDataByUserId(BigInteger userId);
    Collection<EmployerData> getEmployerListByStatus(EmployerStatus employerStatus);
    BigInteger getEmployerDataId(BigInteger userId);

}
