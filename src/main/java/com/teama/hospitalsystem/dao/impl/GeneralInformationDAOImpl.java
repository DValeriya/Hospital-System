package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.GeneralInformationDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.GeneralInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;

@Repository
public class GeneralInformationDAOImpl implements GeneralInformationDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall updateJdbcCall;
    private final SimpleJdbcCall insertJdbcCall;
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralInformationDAOImpl.class);

    public RowMapper<GeneralInformation> mapRow = (ResultSet rs, int rowNum) -> {
        return new GeneralInformation(
                BigInteger.valueOf(rs.getLong("GENERALINFORMATION_ID")),
                rs.getString("ADDRESS"),
                rs.getString("PHONE"),
                rs.getTime("WORKING_START"),
                rs.getTime("WORKING_END"));
    };

    @Autowired
    public GeneralInformationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.updateJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(UPDATE_GEN_INFO_PROCEDURE);
        this.insertJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(CREATE_GEN_INFO_PROCEDURE);
    }

    @Override
    public void editGeneralInformation(GeneralInformation generalInformation) throws DataAccessException {
        try {
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue("GEN_OBJECT_ID", generalInformation.getGeneralInformationId())
                    .addValue("GEN_ADDRESS", generalInformation.getAddress())
                    .addValue("GEN_PHONE", generalInformation.getPhone())
                    .addValue("GEN_WORKING_START", generalInformation.getWorkingStart())
                    .addValue("GEN_WORKING_END", generalInformation.getWorkingEnd());

            this.updateJdbcCall.execute(mapParameters);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("GeneralInformationDAOImpl error. GeneralInformation was not edited");
        }
    }

    @Override
    public GeneralInformation getGeneralInformation(BigInteger id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_GENERALINFO, mapRow, id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("GeneralInformationDAOImpl error. GeneralInformation Failed to get a information by ID");
        }

    }

    @Override
    public void createGeneralInformation(GeneralInformation generalInformation) throws DataAccessException {
        try {
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue("GEN_ADDRESS", generalInformation.getAddress())
                    .addValue("GEN_PHONE", generalInformation.getPhone())
                    .addValue("GEN_WORKING_START", generalInformation.getWorkingStart())
                    .addValue("GEN_WORKING_END", generalInformation.getWorkingEnd());

            this.insertJdbcCall.execute(mapParameters);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("GeneralInformationDAOImpl error. GeneralInformation was not created");
        }
    }
}