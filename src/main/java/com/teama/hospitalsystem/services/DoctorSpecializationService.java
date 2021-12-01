package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.DoctorSpecialization;

import java.math.BigInteger;
import java.util.Collection;

public interface DoctorSpecializationService {

    DoctorSpecialization createDoctorSpecialization(DoctorSpecialization spec);
    void deleteDoctorSpecialization(DoctorSpecialization spec);
    DoctorSpecialization getDoctorSpecializationById(BigInteger id);
    Collection<DoctorSpecialization> getDoctorSpecializationList();
}
