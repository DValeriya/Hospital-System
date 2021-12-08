package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.DoctorDataDAO;
import com.teama.hospitalsystem.dao.EmployerDataDAO;
import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.DoctorData;
import com.teama.hospitalsystem.models.DoctorSpecialization;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.DoctorSpecializationService;
import com.teama.hospitalsystem.services.DoctorsService;
import com.teama.hospitalsystem.services.RegistryService;
import com.teama.hospitalsystem.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class DoctorsServiceImpl implements DoctorsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorsServiceImpl.class);
    private final DoctorDataDAO doctor;
    private final DoctorSpecializationService doctorSpecializationService;
    private final UsersService usersService;
    private final EmployerDataDAO employerDataDAO;

    public DoctorsServiceImpl(DoctorDataDAO doctor, DoctorSpecializationService doctorSpecializationService, RegistryService registryService, UsersService usersService, EmployerDataDAO employerDataDAO) {
        this.doctor = doctor;
        this.doctorSpecializationService = doctorSpecializationService;
        this.usersService = usersService;
        this.employerDataDAO = employerDataDAO;
    }

    @Override
    @Transactional
    public DoctorData createDoctor(User user) throws DAOException{
        try {
            BigInteger idUser = usersService.createUser(user);
            BigInteger idEmployer = employerDataDAO.createEmployerData(user.getEmployerData(), idUser);
            BigInteger getNewId = doctor.createDoctorData(user.getEmployerData().getDoctorData(), idEmployer);
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
        DoctorSpecialization doctorSpecialization = doctorSpecializationService.getDoctorSpecializationById(specializationId);

        List<DoctorData> doctorListBySpecialization = doctor.getDoctorListBySpecialization(specializationId);

        doctorListBySpecialization.forEach(doctorData -> doctorData.setSpec(doctorSpecialization));

        return doctorListBySpecialization;
    }
}
