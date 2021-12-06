package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.UserDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.EmployerStatus;
import com.teama.hospitalsystem.util.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall updateJdbcCall;
    private final SimpleJdbcCall insertJdbcCall;

    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        BigInteger id = rs.getBigDecimal(USER_ID).toBigInteger();
        BigInteger login = rs.getBigDecimal(LOGIN).toBigInteger();
        BigInteger role = rs.getBigDecimal(ROLE).toBigInteger();
        return new User.Builder(id, login, rs.getString(USER_NAME),
                rs.getString(PHONENUMBER), UserRole.fromId(role))
                .withBirthDate(rs.getDate(BIRTHDATE))
                .withEmail(rs.getString(EMAIL))
                .build();
    };

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName(CREATE_USER_FUNCTION);
        this.updateJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(EDIT_USER_PROCEDURE);
    }


    @Override
    public BigInteger createUser(User user) throws DataAccessException {
        try{
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(USER_NAME, user.getName())
                    .addValue(PASSWORD, user.getPassword())
                    .addValue(PHONENUMBER, user.getPhoneNumber())
                    .addValue(BIRTHDATE, user.getBirthDate())
                    .addValue(EMAIL, user.getEmail())
                    .addValue(ROLE, user.getRole().getId());

            return insertJdbcCall.executeFunction(BigInteger.class, mapParameters);

        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("UserDAOImpl error. User was not created.");
        }
    }

    @Override
    public void editUser(User user) throws DataAccessException {
        try{
            SqlParameterSource mapParameters = new MapSqlParameterSource()
                    .addValue(USER_ID, user.getId())
                    .addValue(USER_NAME, user.getName())
                    .addValue(PASSWORD, user.getPassword())
                    .addValue(PHONENUMBER, user.getPhoneNumber())
                    .addValue(BIRTHDATE, user.getBirthDate())
                    .addValue(EMAIL, user.getEmail());

            this.updateJdbcCall.execute(mapParameters);

        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("UserDAOImpl error. User was not edited.");
        }
    }

    @Override
    public User getUserByLoginAndPassword(BigInteger login, String password) throws DataAccessException {
        try{
            return jdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN_AND_PASSWORD, rowMapper, login, password);
        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("UserDAOImpl error. Failed to get User by login and password.");
        }
    }

    @Override
    public User getUserById(BigInteger id) throws DataAccessException {
        try{
            return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, rowMapper, id);
        } catch (DataAccessException dataAccessException){
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new DAOException("UserDAOImpl error. Failed to get User by ID.");
        }
    }

    @Override
    public Collection<User> getUsersList() throws DataAccessException {
        return jdbcTemplate.query(SELECT_USERS, rowMapper);
    }

    @Override
    public Collection<User> getUsersListByRole(UserRole role) throws DataAccessException {
        return jdbcTemplate.query(SELECT_USERS_BY_ROLE, rowMapper, role.getId());
    }

    @Override
    public Collection<User> getUsersListByRoleAndStatus(UserRole role, EmployerStatus status) throws DataAccessException {
        return jdbcTemplate.query(SELECT_USERS_BY_ROLE_AND_STATUS, rowMapper, role.getId(), status.getId());
    }

}