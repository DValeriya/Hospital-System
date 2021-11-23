package com.teama.hospitalsystem.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EmployerStatus {
    WORK(BigInteger.valueOf(5)),
    ABSENT(BigInteger.valueOf(6)),
    DISMISSED(BigInteger.valueOf(7)),
    SICK(BigInteger.valueOf(8)),
    BUSINESS_TRIP(BigInteger.valueOf(9)),
    PERSONAL(BigInteger.valueOf(10));

    private static final String STATUS_ID_NOT_FOUND = "Employer Status with ID[%s] not found";

    private final BigInteger id;

    private static final Map<BigInteger, EmployerStatus> ID_TO_STATUS =
            Stream.of(values()).collect(Collectors.toMap(EmployerStatus::getId, employerStatus -> employerStatus));

    EmployerStatus(BigInteger id){
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public static EmployerStatus fromId(BigInteger id) throws IllegalArgumentException{
        EmployerStatus employerStatus = ID_TO_STATUS.get(id);
        if(employerStatus == null){
            throw new IllegalArgumentException(String.format(STATUS_ID_NOT_FOUND, id));
        }
        return employerStatus;
    }
}
