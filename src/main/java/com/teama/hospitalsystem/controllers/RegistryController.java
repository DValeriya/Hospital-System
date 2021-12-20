package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.RegistryService;
import com.teama.hospitalsystem.util.EmployerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;

@RestController
@RequestMapping("/api/registry")
public class RegistryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistryController.class);
    private final RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRegistry(@RequestBody User newUser) {
        try {
            BigInteger user = registryService.createRegistry(newUser);
            return ResponseEntity.ok(user);
        } catch (DAOException daoException) {
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> editRegistry(@PathVariable BigInteger id, @RequestBody User newUser) {
        try {
            User user = registryService.getRegistryById(id);

            user.setEmail(newUser.getEmail());
            user.setBirthDate(newUser.getBirthDate());
            user.setName(newUser.getName());
            user.setPassword(newUser.getPassword());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setRole(newUser.getRole());

            user.getEmployerData().setStatus(newUser.getEmployerData().getStatus());
            user.getEmployerData().setEndWorkingTime(newUser.getEmployerData().getEndWorkingTime());
            user.getEmployerData().setStartWorkingTime(newUser.getEmployerData().getStartWorkingTime());

            registryService.editRegistry(user);

            return ResponseEntity.ok(user);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getMessage(), daoException, daoException.getCause());
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegistryById(@PathVariable BigInteger id) {
        try {
            User user = registryService.getRegistryById(id);
            return ResponseEntity.ok(user);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getMessage(), daoException, daoException.getCause());
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @GetMapping("/registryList")
    public ResponseEntity<?> getRegistryList() {
        Collection<User> usersList = registryService.getRegistryList();
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/registryListByStatus")
    public ResponseEntity<?> getRegistryListByStatus(@RequestBody EmployerStatus employerStatus) {
        Collection<User> usersList = registryService.getRegistryListByStatus(employerStatus);
        return ResponseEntity.ok(usersList);
    }
}