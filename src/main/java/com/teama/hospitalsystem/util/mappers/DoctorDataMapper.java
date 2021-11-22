package com.teama.hospitalsystem.util.mappers;

import com.teama.hospitalsystem.models.DoctorData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DoctorDataMapper implements RowMapper<DoctorData> {
    @Override
    public DoctorData mapRow(ResultSet rs, int rowNum) throws SQLException {
        DoctorData doctorData = new DoctorData();
        doctorData.setDoctorDataId(BigInteger.valueOf(rs.getLong("DOCTORDATA_ID")));
        doctorData.setAppointmentDuration(rs.getTime("APPOINTMENT_DURATION"));
        return doctorData;
    }
}
