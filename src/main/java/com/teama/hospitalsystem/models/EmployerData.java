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

    public BigInteger getEmployerDataId() {
        return employerDataId;
    }

    public void setEmployerDataId(BigInteger employerDataId) {
        this.employerDataId = employerDataId;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
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

    public static class Builder{
        private EmployerData employer;

        public Builder(){
            employer = new EmployerData();
        }
        public Builder WithEmployerDataId(BigInteger employerDataId){
            employer.employerDataId = employerDataId;
            return this;
        }
        public Builder WithHiringDate(Date hiringDate){
            employer.hiringDate = hiringDate;
            return this;
        }
        public Builder WithStatus(EmployerStatus status){
            employer.status = status;
            return this;
        }
        public Builder WithStartWorkingTime(Time startWorkingTime){
            employer.startWorkingTime = startWorkingTime;
            return this;
        }
        public Builder WithEndWorkingTime(Time endWorkingTime){
            employer.endWorkingTime = endWorkingTime;
            return this;
        }
        public Builder WithDoctorData(DoctorData doctorData){
            employer.doctorData = doctorData;
            return this;
        }
        public EmployerData build(){
            return employer;
        }

    }
}
