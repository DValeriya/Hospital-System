package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.AppointmentDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.util.AppointmentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public AppointmentDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Appointment> rowMapper  = (rs, rowNum)  -> {
        BigInteger id = BigInteger.valueOf(rs.getLong(ID));
        BigInteger doctorId = BigInteger.valueOf(rs.getLong(DOCTOR_ID));
        return new Appointment.Builder(id, rs.getTimestamp(EXPSTART), rs.getTimestamp(EXPEND), doctorId,
                AppointmentStatus.fromId(BigInteger.valueOf(rs.getInt(STATUS))))
                .withActualEnd(rs.getTimestamp(ACTEND))
                .withActualStart(rs.getTimestamp(ACTSTART))
                .withPatientId(BigInteger.valueOf(rs.getLong(PATIENT_ID)))
                .withDiagnosis(rs.getString(DIAGNOSIS))
                .withReferral(rs.getString(REFERRAL))
                .withNextAppointment(BigInteger.valueOf(rs.getLong(NXTAPP)))
                .build();
    };

    @Override
    public Appointment createAppointment(Appointment appointment) throws DataAccessException {
        Map<?,?> map = fillHashMap(appointment);
        try{
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withFunctionName("Create_Appointment");
            SqlParameterSource in = new MapSqlParameterSource();
            appointment.setId(jdbcCall.executeFunction(BigDecimal.class,in).toBigInteger());
            return appointment;
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tAppointment: " + appointment.toString() +"\nMap: " + convertWithStream(map), ex);
            throw new DAOException("AppointmentDAOImpl error. Appointment was not created.\n", ex);
        }

    }

    @Override
    public Appointment getAppointmentById(BigInteger id) throws DataAccessException {
        try{
            return jdbcTemplate.queryForObject(SQL_GET_BY_ID, rowMapper, id);
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tId: " + id.toString(), ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to get information by id.\n", ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorId(BigInteger doctorId) throws DataAccessException {
        try{
            return jdbcTemplate.query(SQL_GET_BY_DOCTOR_ID, rowMapper, doctorId);
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tId: " + doctorId.toString(), ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to get information by doctorId.\n", ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForADay(BigInteger doctorId, WorkDay day) throws DataAccessException {
        try{
            return jdbcTemplate.query(SQL_GET_BY_DOCTOR_ID_AND_DAY, rowMapper, day.getWorkDayId(),
                    doctorId);
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tId: " + doctorId.toString() + "\n\tWorkDay: " + day.toString(), ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to get information by doctorId and by WorkDay.\n", ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByDoctorIdForAMonth(BigInteger doctorId, int month, int year) throws DataAccessException {
        try{
            return jdbcTemplate.query(SQL_GET_BY_DOCTOR_ID_AND_DAY, rowMapper, doctorId, String.format("[1-31]-%s-%s*", month, year));
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tId: " + doctorId.toString() + "\n\tMonth: " + month + "\n\tYear: " + year, ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to ged information by doctorId and Date\n", ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientId(BigInteger patientId) throws DataAccessException {

        try{
            return jdbcTemplate.query(SQL_GET_BY_PATIENT_ID, rowMapper, patientId);
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tId: " + patientId.toString(), ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to get information by patientId.\n", ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByPatientIdForADate(BigInteger patientId, Date date) throws DataAccessException {
        try{
            return jdbcTemplate.query(SQL_GET_BY_PATIENT_ID_AND_DATE, rowMapper, patientId,
                    sdf.format(date) + '*');
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tId: " + patientId.toString() + "\n\tDate: " + date.toString(), ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to get information by patientId and Date.\n", ex);
        }
    }

    @Override
    public void changeAppointmentStatus(BigInteger appointmentId, AppointmentStatus status) throws DataAccessException {
        try{
            jdbcTemplate.update(SQL_CHANGE_APPOINTMENT_STATUS, status.getId(), appointmentId);
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tId: " + appointmentId.toString() + "\n\tStatus: " + status.toString(), ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to change appointment status.\n", ex);
        }
    }

    @Override
    public Collection<Appointment> getAppointmentByWorkDay(WorkDay day) throws DataAccessException {
        try{
            return jdbcTemplate.query(SQL_GET_BY_WORKDAY, rowMapper, day.getWorkDayId());
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tWorkDay: " + day.toString(), ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to get information by WorkDay.\n", ex);
        }
    }
    @Override
    public Collection<Appointment> getAppointmentByWorkDay(BigInteger workDayId) throws DataAccessException {
        try{
            return jdbcTemplate.query(SQL_GET_BY_WORKDAY, rowMapper, workDayId);
        }catch (DataAccessException ex){
            LOGGER.error("Got parameters:\n\tWorkDayId: " + workDayId.toString(), ex);
            throw new DAOException("AppointmentDAOImpl error. Failed to get information by WorkDay.\n", ex);
        }
    }

    @Override
    public void editAppointment(Appointment appointment) throws DataAccessException {
        try{
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("Update_Appointment");
            SqlParameterSource in = new MapSqlParameterSource(fillHashMap(appointment));
            jdbcCall.execute(in);
        }catch (DataAccessException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentDAOImpl error. Failed to edit information by id.\n", ex);
        }
    }

    @Override
    public void startAppointment(BigInteger appointmentId) throws DataAccessException {
        try{
            jdbcTemplate.update(SQL_APPOINTMENT_MANIPULATION, 21, appointmentId, Calendar.getInstance().getTime());
        }catch (DataAccessException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentDAOImpl error. Failed to start appointment.\n", ex);
        }
    }

    @Override
    public void endAppointment(BigInteger appointmentId) throws DataAccessException {
        try{
            jdbcTemplate.update(SQL_APPOINTMENT_MANIPULATION, 21, appointmentId, Calendar.getInstance().getTime());
        }catch (DataAccessException ex){
            LOGGER.error(ex.getMessage());
            throw new DAOException("AppointmentDAOImpl error. Failed to end appointment.\n", ex);
        }
    }

    private Map<String, Object> fillHashMap(Appointment appointment){
        Map<String, Object> toReturn = new HashMap<>();
        toReturn.put(EXPSTART, sdf.format(appointment.getExpectedStart()));
        toReturn.put(EXPEND, sdf.format(appointment.getExpectedEnd()));
        if (appointment.getActualStart() != null) {
            toReturn.put(ACTSTART, sdf.format(appointment.getActualStart()));
        }else  toReturn.put(ACTSTART, (appointment.getActualStart()));
        if (appointment.getActualEnd() != null){
            toReturn.put(ACTEND, sdf.format(appointment.getActualEnd()));
        }else toReturn.put(ACTEND, (appointment.getActualEnd()));
        toReturn.put(DOCTOR_ID, appointment.getDoctorId());
        toReturn.put(PATIENT_ID, appointment.getPatientId());
        toReturn.put(DIAGNOSIS, appointment.getDiagnosis());
        toReturn.put(REFERRAL, appointment.getReferral());
        toReturn.put(NXTAPP, appointment.getNextAppointment());
        toReturn.put(STATUS, String.valueOf(appointment.getStatus().getId()));
        return toReturn;
    }
    private String convertWithStream(Map<?, ?> map) {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
