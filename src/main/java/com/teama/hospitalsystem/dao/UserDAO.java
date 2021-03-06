package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.EAVAttrTypesID;
import com.teama.hospitalsystem.util.EAVObjTypesID;
import com.teama.hospitalsystem.util.EmployerStatus;
import com.teama.hospitalsystem.util.UserRole;

import java.math.BigInteger;
import java.util.Collection;

public interface UserDAO {
    String CREATE_USER_FUNCTION = "CREATE_USER";
    String EDIT_USER_PROCEDURE = "EDIT_USER";

    String SELECT_USERS = "SELECT USERS.OBJECT_ID AS USER_ID, LOGIN.VALUE AS USER_LOGIN, USERS.NAME AS USER_NAME, " +
            "PHONENUMBER.VALUE AS PHONENUMBER, EMAIL.VALUE AS EMAIL, BIRTHDATE.DATE_VALUE AS BIRTHDATE, " +
            "ROLE.LIST_VALUE_ID AS ROLE " +
            "FROM OBJECTS USERS " +
            "LEFT JOIN ATTRIBUTES LOGIN ON LOGIN.OBJECT_ID = USERS.OBJECT_ID" +
            " AND LOGIN.ATTR_ID = " + EAVAttrTypesID.LOGIN +
            " LEFT JOIN ATTRIBUTES PHONENUMBER ON PHONENUMBER.OBJECT_ID = USERS.OBJECT_ID" +
            " AND PHONENUMBER.ATTR_ID = " + EAVAttrTypesID.PHONE_NUMBER +
            " LEFT JOIN ATTRIBUTES BIRTHDATE ON BIRTHDATE.OBJECT_ID = USERS.OBJECT_ID" +
            " AND BIRTHDATE.ATTR_ID = " + EAVAttrTypesID.BIRTH_DATE +
            " LEFT JOIN ATTRIBUTES EMAIL ON EMAIL.OBJECT_ID = USERS.OBJECT_ID" +
            " AND EMAIL.ATTR_ID = " + EAVAttrTypesID.EMAIL +
            " LEFT JOIN ATTRIBUTES ROLE ON ROLE.OBJECT_ID = USERS.OBJECT_ID" +
            " AND ROLE.ATTR_ID = " + EAVAttrTypesID.ROLE +
            " LEFT JOIN ATTRIBUTES PASSWORD ON PASSWORD.OBJECT_ID = USERS.OBJECT_ID" +
            " AND PASSWORD.ATTR_ID = " + EAVAttrTypesID.PASSWORD +
            " WHERE USERS.OBJECT_TYPE_ID = " + EAVObjTypesID.USER;

    String SELECT_USER_BY_ID = SELECT_USERS + " AND USERS.OBJECT_ID = ?";

    String SELECT_USER_BY_EMAIL = SELECT_USERS +
            " AND EMAIL.VALUE = ?";

    String SELECT_USER_BY_LOGIN_WITH_PASSWORD = "SELECT USERS.OBJECT_ID AS USER_ID, LOGIN.VALUE AS USER_LOGIN, USERS.NAME AS USER_NAME, " +
            "PHONENUMBER.VALUE AS PHONENUMBER, EMAIL.VALUE AS EMAIL, BIRTHDATE.DATE_VALUE AS BIRTHDATE, " +
            "PASSWORD.VALUE As PASSWORD, ROLE.LIST_VALUE_ID AS ROLE " +
            "FROM OBJECTS USERS " +
            "LEFT JOIN ATTRIBUTES LOGIN ON LOGIN.OBJECT_ID = USERS.OBJECT_ID " +
            " AND LOGIN.ATTR_ID = " + EAVAttrTypesID.LOGIN +
            " LEFT JOIN ATTRIBUTES PHONENUMBER ON PHONENUMBER.OBJECT_ID = USERS.OBJECT_ID " +
            " AND PHONENUMBER.ATTR_ID = " + EAVAttrTypesID.PHONE_NUMBER +
            " LEFT JOIN ATTRIBUTES BIRTHDATE ON BIRTHDATE.OBJECT_ID = USERS.OBJECT_ID " +
            " AND BIRTHDATE.ATTR_ID = " + EAVAttrTypesID.BIRTH_DATE +
            " LEFT JOIN ATTRIBUTES EMAIL ON EMAIL.OBJECT_ID = USERS.OBJECT_ID " +
            " AND EMAIL.ATTR_ID = " + EAVAttrTypesID.EMAIL +
            " LEFT JOIN ATTRIBUTES ROLE ON ROLE.OBJECT_ID = USERS.OBJECT_ID " +
            " AND ROLE.ATTR_ID = " + EAVAttrTypesID.ROLE +
            " LEFT JOIN ATTRIBUTES PASSWORD ON PASSWORD.OBJECT_ID = USERS.OBJECT_ID " +
            " AND PASSWORD.ATTR_ID = " + EAVAttrTypesID.PASSWORD +
            " WHERE USERS.OBJECT_TYPE_ID = " + EAVObjTypesID.USER +
            " AND LOGIN.VALUE = ?";

    String SELECT_USERS_BY_ROLE = SELECT_USERS +
            " AND ROLE.LIST_VALUE_ID = ?";

    String SELECT_USERS_BY_ROLE_AND_STATUS = "SELECT USERS.OBJECT_ID AS USER_ID, LOGIN.VALUE AS USER_LOGIN, USERS.NAME AS USER_NAME, " +
            "            PHONENUMBER.VALUE AS PHONENUMBER, EMAIL.VALUE AS EMAIL, BIRTHDATE.DATE_VALUE AS BIRTHDATE, " +
            "            ROLE.LIST_VALUE_ID AS ROLE, PASSWORD.VALUE AS PASSWORD " +
            "            FROM OBJECTS USERS " +
            "            LEFT JOIN ATTRIBUTES LOGIN ON LOGIN.OBJECT_ID = USERS.OBJECT_ID " +
            "            AND LOGIN.ATTR_ID = 1" +
            "            LEFT JOIN ATTRIBUTES PHONENUMBER ON PHONENUMBER.OBJECT_ID = USERS.OBJECT_ID " +
            "            AND PHONENUMBER.ATTR_ID = 4" +
            "            LEFT JOIN ATTRIBUTES BIRTHDATE ON BIRTHDATE.OBJECT_ID = USERS.OBJECT_ID " +
            "            AND BIRTHDATE.ATTR_ID = 5" +
            "            LEFT JOIN ATTRIBUTES EMAIL ON EMAIL.OBJECT_ID = USERS.OBJECT_ID " +
            "            AND EMAIL.ATTR_ID = 6" +
            "            LEFT JOIN ATTRIBUTES ROLE ON ROLE.OBJECT_ID = USERS.OBJECT_ID " +
            "            AND ROLE.ATTR_ID = 7" +
            "            LEFT JOIN ATTRIBUTES PASSWORD ON PASSWORD.OBJECT_ID = USERS.OBJECT_ID " +
            "            AND PASSWORD.ATTR_ID = 2 " +
            "            LEFT JOIN OBJECTS EMP ON EMP.PARENT_ID = USERS.OBJECT_ID" +
            "            LEFT JOIN ATTRIBUTES STATUS ON STATUS.OBJECT_ID = EMP.OBJECT_ID" +
            "            AND STATUS.ATTR_ID = 10" +
            "            WHERE USERS.OBJECT_TYPE_ID = 1 AND ROLE.LIST_VALUE_ID = ? " +
            "            AND STATUS.LIST_VALUE_ID = ?";

    String USER_NAME = "USER_NAME";
    String PASSWORD = "PASSWORD";
    String PHONENUMBER = "PHONENUMBER";
    String BIRTHDATE = "BIRTHDATE";
    String EMAIL = "EMAIL";
    String ROLE = "ROLE";
    String LOGIN = "USER_LOGIN";
    String USER_ID = "USER_ID";


    BigInteger createUser(User user);
    void editUser(User user);
    User getUserByLogin(BigInteger login);
    User getUserById(BigInteger id);
    User getUserByEmail(String email);
    Collection<User> getUsersList();
    Collection<User> getUsersListByRole(UserRole role);
    Collection<User> getUsersListByRoleAndStatus(UserRole role, EmployerStatus status);
}
