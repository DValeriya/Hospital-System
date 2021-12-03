package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.UserDAO;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.EmployerStatus;
import com.teama.hospitalsystem.util.UserRole;
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
    private final JdbcTemplate jdbcTemplate;

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
    }


    //TODO: createUser should return User (and EmployerData)
    @Override
    public BigInteger createUser(User user) throws DataAccessException {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName(CREATE_USER_PROCEDURE_NAME);

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put(USER_NAME, user.getName());
        inParamMap.put(PASSWORD, user.getPassword());
        inParamMap.put(PHONENUMBER, user.getPhoneNumber());
        inParamMap.put(BIRTHDATE, user.getBirthDate());
        inParamMap.put(EMAIL, user.getEmail());
        inParamMap.put(ROLE, user.getRole().getId());

        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return jdbcCall.executeFunction(BigInteger.class, in);

    }

    @Override
    public void editUser(User user) throws DataAccessException {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(EDIT_USER_PROCEDURE_NAME);

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put(USER_ID, user.getId());
        inParamMap.put(USER_NAME, user.getName());
        inParamMap.put(PASSWORD, user.getPassword());
        inParamMap.put(PHONENUMBER, user.getPhoneNumber());
        inParamMap.put(BIRTHDATE, user.getBirthDate());
        inParamMap.put(EMAIL, user.getEmail());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        jdbcCall.execute(in);
    }

    @Override
    public User getUserByLoginAndPassword(BigInteger login, String password) throws DataAccessException {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN_AND_PASSWORD, rowMapper, login, password);
    }

    @Override
    public User getUserById(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, rowMapper, id);
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