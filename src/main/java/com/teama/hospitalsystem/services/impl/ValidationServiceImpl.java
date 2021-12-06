package com.teama.hospitalsystem.services.impl;

import com.teama.hospitalsystem.exceptions.ValidationException;
import com.teama.hospitalsystem.models.Appointment;
import com.teama.hospitalsystem.models.EmployerData;
import com.teama.hospitalsystem.models.WorkDay;
import com.teama.hospitalsystem.services.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);
    private final Validator validator;

    public ValidationServiceImpl(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }
    @Override
    public <T> void validateBean(T object) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (violations.size()>0) throw new ValidationException(violations);
    }
}
