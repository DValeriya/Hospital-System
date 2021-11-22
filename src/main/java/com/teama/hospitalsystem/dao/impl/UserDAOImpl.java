package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.UserDAO;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.UserRole;
import com.teama.hospitalsystem.util.mappers.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUser(User user) throws DataAccessException {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(CREATE_USER_PROCEDURE_NAME);

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("USER_NAME", user.getName());
        inParamMap.put("PASSWORD", user.getPassword());
        inParamMap.put("PHONENUMBER", user.getPhoneNumber());
        inParamMap.put("BIRTHDATE", user.getBirthDate());
        inParamMap.put("EMAIL", user.getEmail());
        inParamMap.put("ROLE", user.getRole().getId());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        jdbcCall.execute(in);
    }

    @Override
    public void editUser(User user) throws DataAccessException {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(EDIT_USER_PROCEDURE_NAME);

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("USER_ID", user.getId());
        inParamMap.put("USER_NAME", user.getName());
        inParamMap.put("PASSWORD", user.getPassword());
        inParamMap.put("PHONENUMBER", user.getPhoneNumber());
        inParamMap.put("BIRTHDATE", user.getBirthDate());
        inParamMap.put("EMAIL", user.getEmail());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        jdbcCall.execute(in);
    }

    @Override
    public User getUserByLoginAndPassword(BigInteger login, String password) throws DataAccessException {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN_AND_PASSWORD,
                new UserRowMapper(), login, password);
    }

    @Override
    public User getUserById(BigInteger id) throws DataAccessException {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserRowMapper(), id);
    }

    @Override
    public Collection<User> getUsersList() throws DataAccessException {
        return jdbcTemplate.query(SELECT_USERS, new UserRowMapper());
    }

    @Override
    public Collection<User> getUsersListByRole(UserRole role) throws DataAccessException {
        return jdbcTemplate.query(SELECT_USERS_BY_ROLE, new UserRowMapper(), role.getId());
    }
}