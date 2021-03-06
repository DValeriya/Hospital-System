package com.teama.hospitalsystem.models;


import com.teama.hospitalsystem.util.EmployerStatus;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

public class EmployerData {
    private BigInteger employerDataId;
    private Date hiringDate;
    private EmployerStatus status;
    private Time startWorkingTime;
    private Time endWorkingTime;
    private DoctorData doctorData;

    public EmployerData(BigInteger employerDataId, Date hiringDate, EmployerStatus status,
                        Time startWorkingTime, Time endWorkingTime) {
        this.employerDataId = employerDataId;
        this.hiringDate = hiringDate;
        this.status = status;
        this.startWorkingTime = startWorkingTime;
        this.endWorkingTime = endWorkingTime;
    }

    public BigInteger getEmployerDataId() {
        return employerDataId;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public EmployerStatus getStatus() {
        return status;
    }

    public void setStatus(EmployerStatus status) {
        this.status = status;
    }

    public Time getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(Time startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    public Time getEndWorkingTime() {
        return endWorkingTime;
    }

    public void setEndWorkingTime(Time endWorkingTime) {
        this.endWorkingTime = endWorkingTime;
    }

    public DoctorData getDoctorData() {
        return doctorData;
    }

    public void setDoctorData(DoctorData doctorData) {
        this.doctorData = doctorData;
    }

        @Override
        public String toString() {
            return "EmployerData{" +
                    "employerDataId=" + employerDataId +
                    ", hiringDate=" + hiringDate +
                    ", status=" + status +
                    ", startWorkingTime=" + startWorkingTime +
                    ", endWorkingTime=" + endWorkingTime +
                    ", doctorData=" + doctorData +
                    "}";
        }
}
