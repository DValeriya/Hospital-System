package com.teama.hospitalsystem.models;

import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class WorkDay {
    private BigInteger workDayId;
    private BigInteger employerId;
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

}
