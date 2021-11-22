package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.DoctorDataDAO;
import com.teama.hospitalsystem.models.DoctorData;
import com.teama.hospitalsystem.util.mappers.DoctorDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.UUID;

@Repository
public class DoctorDataDAOImpl implements DoctorDataDAO {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall updateJdbcCall;
    private SimpleJdbcCall insertJdbcCall;
    private DoctorDataMapper doctorDataMapper;

    @Autowired
    public void setDoctorDataMapper(DoctorDataMapper doctorDataMapper) {
        this.doctorDataMapper = doctorDataMapper;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.updateJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName(UPDATE_DOCTORDATA_PROCEDURE);
        this.insertJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName(CREATE_DOCTORDATA_PROCEDURE);
    }


    @Override
    public void createDoctorData(DoctorData doctorData, BigInteger employerId) throws DataAccessException {
        SqlParameterSource mapParameters = new MapSqlParameterSource()
                .addValue("APPOINTMENT_DURATION", doctorData.getAppointmentDuration())
                .addValue("PARENT", employerId)
                .addValue("SPECIALIZATION", doctorData.getSpec().getSpecializationId())
                .addValue("NAME", "doctorData-" + UUID.randomUUID());

        this.insertJdbcCall.execute(mapParameters);
    }

    @Override
    public void editDoctorData(DoctorData doctorData) throws DataAccessException {
        SqlParameterSource mapParameters = new MapSqlParameterSource()
                .addValue("DOCTORDATA_OBJECT_ID", doctorData.getDoctorDataId())
                .addValue("DOCTORDATA_APPOINTMENTDURATION", doctorData.getAppointmentDuration())
                .addValue("SPECIALIZATION", doctorData.getSpec().getSpecializationId());

        this.updateJdbcCall.execute(mapParameters);
    }

    @Override
    public DoctorData getDoctorDataByDoctorId(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_GET_DOCTORDATA_BY_DOCTOR_ID, doctorDataMapper, id);
    }

    @Override
    public DoctorData getDoctorDataId(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_GET_DOCTORDATA_ID, doctorDataMapper, id);
    }
}
