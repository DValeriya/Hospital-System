package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.EmployerStatus;
import com.teama.hospitalsystem.util.UserRole;

import java.math.BigInteger;
import java.util.Collection;

public interface UsersService {

    BigInteger createUser(User user);
    void editUser(User user);
    User getUserByLogin(BigInteger login);
    User getUserById(BigInteger id);
    User getUserByEmail(String email);
    Collection<User> getUsersList();
    Collection<User> getUsersListByRole(UserRole role);
    Collection<User> getUsersListByRoleAndStatus(UserRole role, EmployerStatus status);
}
