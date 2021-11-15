package com.teama.hospitalsystem.util;

import java.math.BigInteger;

public enum UserRole {
    ADMIN(BigInteger.valueOf(1)),
    DOCTOR(BigInteger.valueOf(2)),
    REGISTRY(BigInteger.valueOf(3)),
    PATIENT(BigInteger.valueOf(4));

    private final BigInteger id;

    UserRole(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public static UserRole fromId(BigInteger id) throws IllegalArgumentException {
        for (UserRole role : UserRole.values()) {
            if (role.getId().compareTo(id) == 0) {
                return role;
            }
        }
        throw new IllegalArgumentException("invalid id");
    }
}
