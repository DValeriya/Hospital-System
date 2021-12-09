package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.DoctorDataDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.DoctorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class DoctorDataDAOImpl implements DoctorDataDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall updateJdbcCall;
    private final SimpleJdbcCall insertJdbcCall;
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDataDAOImpl.class);

    public final RowMapper<DoctorData> mapRow = (ResultSet rs, int rowNum) -> {
        DoctorData doctorData = new DoctorData();
        doctorData.setDoctorDataId(rs.getBigDecimal(DOCTORDATA_ID).toBigInteger());
        doctorData.setAppointmentDuration(rs.getTime(APPOINTMENT_DURATION));
        return doctorData;
    };

    @Autowired
    public DoctorDataDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.updateJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(UPDATE_DOCTORDATA_PROCEDURE);
        this.insertJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName(CREATE_DOCTORDATA_FUNCTION);
    }

    @Override
    public BigInteger createDoctorData(DoctorData doctorData, BigInteger employerId) throws DataAccessException {
        try{
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(APPOINTMENT_DURATION, doctorData.getAppointmentDuration())
                    .addValue(DOCTOR_DATA_PARENT_ID, employerId)
                    .addValue(SPECIALIZATION_ID, doctorData.getSpec().getSpecializationId());

            return insertJdbcCall.executeFunction(BigDecimal.class, mapParameters).toBigInteger();
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("DoctorDataDAOImpl error. DoctorData  was not created");
        }
    }

    @Override
    public void editDoctorData(DoctorData doctorData) throws DataAccessException {
        try {
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(DOCTORDATA_OBJECT_ID, doctorData.getDoctorDataId())
                    .addValue(DOCTORDATA_APPOINTMENTDURATION, doctorData.getAppointmentDuration())
                    .addValue(SPECIALIZATION_ID, doctorData.getSpec().getSpecializationId());

            this.updateJdbcCall.execute(mapParameters);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("DoctorDataDAOImpl error. DoctorData was not edited");
        }
    }

    @Override
    public DoctorData getDoctorDataByDoctorId(BigInteger id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_DOCTORDATA_BY_DOCTOR_ID, mapRow, id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("DoctorDataDAOImpl error. Failed to get a doctor by ID");
        }
    }

    @Override
    public DoctorData getDoctorDataId(BigInteger id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_DOCTORDATA_ID, mapRow, id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("DoctorDataDAOImpl error. Failed to get a doctorData by ID");
        }
    }

    @Override
    public List<DoctorData> getDoctorListBySpecialization(BigInteger specializationId) {
        return jdbcTemplate.query(SQL_GET_DOCTOR_LIST_BY_SPECIALIZATION, mapRow, specializationId);
    }
}
