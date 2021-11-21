package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.AppointmentDAO;
import com.teama.hospitalsystem.util.mappers.AppointmentMapper;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.util.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;
    private Logger log = Logger.getLogger(AppointmentDAOImpl.class.getName());

    private final String sqlGetById = "SELECT * FROM (\n" +
            "    SELECT ATTRIBUTES.OBJECT_ID, ATTRIBUTES.VALUE, ATTRIBUTES.ATTR_ID, PATIENTID.OBJECT_ID AS PATIENID,  DOCTORID.OBJECT_ID AS DOCTORID, STATUS.LIST_VALUE_ID AS STATUS FROM ATTRIBUTES\n" +
            "        LEFT JOIN OBJREFERENCE PATIENTID ON PATIENTID.REFERENCE = ? AND PATIENTID.ATTR_ID = 24\n" +
            "        LEFT JOIN OBJREFERENCE DOCTORID ON DOCTORID.REFERENCE = ? AND DOCTORID.ATTR_ID = 23\n" +
            "        FULL OUTER JOIN ATTRIBUTES STATUS ON STATUS.ATTR_ID = 28 AND STATUS.OBJECT_ID = ? \n" +
            "    WHERE  ATTRIBUTES.OBJECT_ID=? AND NOT (ATTRIBUTES.ATTR_ID = 28)\n" +
            "    )\n" +
            "PIVOT (MAX(VALUE) FOR ATTR_ID IN (19,20,21,22,25,26,27))";

    String getList1 = "WITH OBJ AS\n" +
            "    (\n" +
            "        SELECT OBJECT_ID FROM OBJECTS WHERE OBJECT_TYPE_ID = 5\n" +
            "    )\n" +
            "SELECT * FROM (\n" +
            "    SELECT OBJ.OBJECT_ID, ATTRIBUTES.VALUE, ATTRIBUTES.ATTR_ID, PATIENTID.OBJECT_ID AS PATIENID,  DOCTORID.OBJECT_ID AS DOCTORID, STATUS.LIST_VALUE_ID AS STATUS FROM ATTRIBUTES, OBJ\n" +
            "        LEFT JOIN OBJREFERENCE PATIENTID ON PATIENTID.REFERENCE = OBJ.OBJECT_ID AND PATIENTID.ATTR_ID = 24\n" +
            "        LEFT JOIN OBJREFERENCE DOCTORID ON DOCTORID.REFERENCE = OBJ.OBJECT_ID AND DOCTORID.ATTR_ID = 23\n" +
            "        FULL OUTER JOIN ATTRIBUTES STATUS ON STATUS.ATTR_ID = 28 AND STATUS.OBJECT_ID = OBJ.OBJECT_ID\n";
    String getList2 =
            "    WHERE  ATTRIBUTES.OBJECT_ID = OBJ.OBJECT_ID AND NOT (ATTRIBUTES.ATTR_ID = 28)  \n";
    String getList3 = "    )\n PIVOT (MAX(VALUE) FOR ATTR_ID IN (19,20,21,22,25,26,27))";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void createAppointment(Appointment appointment){
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("Create_Appointment");
        Map<String, Object> params = new HashMap<>();
        params.put("EXPSTART", sdf.format(appointment.getExpectedStart()));
        params.put("EXPEND", sdf.format(appointment.getExpectedEnd()));

        if (appointment.getActualStart() != null) {
            params.put("ACTSTART", sdf.format(appointment.getActualStart()));
        }else  params.put("ACTSTART", (appointment.getActualStart()));

        if (appointment.getActualEnd() != null){
            params.put("ACTEND", sdf.format(appointment.getActualEnd()));
        }else params.put("ACTEND", (appointment.getActualEnd()));

        params.put("DOCTORID", appointment.getDoctorId());
        params.put("PATIENTID", appointment.getPatientId());
        params.put("DIAGNOSIS", appointment.getDiagnosis());
        params.put("REFFERAL", appointment.getReferral());
        params.put("NXTAPP", appointment.getNextAppointment());
        params.put("STATUS", String.valueOf(appointment.getStatus().getId()));
        SqlParameterSource in = new MapSqlParameterSource(params);
        jdbcCall.execute(in);
    }

    @Override
    public Appointment getAppointmentById(BigInteger id) {
        return jdbcTemplate.queryForObject(sqlGetById, new AppointmentMapper(), id, id, id, id);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId) {
        return jdbcTemplate.query(getList1 + getList2 + "AND DOCTORID.OBJECT_ID = ?" + getList3, new AppointmentMapper(), doctorId);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day) {
        return jdbcTemplate.query(getList1 +
                        "Left JOIN ATTRIBUTES \"DATE\" ON \"DATE\".OBJECT_ID = OBJ.OBJECT_ID AND \"DATE\".ATTR_ID = 19"+
                        getList2 +"AND DOCTORID.OBJECT_ID = ? AND regexp_like(\"DATE\".VALUE, ?) "
                        + getList3, new AppointmentMapper(), doctorId,
                        sdf.format(day.getDate()) + '*');
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year) {
        return jdbcTemplate.query(getList1 +
                        "Left JOIN ATTRIBUTES \"DATE\" ON \"DATE\".OBJECT_ID = OBJ.OBJECT_ID AND \"DATE\".ATTR_ID = 19"+
                        getList2 +"AND DOCTORID.OBJECT_ID = ? AND regexp_like(\"DATE\".VALUE, ?) "
                        + getList3, new AppointmentMapper(), doctorId, "[1-31]/" + month + '/' + year  + '*');
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientId(BigInteger patientId) {
        return jdbcTemplate.query(getList1 + getList2 + "AND PATIENTID.OBJECT_ID = ?" + getList3, new AppointmentMapper(), patientId);
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date) {
        return jdbcTemplate.query(getList1 +
                        "Left JOIN ATTRIBUTES \"DATE\" ON \"DATE\".OBJECT_ID = OBJ.OBJECT_ID AND \"DATE\".ATTR_ID = 19"+
                        getList2 +"AND DOCTORID.OBJECT_ID = ? AND regexp_like(\"DATE\".VALUE, ?) "
                        + getList3, new AppointmentMapper(), patientId,
                        sdf.format(date) + '*');
    }

    @Override
    public void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status) {
        String ps = "UPDATE ATTRIBUTES\n" +
                "SET LIST_VALUE_ID = " + status.getId() + "\n" +
                "WHERE OBJECT_ID = "+ appointmentId + " AND ATTR_ID = 28";
        jdbcTemplate.execute(ps);
    }

    @Override
    public void editAppointment(Map<String, Object> params) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("Update_Appointment");
        SqlParameterSource in = new MapSqlParameterSource(params);
        jdbcCall.execute(in);
    }

    @Override
    public void startAppointment(BigInteger appointmentId) {
        Date c = new Date();
        String ps = String.format("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) VALUES (21, %s, '%s')",
                appointmentId, sdf.format(c));
        jdbcTemplate.execute(ps);
    }

    @Override
    public void endAppointment(BigInteger appointmentId) {
        Date c = new Date();
        String ps = String.format("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) VALUES (22, %s, '%s')",
                appointmentId, sdf.format(c));
        jdbcTemplate.execute(ps);
    }
}
