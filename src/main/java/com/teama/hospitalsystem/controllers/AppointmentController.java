package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.exceptions.ValidationException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.AppointmentService;
import com.teama.hospitalsystem.services.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private final AppointmentService appointmentService;
    private final ValidationService validationService;

    public AppointmentController(AppointmentService appointmentService, ValidationService validationService){
        this.appointmentService = appointmentService;
        this.validationService = validationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Appointment appointment){
        try{
            validationService.validateBean(appointment);
            Appointment toReturn = appointmentService.createAppointment(appointment);
            return ResponseEntity.ok(toReturn);
        } catch (ValidationException ex){
            LOGGER.error(ex.getFormattedMessage(), ex);
            return ResponseEntity.badRequest().body(ex.GetViolationsParams());
        } catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable BigInteger id){
        try{
            Appointment toReturn = appointmentService.getAppointmentById(id);
            return ResponseEntity.ok(toReturn);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editAppointment(@RequestBody Appointment appointment){
        try{
            validationService.validateBean(appointment);
            appointmentService.editAppointment(appointment);
            return ResponseEntity.ok(appointment);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch (ValidationException ex){
            LOGGER.error(ex.getFormattedMessage(), ex);
            return ResponseEntity.badRequest().body(ex.GetViolationsParams());
        }
    }

    @GetMapping("/list/doctor/{doctorId}")
    public ResponseEntity<?> getAppointmentListByDoctorId(@PathVariable BigInteger doctorId){
        try{
            Collection<Appointment> appointments = appointmentService.getAppointmentByDoctorId(doctorId);
            return ResponseEntity.ok(appointments);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/list/doctorForADay/{doctorId}")
    public ResponseEntity<?> getAppointmentListByDoctorIdForADay(@PathVariable BigInteger doctorId, @RequestBody WorkDay day){
        try{
            Collection<Appointment> appointments = appointmentService.getAppointmentByDoctorIdForADay(doctorId, day);
            return ResponseEntity.ok(appointments);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list/doctorForAMonth/{doctorId}/{month}/{year}")
    public ResponseEntity<?> getAppointmentListByDoctorIdForAMonth(@PathVariable BigInteger doctorId, @PathVariable int month, @PathVariable int year){
        try{
            Collection<Appointment> appointments = appointmentService.getAppointmentByDoctorIdForAMonth(doctorId, month, year);
            return ResponseEntity.ok(appointments);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list/patient/{patientId}")
    public ResponseEntity<?> getAppointmentByPatientId(@PathVariable BigInteger patientId){
        try{
            Collection<Appointment> appointments = appointmentService.getAppointmentByPatientId(patientId);
            return ResponseEntity.ok(appointments);
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list/patientForADay/{patientId}/{date}")
    public ResponseEntity<?> getAppointmentByPatientIdForADate(@PathVariable BigInteger patientId, @PathVariable String date){
        try{
            Date un = sdf.parse(date);
            Collection<Appointment> toReturn = appointmentService.getAppointmentByPatientIdForADate(patientId, un);
            return ResponseEntity.ok(toReturn);
        }catch (DAOException | ParseException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/start/{id}")
    public ResponseEntity<?> startAppointment(@PathVariable BigInteger id){
        try {
            appointmentService.startAppointment(id);
            return ResponseEntity.ok("Appointment started");
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<?> endAppointment(@PathVariable BigInteger id){
        try {
            appointmentService.endAppointment(id);
            return ResponseEntity.ok("Appointment ended");
        }catch (DAOException ex){
            LOGGER.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
