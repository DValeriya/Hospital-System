package com.teama.hospitalsystem.util;

import com.teama.hospitalsystem.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User.Builder(
                BigInteger.valueOf(rs.getInt("id")),
                BigInteger.valueOf(rs.getInt("login")),
                rs.getString("name"),
                rs.getString("phone"),
                UserRole.fromId(BigInteger.valueOf(rs.getInt("role"))))
                .withBirthDate(rs.getDate("birth"))
                .withEmail(rs.getString("email"))
                .build();
    }
}