package com.teama.hospitalsystem.models;


import com.teama.hospitalsystem.util.EmployerStatus;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

public class EmployerData {
    private final BigInteger employerDataId;
    private final Date hiringDate;
    private EmployerStatus status;
    private Time startWorkingTime;
    private Time endWorkingTime;
    private DoctorData doctorData;

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

    private EmployerData(Builder builder){
        this.employerDataId = builder.employerDataId;
        this.hiringDate = builder.hiringDate;
        this.status = builder.status;
        this.startWorkingTime = builder.startWorkingTime;
        this.endWorkingTime = builder.endWorkingTime;
        this.doctorData = builder.doctorData;
    }

    public static class Builder{
        private final BigInteger employerDataId;
        private final Date hiringDate;
        private EmployerStatus status;
        private Time startWorkingTime;
        private Time endWorkingTime;
        private DoctorData doctorData;

        public Builder(BigInteger employerDataId, Date hiringDate) {
            this.employerDataId = employerDataId;
            this.hiringDate = hiringDate;
        }

        public Builder withStatus(EmployerStatus status){
            this.status = status;
            return this;
        }
        public Builder withStartWorkingTime(Time startWorkingTime){
            this.startWorkingTime = startWorkingTime;
            return this;
        }
        public Builder withEndWorkingTime(Time endWorkingTime){
            this.endWorkingTime = endWorkingTime;
            return this;
        }
        public Builder withDoctorData(DoctorData doctorData){
            this.doctorData = doctorData;
            return this;
        }

        public EmployerData build(){
            return new EmployerData(this);
        }

        @Override
        public String toString() {
            return "EmployerData{" +
                    "employerDataId=" + employerDataId +
                    ", hiringDate" + hiringDate +
                    ", status" + status +
                    ", startWorkingTime" + startWorkingTime +
                    ", endWorkingTime" + endWorkingTime +
                    ", doctorData" + doctorData +
                    "}";
        }
    }
}
