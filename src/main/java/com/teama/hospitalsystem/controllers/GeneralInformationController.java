package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.GeneralInformation;
import com.teama.hospitalsystem.services.GeneralInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/general-info")
public class GeneralInformationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralInformationController.class);
    private final GeneralInformationService generalInformationService;

    public GeneralInformationController(GeneralInformationService generalInformationService) {
        this.generalInformationService = generalInformationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGeneralInformation(@PathVariable BigInteger id) {
        try {
            GeneralInformation generalInformation = generalInformationService.getGeneralInformation(id);
            return ResponseEntity.ok(generalInformation);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getMessage(), daoException);
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @PostMapping("/create/general-info")
    public ResponseEntity<?> createGeneralInformation (@RequestBody GeneralInformation generalInformation) {
        try {
            GeneralInformation generalInfo = generalInformationService.createGeneralInformation(generalInformation);
            return ResponseEntity.ok(generalInfo);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getMessage(), daoException);
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @PutMapping("/edit/general-info/{id}")
    public ResponseEntity<?> editGeneralInformation(@PathVariable BigInteger id, @RequestBody GeneralInformation generalInformation) {
        try {
            GeneralInformation generalInfo = generalInformationService.getGeneralInformation(id);

            generalInfo.setAddress(generalInformation.getAddress());
            generalInfo.setPhone(generalInformation.getPhone());
            generalInfo.setWorkingStart(generalInformation.getWorkingStart());
            generalInfo.setWorkingEnd(generalInformation.getWorkingEnd());

            generalInformationService.editGeneralInformation(generalInfo);

            return ResponseEntity.ok(generalInfo);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getMessage(), daoException);
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

}
