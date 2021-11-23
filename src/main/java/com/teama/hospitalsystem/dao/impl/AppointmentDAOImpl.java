package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.AppointmentDAO;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.util.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    private final JdbcTemplate jdbcTemplate;

    public AppointmentDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Appointment> rowMapper = (rs, rowNum) -> {
        BigInteger id = BigInteger.valueOf(rs.getLong("ID"));
        BigInteger doctorId = BigInteger.valueOf(rs.getLong("DOCTORID"));
        return new Appointment.Builder(id, rs.getTimestamp("EXPSTART"), rs.getTimestamp("EXPEND"), doctorId,
                AppointmentStatus.fromId(BigInteger.valueOf(rs.getInt("STATUS"))))
                .withActualEnd(rs.getTimestamp("ACTEND"))
                .withActualStart(rs.getTimestamp("ACTSTART"))
                .withPatientId(BigInteger.valueOf(rs.getLong("PATIENTID")))
                .withDiagnosis(rs.getString("DIAGNOSIS"))
                .withReferral(rs.getString("REFERRAL"))
                .withNextAppointment(BigInteger.valueOf(rs.getLong("NXTAPP")))
                .build();
    };

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
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, rowMapper, id);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId) {
        return jdbcTemplate.query(SQL_GET_BY_DOCTOR_ID, rowMapper, doctorId);
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day) {
        return jdbcTemplate.query(SQL_GET_BY_DOCTOR_ID_AND_DAY, rowMapper, doctorId,
                        sdf.format(day.getDate()) + '*');
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year) {
        return jdbcTemplate.query(SQL_GET_BY_DOCTOR_ID_AND_DAY, rowMapper, doctorId, String.format("[1-31]-%s-%s*", month, year));
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientId(BigInteger patientId) {
        return jdbcTemplate.query(SQL_GET_BY_PATIENT_ID, rowMapper, patientId);
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date) {
        return jdbcTemplate.query(SQL_GET_BY_PATIENT_ID_AND_DATE, rowMapper, patientId,
                        sdf.format(date) + '*');
    }

    @Override
    public void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status) {
        jdbcTemplate.update(SQL_CHANGE_APPOINTMENT_STATUS, status.getId(), appointmentId);
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
        jdbcTemplate.update(SQL_APPOINTMENT_MANIPULATION, 21, appointmentId, Calendar.getInstance().getTime());
    }

    @Override
    public void endAppointment(BigInteger appointmentId) {
        jdbcTemplate.update(SQL_APPOINTMENT_MANIPULATION, 22, appointmentId, Calendar.getInstance().getTime());
    }
}
