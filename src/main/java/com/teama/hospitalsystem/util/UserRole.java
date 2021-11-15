package com.teama.hospitalsystem.util;

import java.math.BigInteger;

public enum UserRole {
    Admin(1),
    Doctor(2),
    Registry(3),
    Patient(4);

    private final int id;

    UserRole(int i) {
        id = i;
    }

    public int GetId() {
        return id;
    }

    public static UserRole fromId(BigInteger id) throws IllegalArgumentException {
        switch (id.intValue()) {
            case 1:
                return Admin;
            case 2:
                return Doctor;
            case 3:
                return Registry;
            case 4:
                return Patient;
            default:
                throw new IllegalArgumentException("invalid id");
        }
    }
}
