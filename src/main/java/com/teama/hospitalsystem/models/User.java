package com.teama.hospitalsystem.models;

import com.teama.hospitalsystem.util.UserRole;

import java.math.BigInteger;
import java.util.Date;

public class User {
    private BigInteger id;
    private BigInteger login;
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

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getLogin() {
        return login;
    }

    public void setLogin(BigInteger login) {
        this.login = login;
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
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withId(BigInteger id) {
            user.id = id;
            return this;
        }

        public Builder withLogin(BigInteger login) {
            user.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder withName(String name) {
            user.name = name;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            user.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withBirthDate(Date birthDate) {
            user.birthDate = birthDate;
            return this;
        }

        public Builder withEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder withUserRole(UserRole role) {
            user.role = role;
            return this;
        }

        public Builder withEmployerData(EmployerData employerData) {
            user.employerData = employerData;
            return this;
        }

        public User build() {
            return user;
        }
    }

}