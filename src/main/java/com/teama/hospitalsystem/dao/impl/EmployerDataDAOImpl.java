package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.EmployerDataDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.util.EmployerStatus;
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
import java.util.Collection;

@Repository
public class EmployerDataDAOImpl implements EmployerDataDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerDataDAOImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall updateJdbcCall;
    private final SimpleJdbcCall insertJdbcCall;

    @Autowired
    public EmployerDataDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName(CREATE_EMP_DATA_FUNCTION);
        this.updateJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(EDIT_EMPLOYER_DATA_PROCEDURE);
    }

    private final RowMapper<EmployerData> rowMapper = (rs, rowNum) -> new EmployerData(
            rs.getBigDecimal(EMPLOYER_ID).toBigInteger(),
            rs.getDate(HIRING_DATE),
            EmployerStatus.fromId(rs.getBigDecimal(STATUS).toBigInteger()),
            rs.getTime(START_WORKING_TIME),
            rs.getTime(END_WORKING_TIME));


    @Override
    public BigInteger createEmployerData(EmployerData employerData, BigInteger userId) throws DataAccessException{
        try{
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(HIRING_DATE, employerData.getHiringDate())
                    .addValue(STATUS, employerData.getStatus().getId())
                    .addValue(START_WORKING_TIME, employerData.getStartWorkingTime())
                    .addValue(END_WORKING_TIME, employerData.getEndWorkingTime())
                    .addValue(EMP_PARENT_ID, userId);

            return insertJdbcCall.executeFunction(BigDecimal.class, mapParameters).toBigInteger();

        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("EmployerDataDAOImpl error. EmployerData was not created.");
        }
    }

    @Override
    public void editEmployerData (EmployerData employerData) throws DataAccessException {
        try{
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(STATUS, employerData.getStatus().getId())
                    .addValue(START_WORKING_TIME, employerData.getStartWorkingTime())
                    .addValue(END_WORKING_TIME, employerData.getEndWorkingTime())
                    .addValue(EMPLOYER_ID, employerData.getEmployerDataId());

            this.updateJdbcCall.execute(mapParameters);

        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("EmployerDataDAOImpl error. EmployerData was not edited.");
        }
    }
    @Override
    public void changeEmployerStatus(EmployerData employerData) throws DataAccessException {
        try {
            jdbcTemplate.update(EDIT_EMP_STATUS, employerData.getStatus().getId(), employerData.getEmployerDataId());
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("EmployerDataDAOImpl error. EmployerStatus was not changed.");
        }
    }

    @Override
    public EmployerData getEmployerDataByUserId(BigInteger userId) throws DataAccessException{
        try{
            return jdbcTemplate.queryForObject(GET_EMP_DATA_BY_USER_ID, rowMapper, userId);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("EmployerDataDAOImpl error. Failed to get a EmployerData by UserID.");
        }
    }

    @Override
    public Collection<EmployerData> getEmployerListByStatus(EmployerStatus employerStatus) throws DataAccessException{
        return jdbcTemplate.query(GET_EMP_LIST_BY_STATUS, rowMapper, employerStatus.getId());
    }


    @Override
    public BigInteger getEmployerDataId(BigInteger userId) throws DataAccessException{
        return jdbcTemplate.queryForObject(GET_EMP_DATA_ID_BY_USER_ID, BigInteger.class, userId);
    }
}