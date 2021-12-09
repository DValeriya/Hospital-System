package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.DoctorSpecializationDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.DoctorSpecialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorSpecializationDAOImpl.class);

    private final RowMapper<DoctorSpecialization> rowMapper = (rs, rowNum) -> {
        BigInteger id = rs.getBigDecimal(ID_DOCTOR_SPECIALIZATION).toBigInteger();
        return new DoctorSpecialization(id, rs.getString(TITLE));
    };


    public DoctorSpecializationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigInteger createDoctorSpecialization(DoctorSpecialization specialization) throws DataAccessException {
       try {
           SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                   .withProcedureName(CREATE_DOCTOR_SPEC_PROCEDURE_NAME);

           Map<String, Object> inParamMap = new HashMap<>();
           inParamMap.put(TITLE_PARAM, specialization.getTitle());
           SqlParameterSource in = new MapSqlParameterSource(inParamMap);
           
           return jdbcCall.executeFunction(BigInteger.class, in);
       } catch (DataAccessException dataAccessException) {
           LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
           throw new DAOException("DoctorSpecializationDAOImpl error. DoctorSpecialization was not created");
       }
    }

    @Override
    public void deleteDoctorSpecialization(DoctorSpecialization specialization) throws DataAccessException {
        try {
            Object[] args = new Object[] { specialization.getSpecializationId() };
            jdbcTemplate.update(DELETE_DOCTOR_SPEC_BY_ID, args);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("DoctorSpecializationDAOImpl error. DoctorSpecialization was not deleted");
        }
    }

    @Override
    public DoctorSpecialization getDoctorSpecializationById(BigInteger id) throws DataAccessException {
        try {
            System.out.println(SELECT_DOCTOR_SPEC_BY_ID);
            System.out.println(id);
            return jdbcTemplate.queryForObject(SELECT_DOCTOR_SPEC_BY_ID, rowMapper, id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("DoctorSpecializationDAOImpl error. DoctorSpecialization failed to get a specialization by ID");
        }
    }

    @Override
    public DoctorSpecialization getDoctorSpecializationByDoctorDataId(BigInteger id) throws DataAccessException {
       try {
           return jdbcTemplate.queryForObject(SELECT_DOCTOR_SPEC_BY_DOC_DATA_ID, rowMapper, id);
       } catch (DataAccessException dataAccessException) {
           LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
           throw new DAOException("DoctorSpecializationDAOImpl error. DoctorSpecialization failed to get a specialization by doctor data ID");
       }
    }

    @Override
    public Collection<DoctorSpecialization> getDoctorSpecializationList() throws DataAccessException {
            return jdbcTemplate.query(SELECT_DOCTOR_SPECS, rowMapper);
    }
}
