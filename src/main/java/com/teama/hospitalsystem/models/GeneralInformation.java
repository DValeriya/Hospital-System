package com.teama.hospitalsystem.models;

import java.sql.Time;

public class GeneralInformation {
    private String address;
    private String phone;
    private Time workingStart;
    private Time workingEnd;

    public GeneralInformation() {}

    public GeneralInformation (String address, String phone, Time workingStart, Time workingEnd) {
        this.address = address;
        this.phone = phone;
        this.workingStart = workingStart;
        this.workingEnd = workingEnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Time getWorkingStart() {
        return workingStart;
    }

    public void setWorkingStart(Time workingStart) {
        this.workingStart = workingStart;
    }

    public Time getWorkingEnd() {
        return workingEnd;
    }

    public void setWorkingEnd(Time workingEnd) {
        this.workingEnd = workingEnd;
    }
}
