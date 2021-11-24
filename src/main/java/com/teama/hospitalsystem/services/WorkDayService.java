package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.WorkDay;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public interface WorkDayService {

    void createWorkDay(WorkDay workDay);

    void deleteWorkDay(WorkDay workDay);

    Collection<WorkDay> getWorkDaysByEmployerId(BigInteger employerId);

    Collection<WorkDay> getWorkDaysByDate(Date date);
}
