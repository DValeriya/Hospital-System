package com.teama.hospitalsystem.util;

import com.teama.hospitalsystem.models.Appointment;
import sun.security.pkcs.ParsingException;

import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentUtil {
    static Logger log = Logger.getLogger(AppointmentUtil.class.getName());
    public static Appointment formFromResultSet(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String actualStart = rs.getString("21");
        String actualEnd = rs.getString("22");
        appointment.setId(BigInteger.valueOf(rs.getInt("OBJECT_ID")));
        appointment.setPatientId(BigInteger.valueOf(rs.getInt("PATIENID")));
        appointment.setDiagnosis(rs.getString("25"));
        appointment.setReferral(rs.getString("26"));
        appointment.setNextAppointment(BigInteger.valueOf(rs.getInt("27")));
        appointment.setStatus(AppointmentStatus.fromId(BigInteger.valueOf(rs.getInt("STATUS"))));
        appointment.setDoctorId(BigInteger.valueOf(rs.getInt("DOCTORID")));
        try {
            appointment.setExpectedStart(sdf.parse(rs.getString("19")));
            appointment.setExpectedEnd(sdf.parse(rs.getString("20")));
            if(actualStart != null){
                appointment.setActualStart(sdf.parse(actualStart));
            }
            if(actualEnd != null){
                appointment.setActualEnd(sdf.parse(actualEnd));
            }
        } catch (ParseException e) {
            log.log(Level.WARNING, e.getMessage());
            throw new UncheckedIOException(new ParsingException());
        }
        return appointment;
    }
}
