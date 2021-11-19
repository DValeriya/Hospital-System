package com.teama.hospitalsystem.models;

import java.math.BigInteger;
import java.util.Objects;

public class DoctorSpecialization {
    private BigInteger specializationId;
    private final String title;

    public DoctorSpecialization(BigInteger specializationId, String title){
        this.specializationId = specializationId;
        this.title = title;
    }

    public DoctorSpecialization(String title){
        this.title = title;
    }

    public BigInteger getSpecializationId() {
        return specializationId;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "DoctorSpecialization{" +
                "specializationId=" + specializationId +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoctorSpecialization)) return false;
        DoctorSpecialization that = (DoctorSpecialization) o;
        return Objects.equals(specializationId, that.specializationId) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specializationId, title);
    }
}
