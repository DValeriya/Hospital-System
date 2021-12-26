package com.teama.hospitalsystem.services;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.ResetPasswordToken;

import java.math.BigInteger;

public interface ResetPasswordTokenService {
    BigInteger deleteToken(BigInteger tokenId) throws DAOException;
    ResetPasswordToken getTokenByValue(String token) throws DAOException;
    ResetPasswordToken getTokenById(BigInteger tokenId) throws DAOException;
    ResetPasswordToken getTokenByUserId(BigInteger userId);
    ResetPasswordToken saveToken(String token, BigInteger userId) throws DAOException;
    void updateToken(ResetPasswordToken resetPasswordToken) throws DAOException;
}
