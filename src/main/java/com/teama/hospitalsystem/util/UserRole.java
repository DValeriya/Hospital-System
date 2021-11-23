package com.teama.hospitalsystem.util;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum UserRole {
    ADMIN(BigInteger.valueOf(1)),
    DOCTOR(BigInteger.valueOf(2)),
    REGISTRY(BigInteger.valueOf(3)),
    PATIENT(BigInteger.valueOf(4));

    private final BigInteger id;
    private static final String ROLE_ID_NOT_FOUND = "User role with ID[%s] not found";

    private static final Map<BigInteger, UserRole> ID_TO_ROLE = Stream.of(values())
            .collect(Collectors.toMap(UserRole::getId, role -> role));

    UserRole(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public static UserRole fromId(BigInteger id) throws IllegalArgumentException {
        UserRole role = ID_TO_ROLE.get(id);
        if (role == null) {
            throw new IllegalArgumentException(String.format(ROLE_ID_NOT_FOUND, id));
        }
        return role;
    }
}
