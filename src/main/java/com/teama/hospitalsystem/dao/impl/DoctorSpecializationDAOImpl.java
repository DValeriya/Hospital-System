package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.DoctorSpecializationDAO;
import com.teama.hospitalsystem.models.DoctorSpecialization;
import com.teama.hospitalsystem.util.DoctorSpecializationRowMapper;
import com.teama.hospitalsystem.util.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorSpecializationDAOImpl implements DoctorSpecializationDAO {

    private final JdbcTemplate jdbcTemplate;

    public DoctorSpecializationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createDoctorSpecialization(DoctorSpecialization specialization) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(CREATE_DOCTOR_SPEC_PROCEDURE_NAME);

            Map<String, Object> inParamMap = new HashMap<>();
            inParamMap.put("TITLE", specialization.getTitle());
            SqlParameterSource in = new MapSqlParameterSource(inParamMap);

            jdbcCall.execute(in);
        } catch (DataAccessException ex) {
            String str = ex.getMessage();
        }
    }

    @Override
    public void deleteDoctorSpecialization(DoctorSpecialization specialization) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(DELETE_DOCTOR_SPEC_PROCEDURE_NAME);

            Map<String, Object> inParamMap = new HashMap<>();
            inParamMap.put("SPEC_ID", specialization.getSpecializationId());
            SqlParameterSource in = new MapSqlParameterSource(inParamMap);

            jdbcCall.execute(in);
        } catch (DataAccessException ex) {
            String str = ex.getMessage();
        }
    }

    @Override
    public DoctorSpecialization getDoctorSpecializationById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_DOCTOR_SPEC_BY_ID,
                    new DoctorSpecializationRowMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<DoctorSpecialization> getDoctorSpecializationList() {
        try {
            return jdbcTemplate.query(SELECT_DOCTOR_SPECS,
                    new DoctorSpecializationRowMapper());
        } catch (DataAccessException ex) {
            return null;
        }
    }
}
