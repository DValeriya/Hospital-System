package com.teama.hospitalsystem.controllers.request;

import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserRequest {
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

    public User asUser() {
        return new User.Builder(this.name, this.password, this.phoneNumber, this.role)
                .withEmail(this.email)
                .withBirthDate(this.birthDate)
                .withEmployerData(this.employerData)
                .build();
    }
}
