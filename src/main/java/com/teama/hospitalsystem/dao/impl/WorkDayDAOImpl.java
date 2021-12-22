package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.WorkDayDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.WorkDay;
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
import java.util.*;
import java.util.Date;

@Repository
public class WorkDayDAOImpl implements WorkDayDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralInformationDAOImpl.class);

    public WorkDayDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<WorkDay> rowMapper = (rs, rowNum) -> new WorkDay(
            rs.getBigDecimal("WORK_DAY_ID").toBigInteger(),
            rs.getBigDecimal("EMPLOYER_ID").toBigInteger(),
            rs.getDate("WORK_DATE"));

    @Override
    public WorkDay createWorkDay(WorkDay workDay) throws DataAccessException {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withFunctionName(CREATE_WORK_DAY_PROCEDURE_NAME);
            Map<String, Object> params = new HashMap<>();
            params.put("EMPLOYER_ID", workDay.getEmployerId());
            params.put("WORK_DATE", workDay.getDate());
            SqlParameterSource in = new MapSqlParameterSource(params);
            workDay.setWorkDayId(jdbcCall.executeFunction(BigDecimal.class, in).toBigInteger());
            return workDay;
        } catch (DataAccessException dataAccessException) {
            String error = String.format("Failed to create workDay in %s method: createWorkDay\nGot parameters:\n\tid: %s\n",
                    WorkDayDAOImpl.class, workDay.toString());
            LOGGER.error(error, dataAccessException);
            throw new DAOException(error, dataAccessException);
        }
    }

    @Override
    public void deleteWorkDay(BigInteger workDayId) throws DataAccessException {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(DELETE_WORK_DAY_PROCEDURE_NAME);
            Map<String, Object> params = new HashMap<>();
            params.put("WORK_DAY_ID", workDayId);
            SqlParameterSource in = new MapSqlParameterSource(params);
            jdbcCall.execute(in);
        } catch (DataAccessException dataAccessException) {
            String error = String.format("Failed to delete workDay in %s method: deleteWorkDay\nGot parameters:\n\tworkDay: %d\n",
                    WorkDayDAOImpl.class, workDayId);
            LOGGER.error(error, dataAccessException);
            throw new DAOException(error, dataAccessException);
        }
    }

    @Override
    public Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId) throws DataAccessException {
        try {
            return jdbcTemplate.query(SELECT_WORK_DAYS_BY_EMPLOYERID, rowMapper, employerId);
        } catch (DataAccessException dataAccessException) {
            String error = String.format("Failed to get workDay in %s method: getWorkDaysByEmployerId\nGot parameters:\n\temployerId: %d\n",
                    WorkDayDAOImpl.class,employerId);
            LOGGER.error(error, dataAccessException);
            throw new DAOException(error, dataAccessException);
        }
    }

    @Override
    public Collection<WorkDay> getWorkDaysByDate(Date date) throws DataAccessException {
        try {
            return jdbcTemplate.query(SELECT_WORK_DAYS_BY_DATE, rowMapper, date);
        } catch (DataAccessException dataAccessException) {
            String error = String.format("Failed to get workDay in %s method: getWorkDaysByDate\nGot parameters:\n\tdate: %s\n",
                    WorkDayDAOImpl.class,date);
            LOGGER.error(error, dataAccessException);
            throw new DAOException(error, dataAccessException);
        }
    }
}