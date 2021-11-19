package com.teama.hospitalsystem.util;

import com.teama.hospitalsystem.models.DoctorSpecialization;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorSpecializationRowMapper implements RowMapper<DoctorSpecialization> {

    @Override
    public DoctorSpecialization mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DoctorSpecialization(
                BigInteger.valueOf(rs.getInt("id")),
                rs.getString("title"));
    }
}
