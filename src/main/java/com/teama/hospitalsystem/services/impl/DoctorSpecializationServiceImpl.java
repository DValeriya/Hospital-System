package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.dao.DoctorSpecializationDAO;
import com.teama.hospitalsystem.exceptions.EntityNotFoundException;
import com.teama.hospitalsystem.models.DoctorSpecialization;
import com.teama.hospitalsystem.services.DoctorSpecializationService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Service
public class DoctorSpecializationServiceImpl implements DoctorSpecializationService {

    private final DoctorSpecializationDAO dao;

    public DoctorSpecializationServiceImpl(DoctorSpecializationDAO dao) {
        this.dao = dao;
    }

    @Override
    public void createDoctorSpecialization(DoctorSpecialization spec) throws DataAccessException {
        dao.createDoctorSpecialization(spec);
    }

    @Override
    public void deleteDoctorSpecialization(DoctorSpecialization spec) throws DataAccessException {
        dao.deleteDoctorSpecialization(spec);
    }

    @Override
    public DoctorSpecialization getDoctorSpecializationById(BigInteger id)
            throws DataAccessException, EntityNotFoundException {
        try {
            return dao.getDoctorSpecializationByDoctorDataId(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Collection<DoctorSpecialization> getDoctorSpecializationList() throws DataAccessException {
        return dao.getDoctorSpecializationList();
    }

    @Override
    public boolean isDoctorSpecializationUsed() {
        // TODO: Need to be implemented
        return false;
    }
}
