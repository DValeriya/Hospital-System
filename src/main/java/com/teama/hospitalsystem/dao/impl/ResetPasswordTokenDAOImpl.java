package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.ResetPasswordTokenDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.ResetPasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;

@Repository
public class ResetPasswordTokenDAOImpl implements ResetPasswordTokenDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall saveJdbcCall;
    private final SimpleJdbcCall updateJdbcCall;
    private final SimpleJdbcCall deleteJdbcCall;
    private static final Logger LOGGER = LoggerFactory.getLogger(ResetPasswordTokenDAOImpl.class);
    private final RowMapper<ResetPasswordToken> rowMapper = (rs, rowNum) -> {
        BigInteger id = rs.getBigDecimal(TOKEN_ID).toBigInteger();
        BigInteger userId = rs.getBigDecimal(USER_ID).toBigInteger();
        String token = rs.getString(RESET_PASSWORD_TOKEN);

        return new ResetPasswordToken(id, token, userId);
    };

    public ResetPasswordTokenDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.saveJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName(SAVE_RESET_PASSWORD_FUNCTION);
        this.updateJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(UPDATE_RESET_PASSWORD_PROCEDURE);
        this.deleteJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName(DELETE_TOKEN);
    }


    @Override
    public BigInteger deleteToken(BigInteger tokenId) throws DAOException {
        try{
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(TOKEN_ID, tokenId);

            return deleteJdbcCall.executeFunction(BigDecimal.class, mapParameters).toBigInteger();
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("ResetPasswordTokenDAOImpl error. Can not deleted");
        }
    }

    @Override
    public ResetPasswordToken getTokenByValue(String token) throws DAOException {
        try {
            return jdbcTemplate.queryForObject(GET_TOKEN_BY_VALUE, rowMapper, token);
        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getMessage());
            throw new DAOException("ResetPasswordTokenDAOImpl error. Can not found token");
        }
    }

    @Override
    public BigInteger saveToken(String resetPasswordToken, BigInteger userId) throws DAOException {
        try{
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(USER_ID, userId)
                    .addValue(TOKEN_VALUE, resetPasswordToken);

            return saveJdbcCall.executeFunction(BigDecimal.class, mapParameters).toBigInteger();
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("ResetPasswordTokenDAOImpl error. Can not created");
        }
    }

    @Override
    public void updateToken(ResetPasswordToken resetPasswordToken) throws DAOException {
        try {
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(TOKEN_ID, resetPasswordToken.getId())
                    .addValue(TOKEN_VALUE, resetPasswordToken.getToken())
                    .addValue(USER_ID, resetPasswordToken.getUserId());

            this.updateJdbcCall.execute(mapParameters);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("ResetPasswordTokenDAOImpl error. Can not updated");
        }
    }

    @Override
    public ResetPasswordToken getTokenById(BigInteger tokenId) throws DAOException {
        try {
            return jdbcTemplate.queryForObject(GET_TOKEN_BY_ID, rowMapper, tokenId);
        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getMessage());
            throw new DAOException("ResetPasswordTokenDAOImpl error. Can not found token by id");
        }
    }

    @Override
    public ResetPasswordToken getTokenByUserId(BigInteger userId) throws DAOException {
        try {
            return jdbcTemplate.queryForObject(GET_TOKEN_BY_USER_ID, rowMapper, userId);
        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getMessage());
            throw new DAOException("ResetPasswordTokenDAOImpl error. Can not found token by id");
        }
    }
}
