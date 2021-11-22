package com.teama.hospitalsystem.dao;

import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.EAVAttrTypesID;
import com.teama.hospitalsystem.util.EAVObjTypesID;
import com.teama.hospitalsystem.util.UserRole;

import java.math.BigInteger;
import java.util.Collection;

public interface UserDAO {
    String CREATE_USER_PROCEDURE_NAME = "CREATE_USER";
    String EDIT_USER_PROCEDURE_NAME = "EDIT_USER";

    String SELECT_USERS = "SELECT USERS.OBJECT_ID AS id, LOGIN.VALUE AS login, USERS.NAME AS name, " +
            "PHONENUMBER.VALUE AS phone, EMAIL.VALUE AS email, BIRTHDATE.DATE_VALUE AS birth, " +
            "ROLE.LIST_VALUE_ID AS role " +
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
            " WHERE USERS.OBJECT_TYPE_ID = " + EAVObjTypesID.USER;

    String SELECT_USER_BY_ID = SELECT_USERS + " AND USERS.OBJECT_ID = ?";

    String SELECT_USER_BY_LOGIN_AND_PASSWORD = SELECT_USERS +
            " AND LOGIN.VALUE = ? AND PASSWORD.VALUE = ?";

    String SELECT_USERS_BY_ROLE = SELECT_USERS +
            " AND ROLE.LIST_VALUE_ID = ?";

    void createUser(User user);
    void editUser(User user);
    User getUserByLoginAndPassword(BigInteger login, String password);
    User getUserById(BigInteger id);
    Collection<User> getUsersList();
    Collection<User> getUsersListByRole(UserRole role);
}
