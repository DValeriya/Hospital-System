package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.exceptions.ValidationException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.ValidationService;
import com.teama.hospitalsystem.services.WorkDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/workDay")
public class WorkDayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkDayController.class);
    private final WorkDayService workDayService;
    private final ValidationService validationService;

    public WorkDayController(WorkDayService workDayService, ValidationService validationService){
        this.workDayService = workDayService;
        this.validationService = validationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWorkDay(@RequestBody WorkDay workDay){
        try {
            validationService.validateBean(workDay);
            WorkDay toReturn = workDayService.createWorkDay(workDay);
            return ResponseEntity.ok(toReturn);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch (ValidationException ex){
            LOGGER.error(ex.getFormattedMessage(), ex);
            return ResponseEntity.badRequest().body(ex.GetViolationsParams());
        }
    }

    @DeleteMapping("/delete/{workDayId}")
    public ResponseEntity<?> deleteWorkDay(@PathVariable BigInteger workDayId){
        try {
            workDayService.deleteWorkDay(workDayId);
            return ResponseEntity.ok("WorkDay deleted successful");
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/getWorkDaysByEmployerId/{employerId}")
    public ResponseEntity<?> getWorkDays(@PathVariable BigInteger employerId){
        try {
            Collection<WorkDay> workDays = workDayService.getWorkDaysByEmployerId(employerId);
            return ResponseEntity.ok(workDays);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/getWorkDaysByDate/{date}")
    public ResponseEntity<?> getWorkDays(@PathVariable Date date){
        try {
            Collection<WorkDay> workDays = workDayService.getWorkDaysByDate(date);
            return ResponseEntity.ok(workDays);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/getAppointmentByWorkDayId/{workDayId}")
    public ResponseEntity<?> getWAppointments(@PathVariable BigInteger workDayId){
        try {
            Collection<Appointment> appointments = workDayService.getAppointments(workDayId);
            return ResponseEntity.ok(appointments);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
