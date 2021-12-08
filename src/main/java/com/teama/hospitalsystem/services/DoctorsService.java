package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.DoctorData;
import com.teama.hospitalsystem.models.User;

import java.math.BigInteger;
import java.util.List;

public interface DoctorsService {
    public DoctorData createDoctor(User user);
    public void editDoctor(DoctorData doctorData);
    public DoctorData getDoctorDataById(BigInteger id);
    public DoctorData getDoctorDataId(BigInteger id);
    public List<DoctorData> getDoctorListBySpecialization(BigInteger specializationId);
}
