package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.DoctorSpecialization;
import com.teama.hospitalsystem.util.EAVObjTypesID;

import java.math.BigInteger;
import java.util.Collection;

public interface DoctorSpecializationDAO {
    String CREATE_DOCTOR_SPEC_PROCEDURE_NAME = "CREATE_DOCTORSPEC";
    String DELETE_DOCTOR_SPEC_BY_ID = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";

    String SELECT_DOCTOR_SPECS = "SELECT OBJECT_ID AS id, NAME AS title " +
            "FROM OBJECTS " +
            "WHERE OBJECT_TYPE_ID = " + EAVObjTypesID.DOCTOR_SPECIALIZATION;

    String SELECT_DOCTOR_SPEC_BY_ID = SELECT_DOCTOR_SPECS +
            " AND OBJECT_ID = ?";

    void createDoctorSpecialization(DoctorSpecialization specialization);
    void deleteDoctorSpecialization(DoctorSpecialization specialization);
    DoctorSpecialization getDoctorSpecializationById(BigInteger id);
    Collection<DoctorSpecialization> getDoctorSpecializationList();
}
