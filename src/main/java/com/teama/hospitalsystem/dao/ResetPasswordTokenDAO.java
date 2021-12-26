package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.ResetPasswordToken;
import com.teama.hospitalsystem.util.EAVObjTypesID;

import java.math.BigInteger;

public interface ResetPasswordTokenDAO {

    String SAVE_RESET_PASSWORD_FUNCTION = "SAVE_RESET_PASSWORD";
    String UPDATE_RESET_PASSWORD_PROCEDURE = "UPDATE_RESET_PASSWORD";
    String DELETE_TOKEN = "DELETE_TOKEN";

    String RESET_PASSWORD_TOKEN = "RESETPASSWORDTOKEN";
    String USER_ID = "USER_ID";
    String TOKEN_ID = "TOKEN_ID";
    String TOKEN_VALUE = "TOKEN_VALUE";

    String GET_TOKEN_BY_VALUE = "SELECT RESETPASSWORDTOKEN.OBJECT_ID AS TOKEN_ID, RESETPASSWORDTOKEN.PARENT_ID USER_ID, " +
            "   RESETPASSWORDTOKEN.NAME AS RESETPASSWORDTOKEN " +
            "FROM OBJECTS RESETPASSWORDTOKEN " +
            "WHERE RESETPASSWORDTOKEN.OBJECT_TYPE_ID = " + EAVObjTypesID.RESET_PASSWORD_TOKEN +
            "    AND RESETPASSWORDTOKEN.NAME = ?";

    String GET_TOKEN_BY_ID = "SELECT RESETPASSWORDTOKEN.OBJECT_ID AS TOKEN_ID, RESETPASSWORDTOKEN.PARENT_ID USER_ID," +
            "            RESETPASSWORDTOKEN.NAME AS RESETPASSWORDTOKEN " +
            "            FROM OBJECTS RESETPASSWORDTOKEN" +
            "            WHERE RESETPASSWORDTOKEN.OBJECT_TYPE_ID = " + EAVObjTypesID.RESET_PASSWORD_TOKEN +
            "            AND RESETPASSWORDTOKEN.OBJECT_ID = ?";

    String GET_TOKEN_BY_USER_ID = "SELECT RESETPASSWORDTOKEN.OBJECT_ID AS TOKEN_ID, RESETPASSWORDTOKEN.PARENT_ID USER_ID," +
            "            RESETPASSWORDTOKEN.NAME AS RESETPASSWORDTOKEN " +
            "            FROM OBJECTS RESETPASSWORDTOKEN" +
            "            WHERE RESETPASSWORDTOKEN.OBJECT_TYPE_ID = " + EAVObjTypesID.RESET_PASSWORD_TOKEN +
            "            AND RESETPASSWORDTOKEN.PARENT_ID = ?";

    BigInteger deleteToken(BigInteger tokenId);
    ResetPasswordToken getTokenByValue(String token);
    BigInteger saveToken(String resetPasswordToken, BigInteger userId);
    void updateToken(ResetPasswordToken resetPasswordToken);
    ResetPasswordToken getTokenById(BigInteger tokenId);
    ResetPasswordToken getTokenByUserId(BigInteger userId);
}
