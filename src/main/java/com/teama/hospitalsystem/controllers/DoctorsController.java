package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.DoctorData;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.DoctorsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorsController.class);
    private final DoctorsService doctorsService;

    public DoctorsController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody User user) {
        try {
            DoctorData doctor = doctorsService.createDoctor(user);
            return ResponseEntity.ok(doctor);
        } catch (DAOException daoException) {
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> editDoctor(@PathVariable BigInteger id, @RequestBody DoctorData doctorData) {
        try {
            DoctorData doctor = doctorsService.getDoctorDataId(id);

            doctor.setAppointmentDuration(doctorData.getAppointmentDuration());

            doctorsService.editDoctor(doctor);

            return ResponseEntity.ok(doctor);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getMessage(), daoException, daoException.getCause());
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorDataById(@PathVariable BigInteger id) {
        try {
            DoctorData doctorData = doctorsService.getDoctorDataId(id);
            return ResponseEntity.ok(doctorData);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getMessage(), daoException, daoException.getCause());
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getDoctorsBySpecialization(@RequestParam("spec-id") BigInteger specializationId) {
        List<DoctorData> doctorListBySpecialization = doctorsService.getDoctorListBySpecialization(specializationId);
        return ResponseEntity.ok(doctorListBySpecialization);
    }
}
