package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.util.AppointmentStatus;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public interface AppointmentDAO {

    String SQL_GET_BY_ID =
            "SELECT  APPOINTMENT.OBJECT_ID AS ID, EXPECTEDSTART.DATE_VALUE AS EXPSTART, EXPECTEDEND.DATE_VALUE AS EXPEND, \n" +
                    "        ACTUALSTART.DATE_VALUE AS ACTSTART, ACTUALEND.DATE_VALUE AS ACTEND, DOCTORID.OBJECT_ID AS DOCTORID,\n" +
                    "        PATIENTID.OBJECT_ID AS PATIENTID, DIAGNOSIS.VALUE AS DIAGNOSIS, REFERRAL.VALUE AS REFERRAL,\n" +
                    "        NXTAPP.OBJECT_ID AS NXTAPP, STATUS.LIST_VALUE_ID AS STATUS FROM OBJECTS APPOINTMENT\n" +
                    "            LEFT JOIN ATTRIBUTES EXPECTEDSTART ON EXPECTEDSTART.ATTR_ID = 19 AND EXPECTEDSTART.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN ATTRIBUTES EXPECTEDEND ON EXPECTEDEND.ATTR_ID = 20 AND EXPECTEDEND.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN ATTRIBUTES ACTUALSTART ON ACTUALSTART.ATTR_ID = 21 AND ACTUALSTART.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN ATTRIBUTES ACTUALEND ON ACTUALEND.ATTR_ID = 22 AND ACTUALEND.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN OBJREFERENCE DOCTORID ON DOCTORID.ATTR_ID = 23 AND DOCTORID.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN OBJREFERENCE PATIENTID ON PATIENTID.ATTR_ID = 24 AND PATIENTID.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN ATTRIBUTES DIAGNOSIS ON DIAGNOSIS.ATTR_ID = 25 AND DIAGNOSIS.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN ATTRIBUTES REFERRAL ON REFERRAL.ATTR_ID = 26 AND REFERRAL.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN OBJREFERENCE NXTAPP ON NXTAPP.ATTR_ID = 27 AND NXTAPP.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
                    "            LEFT JOIN ATTRIBUTES STATUS ON STATUS.ATTR_ID = 28 AND STATUS.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "        WHERE APPOINTMENT.OBJECT_ID = ? AND OBJECT_TYPE_ID = 5";

    String SQL_GET_LIST =
            "WITH OBJ AS (\n" +
                    "    SELECT OBJECT_ID FROM OBJECTS WHERE OBJECT_TYPE_ID = 5\n" +
                    ")\n" +
                    "SELECT APPOINTMENT.OBJECT_ID AS ID, EXPECTEDSTART.DATE_VALUE AS EXPSTART, EXPECTEDEND.DATE_VALUE AS EXPEND,\n" +
                    "       ACTUALSTART.DATE_VALUE AS ACTSTART, ACTUALEND.DATE_VALUE AS ACTEND, DOCTORID.OBJECT_ID AS DOCTORID,\n" +
                    "       PATIENTID.OBJECT_ID AS PATIENTID, DIAGNOSIS.VALUE AS DIAGNOSIS, REFERRAL.VALUE AS REFERRAL,\n" +
                    "       NXTAPP.OBJECT_ID AS NXTAPP, STATUS.LIST_VALUE_ID AS STATUS FROM OBJ APPOINTMENT\n" +
                    "    LEFT JOIN ATTRIBUTES EXPECTEDSTART ON EXPECTEDSTART.ATTR_ID = 19 AND EXPECTEDSTART.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN ATTRIBUTES EXPECTEDEND ON EXPECTEDEND.ATTR_ID = 20 AND EXPECTEDEND.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN ATTRIBUTES ACTUALSTART ON ACTUALSTART.ATTR_ID = 21 AND ACTUALSTART.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN ATTRIBUTES ACTUALEND ON ACTUALEND.ATTR_ID = 22 AND ACTUALEND.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN OBJREFERENCE DOCTORID ON DOCTORID.ATTR_ID = 23 AND DOCTORID.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN OBJREFERENCE PATIENTID ON PATIENTID.ATTR_ID = 24 AND PATIENTID.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN ATTRIBUTES DIAGNOSIS ON DIAGNOSIS.ATTR_ID = 25 AND DIAGNOSIS.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN ATTRIBUTES REFERRAL ON REFERRAL.ATTR_ID = 26 AND REFERRAL.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN OBJREFERENCE NXTAPP ON NXTAPP.ATTR_ID = 27 AND NXTAPP.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
                    "    LEFT JOIN ATTRIBUTES STATUS ON STATUS.ATTR_ID = 28 AND STATUS.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
                    "WHERE APPOINTMENT.OBJECT_ID = APPOINTMENT.OBJECT_ID";

    String SQL_GET_BY_DOCTOR_ID = SQL_GET_LIST + " AND DOCTORID.OBJECT_ID = ?";



    String SQL_GET_BY_PATIENT_ID = SQL_GET_LIST + " AND PATIENTID.OBJECT_ID = ?";

    String SQL_GET_BY_PATIENT_ID_AND_DATE = SQL_GET_BY_PATIENT_ID + " AND regexp_like(EXPECTEDSTART.DATE_VALUE, ?)";

    String SQL_GET_BY_WORKDAY = "WITH OBJ AS (\n" +
            "    SELECT OBJECT_ID FROM OBJREFERENCE WHERE REFERENCE = ? AND ATTR_ID = 16\n" +
            ")\n" +
            "SELECT APPOINTMENT.OBJECT_ID AS ID, EXPECTEDSTART.DATE_VALUE AS EXPSTART, EXPECTEDEND.DATE_VALUE AS EXPEND,\n" +
            "       ACTUALSTART.DATE_VALUE AS ACTSTART, ACTUALEND.DATE_VALUE AS ACTEND, DOCTORID.OBJECT_ID AS DOCTORID,\n" +
            "       PATIENTID.OBJECT_ID AS PATIENTID, DIAGNOSIS.VALUE AS DIAGNOSIS, REFERRAL.VALUE AS REFERRAL,\n" +
            "       NXTAPP.OBJECT_ID AS NXTAPP, STATUS.LIST_VALUE_ID AS STATUS FROM OBJ APPOINTMENT\n" +
            "    LEFT JOIN ATTRIBUTES EXPECTEDSTART ON EXPECTEDSTART.ATTR_ID = 19 AND EXPECTEDSTART.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN ATTRIBUTES EXPECTEDEND ON EXPECTEDEND.ATTR_ID = 20 AND EXPECTEDEND.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN ATTRIBUTES ACTUALSTART ON ACTUALSTART.ATTR_ID = 21 AND ACTUALSTART.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN ATTRIBUTES ACTUALEND ON ACTUALEND.ATTR_ID = 22 AND ACTUALEND.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN OBJREFERENCE DOCTORID ON DOCTORID.ATTR_ID = 23 AND DOCTORID.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN OBJREFERENCE PATIENTID ON PATIENTID.ATTR_ID = 24 AND PATIENTID.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN ATTRIBUTES DIAGNOSIS ON DIAGNOSIS.ATTR_ID = 25 AND DIAGNOSIS.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN ATTRIBUTES REFERRAL ON REFERRAL.ATTR_ID = 26 AND REFERRAL.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN OBJREFERENCE NXTAPP ON NXTAPP.ATTR_ID = 27 AND NXTAPP.REFERENCE = APPOINTMENT.OBJECT_ID\n" +
            "    LEFT JOIN ATTRIBUTES STATUS ON STATUS.ATTR_ID = 28 AND STATUS.OBJECT_ID = APPOINTMENT.OBJECT_ID\n" +
            "WHERE APPOINTMENT.OBJECT_ID = APPOINTMENT.OBJECT_ID";

    String SQL_GET_BY_DOCTOR_ID_AND_DAY = SQL_GET_BY_WORKDAY + " AND DOCTORID.OBJECT_ID = ?";

    String SQL_GET_BY_MONTH = SQL_GET_BY_DOCTOR_ID + " AND regexp_like(EXPECTEDSTART.DATE_VALUE, '?')";

    String SQL_APPOINTMENT_MANIPULATION = "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE) VALUES (?, ?, ?)";
    String SQL_CHANGE_APPOINTMENT_STATUS = "UPDATE ATTRIBUTES SET LIST_VALUE_ID = ? WHERE OBJECT_ID = ? AND ATTR_ID = 28";

    String ID = "ID";
    String EXPSTART = "EXPSTART";
    String EXPEND = "EXPEND";
    String ACTSTART = "ACTSTART";
    String ACTEND = "ACTEND";
    String PATIENT_ID = "PATIENTID";
    String DOCTOR_ID = "DOCTORID";
    String DIAGNOSIS = "DIAGNOSIS";
    String REFERRAL = "REFERRAL";
    String NXTAPP ="NXTAPP";
    String STATUS ="STATUS";

    String GET_APPOINTMENT_BY_ID_ERROR = "Failed to get appointment in %s method: getAppointmentById\nGot parameters:\n\tid: %d\n";
    String GET_APPOINTMENT_BY_DOCTOR_ID_ERROR = "Failed to get appointment in %s method: getAppointmentByDoctorId\nGot parameters:\n\tdoctorId: %d\n";
    String GET_APPOINTMENT_BY_DOCTOR_ID_FOR_A_DAY_ERROR = "Failed to get appointment in %s method: getAppointmentByDoctorIdForADay\nGot parameters:\n\tdoctorId: %d\n\tWorkDay: %s\n";
    String GET_APPOINTMENT_BY_DOCTOR_ID_FOR_A_MONTH_ERROR = "Failed to get appointment in %s method: getAppointmentByDoctorIdForAMonth\nGot parameters:\n\tdoctorId: %d\n\tMonth: %s\n\tYear: %s\n";
    String GET_APPOINTMENT_BY_PATIENT_ID_ERROR = "Failed to get appointment in %s method: getAppointmentByPatientId\nGot parameters:\n\tpatientId: %d\n";
    String GET_APPOINTMENT_BY_PATIENT_FOR_A_DATE_ID_ERROR = "Failed to get appointment in %s method: getAppointmentByPatientIdForADate\nGot parameters:\n\tpatientId: %d\n\tDate: %s\n";
    String GET_APPOINTMENT_BY_WORK_DAY_ERROR = "Failed to get appointment in %s method: getAppointmentByWorkDay\nGot parameters:\n\tWorkDay: %s\n";
    String GET_APPOINTMENT_BY_WORK_DAY_ID_ERROR = "Failed to get appointment in %s method: getAppointmentByWorkDay\nGot parameters:\n\tworkDayId: %s\n";
    String CHANGE_APPOINTMENT_STATUS_ERROR = "Failed to edit appointment in %s method: changeAppointmentStatus\nGot parameters:\n\tappointmentId: %d\n\tstatus:%s\n";
    String EDIT_APPOINTMENT_ERROR = "Failed to edit appointment in %s method: editAppointment\nGot parameters:\n\tappointment: %s\n\tMap: %s\n";
    String START_APPOINTMENT_ERROR = "Failed to start appointment in %s method: startAppointment\nGot parameters:\n\tappointmentId: %d\n";
    String END_APPOINTMENT_ERROR = "Failed to end appointment in %s method: endAppointment\nGot parameters:\n\tappointmentId: %d\n      ";

    Appointment createAppointment(Appointment appointment);

    Appointment getAppointmentById(BigInteger id);

    void editAppointment(Appointment appointment);

    Collection<Appointment> getAppointmentByPatientId(BigInteger patientId);

    Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date);

    Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId);

    Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year);

    Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day);

    void startAppointment(BigInteger appointmentId);

    void endAppointment(BigInteger appointmentId);

    void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status);
    Collection<Appointment> getAppointmentByWorkDay(WorkDay day);
    Collection<Appointment> getAppointmentByWorkDay(BigInteger workDayId);
}
