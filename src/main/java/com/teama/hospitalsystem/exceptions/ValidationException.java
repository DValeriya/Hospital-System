package com.teama.hospitalsystem.exceptions;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationException extends RuntimeException {
    private Map<String, String> map;
    private StringBuilder formattedMessage;
    public ValidationException(){
        super();
    }
    public <T> ValidationException(Set<ConstraintViolation<T>> violations){
        super(String.format("{%d} violations has been found", violations.size()));
        Map<String, String> map = new HashMap<>();
        formattedMessage = new StringBuilder("Founded violations:\n");
        for (ConstraintViolation<?> v: violations
        ) {
            map.put(v.getPropertyPath().toString(), v.getMessage() + " : " + v.getInvalidValue());
            formattedMessage.append("\t in ").append(v.getRootBeanClass().toString()).append(" : ").append(v.getMessage())
                    .append("\n\t\t").append(v.getPropertyPath()).append(" : ").append(v.getInvalidValue()).append('\n');
        }
        this.map = map;
    }
    public Map<String, String> GetViolationsParams(){
        return map;
    }
    public String getFormattedMessage() {
        return formattedMessage.toString();
    }
}
