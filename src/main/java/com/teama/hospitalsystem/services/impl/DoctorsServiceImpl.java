package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.DoctorDataDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.DoctorData;
import com.teama.hospitalsystem.services.DoctorSpecializationService;
import com.teama.hospitalsystem.services.DoctorsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class DoctorsServiceImpl implements DoctorsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorsServiceImpl.class);
    private final DoctorDataDAO doctor;
    private final DoctorSpecializationService doctorSpecializationService;

    public DoctorsServiceImpl(DoctorDataDAO doctor, DoctorSpecializationService doctorSpecializationService) {
        this.doctor = doctor;
        this.doctorSpecializationService = doctorSpecializationService;
    }

    @Override
    public DoctorData createDoctor(DoctorData doctorData, BigInteger employerId) throws DAOException{
        try {
            BigInteger getNewId = doctor.createDoctorData(doctorData, employerId); //TODO
            return getDoctorDataId(getNewId);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public void editDoctor(DoctorData doctorData) throws DAOException{
        try {
            doctor.editDoctorData(doctorData);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public DoctorData getDoctorDataById(BigInteger id) throws DAOException {
        try {
            DoctorData doctorData = doctor.getDoctorDataByDoctorId(id);
            doctorSpecializationService.getDoctorSpecializationById(id);
            return doctorData;
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public DoctorData getDoctorDataId(BigInteger id) throws DAOException {
        try {
            DoctorData doctorData = doctor.getDoctorDataId(id);
            doctorSpecializationService.getDoctorSpecializationById(id);
            return doctorData;
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getLocalizedMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public List<DoctorData> getDoctorListBySpecialization(BigInteger specializationId) {
        return doctor.getDoctorListBySpecialization(specializationId);
    }
}
