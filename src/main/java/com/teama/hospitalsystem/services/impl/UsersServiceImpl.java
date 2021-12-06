package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.UserDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.exceptions.EntityNotFoundException;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.UsersService;
import com.teama.hospitalsystem.util.EmployerStatus;
import com.teama.hospitalsystem.util.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Service
public class UsersServiceImpl implements UsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistryServiceImpl.class);

    private final UserDAO dao;

    public UsersServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public BigInteger createUser(User user) throws DAOException{
        try{
            return dao.createUser(user);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public void editUser(User user) throws DAOException {
        try{
            dao.editUser(user);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public User getUserByLoginAndPassword(BigInteger login, String password) throws DAOException{
        try {
            return dao.getUserByLoginAndPassword(login, password);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public User getUserById(BigInteger id) throws DAOException{
        try {
            return dao.getUserById(id);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public Collection<User> getUsersList() throws DataAccessException {
        return dao.getUsersList();
    }

    @Override
    public Collection<User> getUsersListByRole(UserRole role) throws DataAccessException {
        return dao.getUsersListByRole(role);
    }

    @Override
    public Collection<User> getUsersListByRoleAndStatus(UserRole role, EmployerStatus status) throws DataAccessException {
        return dao.getUsersListByRoleAndStatus(role, status);
    }
}