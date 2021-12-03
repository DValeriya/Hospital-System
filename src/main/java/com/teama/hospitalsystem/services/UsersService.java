package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.EmployerStatus;
import com.teama.hospitalsystem.util.UserRole;

import java.math.BigInteger;
import java.util.Collection;

public interface UsersService {

    void createUser(User user);
    void editUser(User user);
    User getUserByLoginAndPassword(BigInteger login, String password);
    User getUserById(BigInteger id);
    Collection<User> getUsersList();
    Collection<User> getUsersListByRole(UserRole role);
    Collection<User> getUsersListByRoleAndStatus(UserRole role, EmployerStatus status);
}
