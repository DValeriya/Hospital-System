package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.DoctorData;
import com.teama.hospitalsystem.util.EAVAttrTypesID;

import java.math.BigInteger;

public interface DoctorDataDAO {
    String UPDATE_DOCTORDATA_PROCEDURE = "UPDATE_DOCTORDATA";
    String CREATE_DOCTORDATA_PROCEDURE = "CREATE_DOCTORDATA";

    String SQL_GET_DOCTORDATA_BY_DOCTOR_ID = "SELECT DOCTORDATA.OBJECT_ID DOCTORDATA_ID, " +
            "APPOINTMENTDURATION.VALUE APPOINTMENT_DURATION, " +
            "SPECIALIZATION.OBJECT_ID SECIALIZATION_ID " +
            "FROM OBJECTS DOCTORDATA, ATTRIBUTES APPOINTMENTDURATION, " +
            "OBJREFERENCE SPECIALIZATION " +
            "WHERE DOCTORDATA.PARENT_ID = ? " +
            " AND APPOINTMENTDURATION.ATTR_ID = " + EAVAttrTypesID.APPOINTMENT_DURATION +
            " AND APPOINTMENTDURATION.OBJECT_ID = DOCTORDATA.OBJECT_ID" +
            " AND SPECIALIZATION.ATTR_ID = " + EAVAttrTypesID.SPEC +
            " AND SPECIALIZATION.OBJECT_ID = DOCTORDATA.OBJECT_ID";

    String SQL_GET_DOCTORDATA_ID = "SELECT DOCTORDATA.OBJECT_ID DOCTORDATA_ID, " +
            "APPOINTMENTDURATION.VALUE APPOINTMENT_DURATION, " +
            "SPECIALIZATION.OBJECT_ID SECIALIZATION_ID " +
            "FROM OBJECTS DOCTORDATA, ATTRIBUTES APPOINTMENTDURATION, OBJREFERENCE SPECIALIZATION " +
            "WHERE DOCTORDATA.OBJECT_ID = ? " +
            " AND APPOINTMENTDURATION.ATTR_ID = " + EAVAttrTypesID.APPOINTMENT_DURATION +
            " AND APPOINTMENTDURATION.OBJECT_ID = DOCTORDATA.OBJECT_ID " +
            " AND SPECIALIZATION.ATTR_ID = " + EAVAttrTypesID.SPEC +
            " AND SPECIALIZATION.OBJECT_ID = DOCTORDATA.OBJECT_ID";

    public void createDoctorData(DoctorData doctorData, BigInteger employerId);
    public void editDoctorData(DoctorData doctorData);
    public DoctorData getDoctorDataByDoctorId(BigInteger id);
    public DoctorData getDoctorDataId(BigInteger id);
}
