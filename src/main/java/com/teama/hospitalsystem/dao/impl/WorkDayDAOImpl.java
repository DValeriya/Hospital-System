package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.WorkDayDAO;
import com.teama.hospitalsystem.models.WorkDay;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.*;
import java.util.Date;

@Repository
public class WorkDayDAOImpl implements WorkDayDAO {
    private final JdbcTemplate jdbcTemplate;

    public WorkDayDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<WorkDay> rowMapper = (rs, rowNum) -> new WorkDay(
            rs.getBigDecimal("workDayId").toBigInteger(),
            rs.getBigDecimal("employerId").toBigInteger(),
            rs.getDate("work_date"));

    @Override
    public void createWorkDay(WorkDay workDay) throws DataAccessException {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(CREATE_WORK_DAY_PROCEDURE_NAME);
        Map<String, Object> params = new HashMap<>();
        params.put("EMPLOYER_ID", workDay.getEmployerId());
        params.put("WORK_DATE", workDay.getDate());
        SqlParameterSource in = new MapSqlParameterSource(params);
        jdbcCall.execute(in);
    }

    @Override
    public void deleteWorkDay(WorkDay workDay) throws DataAccessException {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(DELETE_WORK_DAY_PROCEDURE_NAME);
        Map<String, Object> params = new HashMap<>();
        params.put("WORK_DAY_ID", workDay.getWorkDayId());
        SqlParameterSource in = new MapSqlParameterSource(params);
        jdbcCall.execute(in);
    }

    @Override
    public Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId) throws DataAccessException {
        return jdbcTemplate.query(SELECT_WORK_DAYS_BY_EMPLOYERID, rowMapper, employerId);
    }

    @Override
    public Collection<WorkDay> getWorkDaysByDate(Date date) throws DataAccessException {
        return jdbcTemplate.query(SELECT_WORK_DAYS_BY_DATE, rowMapper, date);
    }
}