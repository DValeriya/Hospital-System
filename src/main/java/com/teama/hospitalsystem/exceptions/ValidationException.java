package com.teama.hospitalsystem.exceptions;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationException extends RuntimeException {
    private Map<String, String> map;
    public ValidationException(){
        super();
    }
    public <T> ValidationException(Set<ConstraintViolation<T>> violations){
        super(String.format("{%d} violations has been found", violations.size()));
        Map<String, String> map = new HashMap<>();
        for (ConstraintViolation<?> v: violations
        ) {
            map.put(v.getPropertyPath().toString(), v.getMessage() + " : " + v.getInvalidValue());
//            toReturn.append("\t in ").append(v.getRootBeanClass().toString()).append(" : ").append(v.getMessage())
//                    .append("\n\t\t").append(v.getPropertyPath()).append(" : ").append(v.getInvalidValue()).append('\n');
        }
        this.map = map;
    }
    public Map<?,?> GetViolationsParams(){
        return map;
    }
//    private static <T> String formMessage(Set<ConstraintViolation<T>> violations){
//        Map<String, String> map = new HashMap<>();
////        StringBuilder toReturn = new StringBuilder("Such violations has been found:\n");
//
//        this.map = map;
////        return toReturn.toString();
//    }
}
