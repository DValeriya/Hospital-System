package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.ResetPasswordTokenDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.ResetPasswordToken;
import com.teama.hospitalsystem.services.ResetPasswordTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ResetPasswordTokenServiceImpl implements ResetPasswordTokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResetPasswordTokenServiceImpl.class);
    private final ResetPasswordTokenDAO resetPasswordTokenDAO;

    public ResetPasswordTokenServiceImpl(ResetPasswordTokenDAO resetPasswordTokenDAO) {
        this.resetPasswordTokenDAO = resetPasswordTokenDAO;
    }

    @Override
    public BigInteger deleteToken(BigInteger tokenId) throws DAOException {
        try {
            return resetPasswordTokenDAO.deleteToken(tokenId);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public ResetPasswordToken getTokenByValue(String token) throws DAOException {
        try {
            return resetPasswordTokenDAO.getTokenByValue(token);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public ResetPasswordToken getTokenById(BigInteger tokenId) throws DAOException {
        try {
            return resetPasswordTokenDAO.getTokenById(tokenId);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public ResetPasswordToken getTokenByUserId(BigInteger userId) throws DAOException {
        try {
            return resetPasswordTokenDAO.getTokenByUserId(userId);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public ResetPasswordToken saveToken(String token, BigInteger userId) throws DAOException{
        try {
            BigInteger resetTokenId = resetPasswordTokenDAO.saveToken(token, userId);
            return getTokenById(resetTokenId);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public void updateToken(ResetPasswordToken resetPasswordToken) throws DAOException{
        try {
            resetPasswordTokenDAO.updateToken(resetPasswordToken);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }
}
