package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.DoctorDataDAO;
import com.teama.hospitalsystem.models.DoctorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class DoctorDataDAOImpl implements DoctorDataDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall updateJdbcCall;
    private final SimpleJdbcCall insertJdbcCall;

    public final RowMapper<DoctorData> mapRow = (ResultSet rs, int rowNum) -> {
        DoctorData doctorData = new DoctorData();
        doctorData.setDoctorDataId(BigInteger.valueOf(rs.getLong("DOCTORDATA_ID")));
        doctorData.setAppointmentDuration(rs.getTime("APPOINTMENT_DURATION"));
        return doctorData;
    };

    @Autowired
    public DoctorDataDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.updateJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(UPDATE_DOCTORDATA_PROCEDURE);
        this.insertJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(CREATE_DOCTORDATA_PROCEDURE);
    }

    @Override
    public void createDoctorData(DoctorData doctorData, BigInteger employerId) throws DataAccessException {
        SqlParameterSource mapParameters = new MapSqlParameterSource()
                .addValue("APPOINTMENT_DURATION", doctorData.getAppointmentDuration())
                .addValue("PARENT", employerId)
                .addValue("SPECIALIZATION", doctorData.getSpec().getSpecializationId());

        this.insertJdbcCall.execute(mapParameters);
    }

    @Override
    public void editDoctorData(DoctorData doctorData) throws DataAccessException {
        SqlParameterSource mapParameters = new MapSqlParameterSource()
                .addValue("DOCTORDATA_OBJECT_ID", doctorData.getDoctorDataId())
                .addValue("DOCTORDATA_APPOINTMENTDURATION", doctorData.getAppointmentDuration())
                .addValue("SPECIALIZATION", doctorData.getSpec().getSpecializationId());

        this.updateJdbcCall.execute(mapParameters);
    }

    @Override
    public DoctorData getDoctorDataByDoctorId(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_GET_DOCTORDATA_BY_DOCTOR_ID, mapRow, id);
    }

    @Override
    public DoctorData getDoctorDataId(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_GET_DOCTORDATA_ID, mapRow, id);
    }

    @Override
    public List<DoctorData> getDoctorListBySpecialization(BigInteger specializationId) {
        return jdbcTemplate.query(SQL_GET_DOCTOR_LIST_BY_SPECIALIZATION, mapRow, specializationId);
    }
}
