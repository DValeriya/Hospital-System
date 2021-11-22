package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.GeneralInformationDAO;
import com.teama.hospitalsystem.models.GeneralInformation;
import com.teama.hospitalsystem.util.mappers.GeneralInformationMapper;
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
public class GeneralInformationDAOImpl implements GeneralInformationDAO {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall updateJdbcCall;
    private SimpleJdbcCall insertJdbcCall;
    private GeneralInformationMapper generalInformationMapper;

    @Autowired
    public void setGeneralInformationMapper(GeneralInformationMapper generalInformationMapper) {
        this.generalInformationMapper = generalInformationMapper;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.updateJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName(UPDATE_GEN_INFO_PROCEDURE);
        this.insertJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName(CREATE_GEN_INFO_PROCEDURE);
    }

    @Override
    public void editGeneralInformation(GeneralInformation generalInformation) throws DataAccessException {
        SqlParameterSource mapParameters = new MapSqlParameterSource()
                .addValue("GEN_OBJECT_ID", generalInformation.getGeneralInformationId())
                .addValue("GEN_ADDRESS", generalInformation.getAddress())
                .addValue("GEN_PHONE", generalInformation.getPhone())
                .addValue("GEN_WORKING_START", generalInformation.getWorkingStart())
                .addValue("GEN_WORKING_END", generalInformation.getWorkingEnd());

        this.updateJdbcCall.execute(mapParameters);
    }

    @Override
    public GeneralInformation getGeneralInformation(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_GET_GENERALINFO, generalInformationMapper, id);
    }

    @Override
    public void createGeneralInformation(GeneralInformation generalInformation) throws DataAccessException {
        SqlParameterSource mapParameters = new MapSqlParameterSource()
                .addValue("GEN_ADDRESS", generalInformation.getAddress())
                .addValue("GEN_PHONE", generalInformation.getPhone())
                .addValue("GEN_WORKING_START", generalInformation.getWorkingStart())
                .addValue("GEN_WORKING_END", generalInformation.getWorkingEnd())
                .addValue("NAME", "generalInformation-" + UUID.randomUUID());

        this.insertJdbcCall.execute(mapParameters);
    }
}