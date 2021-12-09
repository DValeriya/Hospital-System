package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.DoctorSpecialization;
import com.teama.hospitalsystem.util.EAVAttrTypesID;
import com.teama.hospitalsystem.util.EAVObjTypesID;

import java.math.BigInteger;
import java.util.Collection;

public interface DoctorSpecializationDAO {
    String CREATE_DOCTOR_SPEC_PROCEDURE_NAME = "CREATE_DOCTORSPEC";
    String DELETE_DOCTOR_SPEC_BY_ID = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";

    String ID_DOCTOR_SPECIALIZATION = "id";
    String TITLE = "title";

    String TITLE_PARAM = "TITLE";

    String SELECT_DOCTOR_SPECS = "SELECT SPECS.OBJECT_ID AS id, TITLE.VALUE AS title " +
            "FROM OBJECTS SPECS, ATTRIBUTES TITLE " +
            "WHERE SPECS.OBJECT_TYPE_ID = " + EAVObjTypesID.DOCTOR_SPECIALIZATION +
            "   AND TITLE.ATTR_ID = " + EAVAttrTypesID.TITLE +
            "   AND TITLE.OBJECT_ID = SPECS.OBJECT_ID";

    String SELECT_DOCTOR_SPEC_BY_ID = SELECT_DOCTOR_SPECS +
            "   AND SPECS.OBJECT_ID = ?";

    String SELECT_DOCTOR_SPEC_BY_DOC_DATA_ID = "SELECT SPECIALIZATION.OBJECT_ID id, TITLE.VALUE title " +
            "FROM OBJECTS SPECIALIZATION, ATTRIBUTES TITLE, OBJREFERENCE REF " +
            "WHERE SPECIALIZATION.OBJECT_TYPE_ID = " + EAVObjTypesID.DOCTOR_SPECIALIZATION +
            "    AND TITLE.ATTR_ID = " + EAVAttrTypesID.TITLE +
            "    AND TITLE.OBJECT_ID = SPECIALIZATION.OBJECT_ID " +
            "    AND REF.ATTR_ID = " + EAVAttrTypesID.SPEC +
            "    AND REF.OBJECT_ID = ?" +
            "    AND REF.REFERENCE = SPECIALIZATION.OBJECT_ID";

    BigInteger createDoctorSpecialization(DoctorSpecialization specialization);
    void deleteDoctorSpecialization(DoctorSpecialization specialization);
    DoctorSpecialization getDoctorSpecializationById(BigInteger id);
    DoctorSpecialization getDoctorSpecializationByDoctorDataId(BigInteger id);
    Collection<DoctorSpecialization> getDoctorSpecializationList();
}
