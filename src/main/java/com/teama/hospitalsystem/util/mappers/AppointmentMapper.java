package com.teama.hospitalsystem.util.mappers;

import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.util.AppointmentUtil;
import org.springframework.jdbc.core.RowMapper;
import sun.security.pkcs.ParsingException;

import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentMapper implements RowMapper<Appointment> {

    Logger log = Logger.getLogger(AppointmentMapper.class.getName());
    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AppointmentUtil.formFromResultSet(rs);
    }
}
