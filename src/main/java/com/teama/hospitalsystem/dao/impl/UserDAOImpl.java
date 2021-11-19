package com.teama.hospitalsystem.dao.impl;

import com.teama.hospitalsystem.dao.UserDAO;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.UserRole;
import com.teama.hospitalsystem.util.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUser(User user) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(CREATE_USER_PROCEDURE_NAME);

            Map<String, Object> inParamMap = new HashMap<>();
            inParamMap.put("NAME", user.getName());
            inParamMap.put("PASSWORD", user.getPassword());
            inParamMap.put("PHONENUMBER", user.getPhoneNumber());
            inParamMap.put("BIRTHDATE", user.getBirthDate());
            inParamMap.put("EMAIL", user.getEmail());
            inParamMap.put("ROLE", user.getRole().getId());
            SqlParameterSource in = new MapSqlParameterSource(inParamMap);

            jdbcCall.execute(in);
        } catch (DataAccessException ex) {
            String str = ex.getMessage();
        }
    }

    @Override
    public User getUserByLoginAndPassword(BigInteger login, String password) {
        try {
            return jdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN_AND_PASSWORD,
                    new UserRowMapper(), login, password);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserRowMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getUsersList() {
        try {
            return jdbcTemplate.query(SELECT_USERS, new UserRowMapper());
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getUsersListByRole(UserRole role) {
        try {
            return jdbcTemplate.query(SELECT_USERS_BY_ROLE, new UserRowMapper(), role.getId());
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public BigInteger getEmployerDataId(BigInteger userId) {
        try {
            Integer id = jdbcTemplate.queryForObject(SELECT_EMPLOYER_DATA_BY_USER_ID,
                    Integer.class, userId);
            return id != null ? BigInteger.valueOf(id) : null;
        } catch (DataAccessException ex) {
            return null;
        }
    }
}
