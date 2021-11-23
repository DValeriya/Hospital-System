package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.UserDAO;
import com.teama.hospitalsystem.exceptions.EntityNotFoundException;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.UsersService;
import com.teama.hospitalsystem.util.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserDAO dao;

    public UsersServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public void createUser(User user) throws DataAccessException {
        dao.createUser(user);
    }

    @Override
    public void editUser(User user) throws DataAccessException {
        dao.editUser(user);
    }

    @Override
    public User getUserByLoginAndPassword(BigInteger login, String password)
            throws DataAccessException, EntityNotFoundException {
        try {
            return dao.getUserByLoginAndPassword(login, password);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User getUserById(BigInteger id) throws DataAccessException, EntityNotFoundException {
        try {
            return dao.getUserById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException();
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
}
