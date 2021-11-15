package com.teama.hospitalsystem.models;

import com.teama.hospitalsystem.util.UserRole;

import java.math.BigInteger;
import java.util.Date;

public class User {
    private final BigInteger id;
    private final BigInteger login;
    private String password;
    private String name;
    private String phoneNumber;
    private Date birthDate;
    private String email;
    private UserRole role;
    private EmployerData employerData;

    public BigInteger getId() {
        return id;
    }

    public BigInteger getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public EmployerData getEmployerData() {
        return employerData;
    }

    public void setEmployerData(EmployerData employerData) {
        this.employerData = employerData;
    }

    public static class Builder {
        private BigInteger id = BigInteger.ZERO;
        private BigInteger login = BigInteger.ZERO;
        private String password = "";
        private String name = "";
        private String phoneNumber = "";
        private Date birthDate;
        private String email = "";
        private final UserRole role;
        private EmployerData employerData;

        public Builder(BigInteger id, BigInteger login, UserRole role) {
            this.id = id;
            this.login = login;
            this.role = role;
        }

        public Builder(String name, String phoneNumber, UserRole role) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.role = role;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withEmployerData(EmployerData employerData) {
            this.employerData = employerData;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.name = builder.name;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.birthDate = builder.birthDate;
        this.email = builder.email;
        this.role = builder.role;
        this.employerData = builder.employerData;
    }

}