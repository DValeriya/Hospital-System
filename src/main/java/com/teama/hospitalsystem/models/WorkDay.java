package com.teama.hospitalsystem.models;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class WorkDay {
    private BigInteger workDayId;
    @NotNull
    private BigInteger employerId;
    @NotNull
    private Date date;
    private List<Appointment> appointments;

    public WorkDay(BigInteger workDayId, BigInteger employerId, Date date) {
        this.workDayId = workDayId;
        this.employerId = employerId;
        this.date = date;
        appointments = new LinkedList<>();
    }

    public BigInteger getWorkDayId() {
        return workDayId;
    }

    public void setWorkDayId(BigInteger workDayId) {
        this.workDayId = workDayId;
    }

    public BigInteger getEmployerId() {
        return employerId;
    }

    public void setEmployerId(BigInteger employerId) {
        this.employerId = employerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointments(Appointment appointment) {
        appointments.add(appointment);
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "workDayId=" + workDayId +
                ", employerId=" + employerId +
                ", date=" + date +
                ", appointments=" + appointments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkDay)) return false;
        WorkDay day = (WorkDay) o;
        return Objects.equals(workDayId, day.workDayId) &&
                Objects.equals(employerId, day.employerId) &&
                Objects.equals(date, day.date) &&
                Objects.equals(appointments, day.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workDayId, employerId, date, appointments);
    }
}
