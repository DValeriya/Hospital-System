package com.teama.hospitalsystem.util.mappers;

import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        BigInteger id = rs.getBigDecimal("id").toBigInteger();
        BigInteger login = rs.getBigDecimal("login").toBigInteger();
        BigInteger role = rs.getBigDecimal("role").toBigInteger();
        return new User.Builder(id, login, rs.getString("name"),
                rs.getString("phone"), UserRole.fromId(role))
                .withBirthDate(rs.getDate("birth"))
                .withEmail(rs.getString("email"))
                .build();
    }
}