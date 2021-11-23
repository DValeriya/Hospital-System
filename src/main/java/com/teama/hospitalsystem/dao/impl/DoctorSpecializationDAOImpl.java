package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.DoctorSpecializationDAO;
import com.teama.hospitalsystem.models.DoctorSpecialization;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DoctorSpecializationDAOImpl implements DoctorSpecializationDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<DoctorSpecialization> rowMapper = (rs, rowNum) -> {
        BigInteger id = rs.getBigDecimal("id").toBigInteger();
        return new DoctorSpecialization(id, rs.getString("title"));
    };


    public DoctorSpecializationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createDoctorSpecialization(DoctorSpecialization specialization) throws DataAccessException {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(CREATE_DOCTOR_SPEC_PROCEDURE_NAME);

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("TITLE", specialization.getTitle());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        jdbcCall.execute(in);
    }

    @Override
    public void deleteDoctorSpecialization(DoctorSpecialization specialization) throws DataAccessException {
        Object[] args = new Object[] { specialization.getSpecializationId() };
        jdbcTemplate.update(DELETE_DOCTOR_SPEC_BY_ID, args);
    }

    @Override
    public DoctorSpecialization getDoctorSpecializationById(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SELECT_DOCTOR_SPEC_BY_ID, rowMapper, id);
    }

    @Override
    public DoctorSpecialization getDoctorSpecializationByDoctorDataId(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SELECT_DOCTOR_SPEC_BY_DOC_DATA_ID, rowMapper, id);
    }

    @Override
    public Collection<DoctorSpecialization> getDoctorSpecializationList() throws DataAccessException {
            return jdbcTemplate.query(SELECT_DOCTOR_SPECS, rowMapper);
    }
}
