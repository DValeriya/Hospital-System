package com.teama.hospitalsystem.models;

import jdk.nashorn.internal.runtime.Specialization;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Objects;

public class DoctorData {
    private BigInteger doctorDataId;
    private DoctorSpecialization spec;
    private Time appointmentDuration;
    /* Ctor */

    public DoctorData(){}
    /* Setters */

    public void setDoctorDataId(BigInteger doctorDataId) {
        this.doctorDataId = doctorDataId;
    }

    public void setSpec(DoctorSpecialization spec) {
        this.spec = spec;
    }

    public void setAppointmentDuration(Time appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }
    /* Getters */

    public BigInteger getDoctorDataId() {
        return doctorDataId;
    }

    public DoctorSpecialization getSpec() {
        return spec;
    }

    public Time getAppointmentDuration() {
        return appointmentDuration;
    }

    @Override
    public String toString() {
        return "DoctorData{" +
                "doctorDataId=" + doctorDataId +
                ", spec=" + spec +
                ", appointmentDuration=" + appointmentDuration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoctorData)) return false;
        DoctorData that = (DoctorData) o;
        return doctorDataId.equals(that.doctorDataId) &&
                spec.equals(that.spec) &&
                Objects.equals(appointmentDuration, that.appointmentDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorDataId, spec, appointmentDuration);
    }
}
