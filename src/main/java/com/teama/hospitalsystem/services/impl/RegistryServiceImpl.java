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
    public BigInteger createRegistry(User user) throws DAOException{
        try{
            BigInteger id = usersServiceImpl.createUser(user);
            return employerDataDAO.createEmployerData(user.getEmployerData(), id);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public void editRegistry(User user) throws DAOException{
        try{
            employerDataDAO.editEmployerData(user.getEmployerData());
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public void changeRegistryStatus(EmployerData employerData) throws DAOException{
        try{
            employerDataDAO.changeEmployerStatus(employerData);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public User getRegistryById(BigInteger userId) throws DAOException{
        try{
            User user = usersServiceImpl.getUserById(userId);
            user.setEmployerData(employerDataDAO.getEmployerDataByUserId(userId));
            return user;
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public Collection<User> getRegistryList() {
        try{
            return usersServiceImpl.getUsersListByRole(UserRole.REGISTRY);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public Collection<User> getRegistryListByStatus(EmployerStatus employerStatus) {
        try{
            return usersServiceImpl.getUsersListByRoleAndStatus(UserRole.REGISTRY, employerStatus);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }
}