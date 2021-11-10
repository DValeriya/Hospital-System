package com.teama.hospitalsystem.models;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

public class Appointment {
    private BigInteger id;
    private Date expectedStart;
    private Date expectedEnd;
    private Date actualStart;
    private Date actualEnd;
    private BigInteger doctorId;
    private BigInteger patientId;
    private String diagnosis;
    private String referral;
    private BigInteger nextAppointment;
    private AppointmentStatus status;
    /* Ctor */

    public Appointment(){}

    /* Setters */

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setExpectedStart(Date expectedStart) {
        this.expectedStart = expectedStart;
    }

    public void setExpectedEnd(Date expectedEnd) {
        this.expectedEnd = expectedEnd;
    }

    public void setActualStart(Date actualStart) {
        this.actualStart = actualStart;
    }

    public void setActualEnd(Date actualEnd) {
        this.actualEnd = actualEnd;
    }

    public void setDoctorId(BigInteger doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(BigInteger patientId) {
        this.patientId = patientId;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public void setNextAppointment(BigInteger nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
    /* Getters */

    public BigInteger getId() {
        return id;
    }

    public Date getExpectedStart() {
        return expectedStart;
    }

    public Date getExpectedEnd() {
        return expectedEnd;
    }

    public Date getActualStart() {
        return actualStart;
    }

    public Date getActualEnd() {
        return actualEnd;
    }

    public BigInteger getDoctorId() {
        return doctorId;
    }

    public BigInteger getPatientId() {
        return patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getReferral() {
        return referral;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public BigInteger getNextAppointment() {
        return nextAppointment;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", expectedStart=" + expectedStart +
                ", expectedEnd=" + expectedEnd +
                ", actualStart=" + actualStart +
                ", actualEnd=" + actualEnd +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", diagnosis='" + diagnosis + '\'' +
                ", referral='" + referral + '\'' +
                ", nextAppointment=" + nextAppointment +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return id.equals(that.id) &&
                expectedStart.equals(that.expectedStart) &&
                expectedEnd.equals(that.expectedEnd) &&
                Objects.equals(actualStart, that.actualStart) &&
                Objects.equals(actualEnd, that.actualEnd) &&
                doctorId.equals(that.doctorId) &&
                patientId.equals(that.patientId) &&
                Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(referral, that.referral) &&
                Objects.equals(nextAppointment, that.nextAppointment) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expectedStart, expectedEnd, actualStart, actualEnd, doctorId, patientId, diagnosis, referral, nextAppointment, status);
    }
}
