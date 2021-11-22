package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.GeneralInformation;
import com.teama.hospitalsystem.util.EAVAttrTypesID;
import com.teama.hospitalsystem.util.EAVObjTypesID;

import java.math.BigInteger;
import java.sql.Time;

public interface GeneralInformationDAO {
    String UPDATE_GEN_INFO_PROCEDURE = "UPDATE_GENINFO";
    String CREATE_GEN_INFO_PROCEDURE = "CREATE_GENINFO";

    String SQL_GET_GENERALINFO = "SELECT GENERALINFORMATION.OBJECT_ID GENERALINFORMATION_ID, ADDRESS.VALUE ADDRESS, " +
            "    PHONE.VALUE PHONE, WORKING_START.VALUE WORKING_START, WORKING_END.VALUE WORKING_END " +
            "FROM OBJECTS GENERALINFORMATION, ATTRIBUTES ADDRESS, " +
            "    ATTRIBUTES PHONE, ATTRIBUTES WORKING_START, ATTRIBUTES WORKING_END " +
            "WHERE GENERALINFORMATION.OBJECT_ID = ?" +
            "    AND ADDRESS.ATTR_ID = " + EAVAttrTypesID.ADDRESS +
            "    AND ADDRESS.OBJECT_ID = GENERALINFORMATION.OBJECT_ID " +
            "    AND PHONE.ATTR_ID = " + EAVAttrTypesID.PHONE +
            "    AND PHONE.OBJECT_ID = GENERALINFORMATION.OBJECT_ID " +
            "    AND WORKING_START.ATTR_ID = " + EAVAttrTypesID.WORKING_START +
            "    AND WORKING_START.OBJECT_ID = GENERALINFORMATION.OBJECT_ID " +
            "    AND WORKING_END.ATTR_ID = " + EAVAttrTypesID.WORKING_END +
            "    AND WORKING_END.OBJECT_ID = GENERALINFORMATION.OBJECT_ID";

    public void editGeneralInformation(GeneralInformation generalInformation);
    public GeneralInformation getGeneralInformation(BigInteger id);
    public void createGeneralInformation(GeneralInformation generalInformation);
}
