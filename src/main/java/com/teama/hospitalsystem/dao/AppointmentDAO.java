package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.util.AppointmentStatus;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.Month;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AppointmentDAO {

    String sqlGetById =
            "SELECT APPOINTMENT.OBJECT_ID AS ID, EXPECTEDSTART.DATE_VALUE AS EXPSTART, EXPECTEDEND.DATE_VALUE AS EXPEND,\n" +
                    "       ACTUALSTART.DATE_VALUE AS ACTSTART, ACTUALEND.DATE_VALUE AS ACTEND, DOCTORID.OBJECT_ID AS DOCTORID,\n" +
                    "       PATIENTID.OBJECT_ID AS PATIENTID, DIAGNOSIS.VALUE AS DIAGNOSIS, REFERRAL.VALUE AS REFERRAL,\n" +
                    "       NXTAPP.OBJECT_ID AS NXTAPP, STATUS.LIST_VALUE_ID AS STATUS FROM OBJECTS APPOINTMENT\n" +
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
                    "WHERE APPOINTMENT.OBJECT_ID = ? AND OBJECT_TYPE_ID = 5";

    String sqlGetList =
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

    String sqlGetByDoctorId = sqlGetList + " AND DOCTORID.OBJECT_ID = ?";

    String sqlGetByDoctorIdAndDay = sqlGetByDoctorId + "AND regexp_like(EXPECTEDSTART.DATE_VALUE, '?')";

    String sqlGetByPatientId = sqlGetList + "AND PATIENT.OBJECT_ID = ?";

    String sqlGetByPatientIdForDay = sqlGetByPatientId + "AND regexp_like(EXPECTEDSTART.DATE_VALUE, '?')";

    String sqlAppointmentManipulation = "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE) VALUES (?, ?, ?)";
    String sqlChangeAppointmentStatus = "UPDATE ATTRIBUTES SET LIST_VALUE_ID = ? WHERE OBJECT_ID = ? AND ATTR_ID = 28";

    void createAppointment(Appointment appointment) throws SQLException;

    Appointment getAppointmentById(BigInteger id);

    void editAppointment(Map<String, Object> params);

    Collection<Appointment> getAppointmentByPatientId(BigInteger patientId);

    Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date);

    Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId);

    Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year);

    Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day);

    void startAppointment(BigInteger appointmentId);

    void endAppointment(BigInteger appointmentId);

    void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status);
}
