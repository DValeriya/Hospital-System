package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.GeneralInformationDAO;
import com.teama.hospitalsystem.exceptions.EntityNotCreatedException;
import com.teama.hospitalsystem.exceptions.EntityNotEditedException;
import com.teama.hospitalsystem.exceptions.EntityNotFoundException;
import com.teama.hospitalsystem.models.GeneralInformation;
import com.teama.hospitalsystem.services.GeneralInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
    public void editGeneralInformation(GeneralInformation generalInformation) {
        try {
            generalInfo.editGeneralInformation(generalInformation);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new EntityNotEditedException(GeneralInformation.class, generalInformation);
        }
    }

    @Override
    public GeneralInformation getGeneralInformation(BigInteger id) {
        try {
            return generalInfo.getGeneralInformation(id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new EntityNotFoundException(GeneralInformation.class, "id", id.toString());
        }
    }

    @Override
    public void createGeneralInformation(GeneralInformation generalInformation) {
        try {
            generalInfo.createGeneralInformation(generalInformation);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException.getLocalizedMessage(), dataAccessException);
            throw new EntityNotCreatedException(GeneralInformation.class, generalInformation);
        }
    }
}
