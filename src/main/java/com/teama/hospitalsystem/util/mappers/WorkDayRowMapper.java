package com.teama.hospitalsystem.util.mappers;

import org.springframework.jdbc.core.RowMapper;
import com.teama.hospitalsystem.models.WorkDay;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;


public class WorkDayRowMapper implements RowMapper<WorkDay> {

    @Override
    public WorkDay mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WorkDay(
                BigInteger.valueOf(rs.getInt("workDayId")),
                BigInteger.valueOf(rs.getInt("employerId")),
                rs.getDate("work_date"));
    }
}
