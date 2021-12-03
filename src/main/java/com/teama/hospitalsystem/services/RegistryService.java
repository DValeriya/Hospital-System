package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.util.EmployerStatus;

import java.math.BigInteger;
import java.util.Collection;

public interface RegistryService {

    void createRegistry(User user);
    void editRegistry(User user);
    void changeRegistryStatus(EmployerData employerData);
    User getRegistryById(BigInteger userId);
    Collection<User> getRegistryListByStatus(EmployerStatus employerStatus);
    Collection<User> getRegistryList();

}
