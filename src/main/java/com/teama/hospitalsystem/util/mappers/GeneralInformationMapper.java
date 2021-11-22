package com.teama.hospitalsystem.util.mappers;

import com.teama.hospitalsystem.models.GeneralInformation;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class GeneralInformationMapper implements RowMapper<GeneralInformation> {

    @Override
    public GeneralInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GeneralInformation(
                BigInteger.valueOf(rs.getLong("GENERALINFORMATION_ID")),
                rs.getString("ADDRESS"),
                rs.getString("PHONE"),
                rs.getTime("WORKING_START"),
                rs.getTime("WORKING_END"));
    }
}
