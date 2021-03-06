package com.teama.hospitalsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.teama.hospitalsystem.util.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@JsonDeserialize(builder = User.Builder.class)
public class User {
    private final BigInteger id;
    private final BigInteger login;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @Pattern(regexp="\\(\\d{3}\\)\\d{2}-\\d{2}-\\d{3}")
    private String phoneNumber;
    @Past
    private Date birthDate;
    @Email
    private String email;
    @NotNull
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

    @JsonPOJOBuilder
    public static class Builder {
        private String email = null;
        private Date birthDate = null;
        private EmployerData employerData;
        private BigInteger id;
        private BigInteger login;
        private final String name;
        private final String password;
        private final UserRole role;
        private final String phoneNumber;

        public Builder(BigInteger id, BigInteger login, String name,
                       String phoneNumber, UserRole role) {
            this(name, null, phoneNumber, role);
            this.id = id;
            this.login = login;
        }

        public Builder(@JsonProperty("name") String name, @JsonProperty("password") String password,
                       @JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("role") UserRole role) {
            this.name = name;
            this.password = password;
            this.phoneNumber = phoneNumber;
            this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(email, user.email) &&
                role == user.role &&
                Objects.equals(employerData, user.employerData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, phoneNumber,
                birthDate, email, role, employerData);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login=" + login +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", employerData=" + employerData +
                '}';
    }
}