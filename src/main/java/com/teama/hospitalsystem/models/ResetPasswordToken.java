package com.teama.hospitalsystem.models;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.Objects;

public class ResetPasswordToken {
    private BigInteger id;
    @NotBlank
    private String token;
    private BigInteger userId;

    public ResetPasswordToken(BigInteger id, String token, BigInteger userId) {
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ResetPasswordToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResetPasswordToken that = (ResetPasswordToken) o;
        return Objects.equals(id, that.id) && Objects.equals(token, that.token) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, userId);
    }
}
