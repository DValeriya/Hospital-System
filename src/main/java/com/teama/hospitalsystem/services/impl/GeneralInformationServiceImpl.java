package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.GeneralInformationDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.GeneralInformation;
import com.teama.hospitalsystem.services.GeneralInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class GeneralInformationServiceImpl implements GeneralInformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralInformationServiceImpl.class);
    private final GeneralInformationDAO generalInfo;

    public GeneralInformationServiceImpl(GeneralInformationDAO generalInformation) {
        this.generalInfo = generalInformation;
    }

    @Override
    public void editGeneralInformation(GeneralInformation generalInformation) throws DAOException {
        try {
            generalInfo.editGeneralInformation(generalInformation);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public GeneralInformation getGeneralInformation(BigInteger id) throws DAOException {
        try {
            return generalInfo.getGeneralInformation(id);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public GeneralInformation createGeneralInformation(GeneralInformation generalInformation) throws DAOException {
        try {
            BigInteger getNewId = generalInfo.createGeneralInformation(generalInformation);
            return generalInfo.getGeneralInformation(getNewId);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }
}
