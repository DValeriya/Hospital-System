package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.exceptions.DAOException;
import com.teama.hospitalsystem.models.User;
import com.teama.hospitalsystem.services.UsersService;
import com.teama.hospitalsystem.util.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        try {
            System.out.println(newUser);
            BigInteger userId = usersService.createUser(newUser);
            User user = usersService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (DAOException daoException) {
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> editUser(@PathVariable BigInteger id, @RequestBody User newUser) {
        try {
            User user = usersService.getUserById(id);

            user.setEmail(newUser.getEmail());
            user.setBirthDate(newUser.getBirthDate());
            user.setName(newUser.getName());
            user.setPassword(newUser.getPassword());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setRole(newUser.getRole());

            usersService.editUser(user);

            return ResponseEntity.ok(user);
        } catch (DAOException daoException){
            LOGGER.error(daoException.getMessage(), daoException, daoException.getCause());
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    //TODO:: (Failed to get User by Id)
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable BigInteger id) {
        try {
            User user = usersService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (DAOException daoException) {
            LOGGER.error(daoException.getMessage(), daoException, daoException.getCause());
            return ResponseEntity.badRequest().body(daoException.getMessage());
        }
    }

    @GetMapping("/userList")
    public ResponseEntity<?> getUserList() {
        Collection<User> usersList = usersService.getUsersList();
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/getPatientByLoginAndPassword")
    public ResponseEntity<?> getPatientByLoginAndPassword(BigInteger login) {
        User user = usersService.getUserByLogin(login);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/userListByRole")
    public ResponseEntity<?> getUserListByRole(UserRole role) {
        Collection<User> usersList = usersService.getUsersListByRole(role);
        return ResponseEntity.ok(usersList);
    }


}
