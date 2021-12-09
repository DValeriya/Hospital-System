package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.DoctorData;
import com.teama.hospitalsystem.models.User;

import java.math.BigInteger;
import java.util.List;

public interface DoctorsService {
    DoctorData createDoctor(User user);
    void editDoctor(DoctorData doctorData);
    DoctorData getDoctorDataById(BigInteger id);
    DoctorData getDoctorDataId(BigInteger id);
    List<DoctorData> getDoctorListBySpecialization(BigInteger specializationId);
}
