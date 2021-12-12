package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.DoctorSpecialization;
import com.teama.hospitalsystem.services.DoctorSpecializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;

@RestController
@RequestMapping("/api/specialization")
public class DoctorSpecializationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorSpecializationController.class);
    private final DoctorSpecializationService doctorSpecializationService;

    public DoctorSpecializationController(DoctorSpecializationService doctorSpecializationService) {
        this.doctorSpecializationService = doctorSpecializationService;
    }

    @RequestMapping("/create")
    public ResponseEntity<?> createDoctorSpecialization(@RequestBody DoctorSpecialization doctorSpecialization) {
        try {
            DoctorSpecialization specialization = doctorSpecializationService.createDoctorSpecialization(doctorSpecialization);
            return ResponseEntity.ok(specialization);
        } catch (DAOException daoException) {
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorSpecializationById(@PathVariable BigInteger id) {
        try {
            DoctorSpecialization specialization = doctorSpecializationService.getDoctorSpecializationById(id);
            return ResponseEntity.ok(specialization);
        } catch (DAOException daoException) {
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getDoctorSpecializationList() {
        Collection<DoctorSpecialization> doctorSpecializationList = doctorSpecializationService.getDoctorSpecializationList();
        return ResponseEntity.ok(doctorSpecializationList);
    }
}
