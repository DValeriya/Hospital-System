package com.teama.hospitalsystem.util;

public enum EAVObjTypes {
    USER(1),
    EMPLOYER_DATA(2),
    WORKDAY(3),
    DOCTOR_DATA(4),
    APPOINTMENT(5),
    DOCTOR_SPECIALIZATION(6),
    GENERAL_INFORMATION(7);

    private final int id;

    EAVObjTypes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
