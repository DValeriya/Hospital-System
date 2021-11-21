package com.teama.hospitalsystem.util;

import com.teama.hospitalsystem.models.Appointment;

import java.math.BigInteger;

public enum AppointmentStatus {
    FREE(BigInteger.valueOf(11)),
    RESERVED(BigInteger.valueOf(12)),
    NOTAVAILABLE(BigInteger.valueOf(13));

    private final BigInteger _id;
    AppointmentStatus(BigInteger id){
        _id = id;
    }

    public BigInteger getId() {
        return _id;
    }

    public static AppointmentStatus fromId(BigInteger id) throws IllegalArgumentException {
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid id");
    }
}
