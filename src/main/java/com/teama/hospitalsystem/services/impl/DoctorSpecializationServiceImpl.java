package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.DoctorSpecializationDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.DoctorSpecialization;
import com.teama.hospitalsystem.services.DoctorSpecializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Service
public class DoctorSpecializationServiceImpl implements DoctorSpecializationService {

    private final DoctorSpecializationDAO dao;
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorSpecializationServiceImpl.class);

    public DoctorSpecializationServiceImpl(DoctorSpecializationDAO dao) {
        this.dao = dao;
    }

    @Override
    public DoctorSpecialization createDoctorSpecialization(DoctorSpecialization spec) throws DAOException {
        try {
            BigInteger getNewId = dao.createDoctorSpecialization(spec);
            return getDoctorSpecializationById(getNewId);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public void deleteDoctorSpecialization(DoctorSpecialization spec) throws DAOException {
        try {
            dao.deleteDoctorSpecialization(spec);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public DoctorSpecialization getDoctorSpecializationById(BigInteger id) throws DAOException{
        try {
            return dao.getDoctorSpecializationByDoctorDataId(id);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public Collection<DoctorSpecialization> getDoctorSpecializationList() throws DataAccessException {
        return dao.getDoctorSpecializationList();
    }
}
