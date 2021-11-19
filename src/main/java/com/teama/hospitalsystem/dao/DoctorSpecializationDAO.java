package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.DoctorSpecialization;
import com.teama.hospitalsystem.util.EAVAttrTypesID;
import com.teama.hospitalsystem.util.EAVObjTypesID;

import java.math.BigInteger;
import java.util.List;

public interface DoctorSpecializationDAO {
    String CREATE_DOCTOR_SPEC_PROCEDURE_NAME = "CREATE_DOCTORSPEC";
    String DELETE_DOCTOR_SPEC_PROCEDURE_NAME = "DELETE_DOCTORSPEC";

    String SELECT_DOCTOR_SPECS = "SELECT SPECS.OBJECT_ID AS id, TITLE.VALUE AS title " +
            "FROM OBJECTS SPECS " +
            "LEFT JOIN ATTRIBUTES TITLE ON TITLE.OBJECT_ID = SPECS.OBJECT_ID " +
            "AND TITLE.ATTR_ID = " + EAVAttrTypesID.TITLE +
            "WHERE SPECS.OBJECT_TYPE_ID = " + EAVObjTypesID.DOCTOR_SPECIALIZATION;

    String SELECT_DOCTOR_SPEC_BY_ID = SELECT_DOCTOR_SPECS +
            " AND SPECS.OBJECT_ID = ?";

    void createDoctorSpecialization(DoctorSpecialization specialization);
    void deleteDoctorSpecialization(DoctorSpecialization specialization);
    DoctorSpecialization getDoctorSpecializationById(BigInteger id);
    List<DoctorSpecialization> getDoctorSpecializationList();
}
