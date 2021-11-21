package com.teama.hospitalsystem.util.mappers;

import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.util.EmployerStatus;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployerRowMapper implements RowMapper<EmployerData> {

    @Override
    public EmployerData mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EmployerData(
                BigInteger.valueOf(rs.getInt("ID")),
                rs.getDate("HIRING_DATE"),
                EmployerStatus.fromId(BigInteger.valueOf(rs.getInt("STATUS"))),
                rs.getTime("START_WORKING_TIME"),
                rs.getTime("END_WORKING_TIME"));
    }
}
