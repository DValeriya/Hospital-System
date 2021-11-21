package com.teama.hospitalsystem.util;

import java.math.BigInteger;

public enum EmployerStatus {
    WORK(BigInteger.valueOf(1)),
    ABSENT(BigInteger.valueOf(2)),
    DISMISSED(BigInteger.valueOf(3)),
    SICK(BigInteger.valueOf(4)),
    BUSINESS_TRIP(BigInteger.valueOf(5)),
    PERSONAL(BigInteger.valueOf(6));

    private final BigInteger id;

    EmployerStatus(BigInteger id){
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public static EmployerStatus fromId(BigInteger id) throws IllegalArgumentException{
        for(EmployerStatus employerStatus : values()){
            if(employerStatus.getId().compareTo(id) == 0){
                return employerStatus;
            }
        }
        throw new IllegalArgumentException("Invalid id");
    }
}
