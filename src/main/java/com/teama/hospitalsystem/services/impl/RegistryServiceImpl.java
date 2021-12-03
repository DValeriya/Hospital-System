package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.EmployerDataDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.RegistryService;
import com.teama.hospitalsystem.util.EmployerStatus;
import com.teama.hospitalsystem.util.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Service
public class RegistryServiceImpl implements RegistryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistryServiceImpl.class);

    private final EmployerDataDAO employerDataDAO;
    private final UsersServiceImpl usersServiceImpl;

    public RegistryServiceImpl(EmployerDataDAO employerDataDAO, UsersServiceImpl usersServiceImpl){
        this.employerDataDAO = employerDataDAO;
        this.usersServiceImpl = usersServiceImpl;
    }

    @Override
    public void createRegistry(User user) {
        try{
            usersServiceImpl.createUser(user);
            employerDataDAO.createEmployerData(user.getEmployerData(), user.getId());
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public void editRegistry(User user) {
        try{
            employerDataDAO.editEmployerData(user.getEmployerData());
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public void changeRegistryStatus(EmployerData employerData) {
        try{
            employerDataDAO.changeEmployerStatus(employerData);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public User getRegistryById(BigInteger userId) {
        User user = usersServiceImpl.getUserById(userId);
        user.setEmployerData(employerDataDAO.getEmployerDataByUserId(userId));
        return user;
    }

    @Override
    public Collection<User> getRegistryList() {
        return usersServiceImpl.getUsersListByRole(UserRole.REGISTRY);
    }

    @Override
    public Collection<User> getRegistryListByStatus(EmployerStatus employerStatus) {
        return usersServiceImpl.getUsersListByRoleAndStatus(UserRole.REGISTRY, employerStatus);
    }
}
