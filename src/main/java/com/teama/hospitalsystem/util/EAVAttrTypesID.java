package com.teama.hospitalsystem.util;

public class EAVAttrTypesID {

    private EAVAttrTypesID() { } // Не допускает инстанцирование

    // Атрибуты для User
    public static final int LOGIN = 1;
    public static final int PASSWORD = 2;
    public static final int NAME = 3;
    public static final int PHONE_NUMBER = 4;
    public static final int BIRTH_DATE = 5;
    public static final int EMAIL = 6;
    public static final int ROLE = 7;
    public static final int EMPLOYER_DATA = 8;

    // Атрибуты для EmployerData
    public static final int HIRING_DATE = 9;
    public static final int EMP_STATUS = 10;
    public static final int START_WORKING_TIME = 11;
    public static final int END_WORKING_TIME = 12;
    public static final int DOCTOR_DATA = 13;

    // Атрибуты для WorkDay
    public static final int EMPLOYER_ID = 14;
    public static final int DATE = 15;
    public static final int APPOINTMENTS = 16;

    // Атрибуты для DoctorData
    public static final int SPEC = 17;
    public static final int APPOINTMENT_DURATION = 18;

    // Атрибуты для Appointment
    public static final int EXPECTED_START = 19;
    public static final int EXPECTED_END = 20;
    public static final int ACTUAL_START = 21;
    public static final int ACTUAL_END = 22;
    public static final int DOCTOR_ID = 23;
    public static final int PATIENT_ID = 24;
    public static final int DIAGNOSIS = 25;
    public static final int REFERRAL = 26;
    public static final int NEXT_APPOINTMENT_ID = 27;
    public static final int AP_STATUS = 28;

    // Атрибуты для DoctorSpecialization
    public static final int TITLE = 29;

    // Атрибуты для GeneralInformation
    public static final int ADDRESS = 30;
    public static final int PHONE = 31;
    public static final int WORKING_START = 32;
    public static final int WORKING_END = 33;
}
