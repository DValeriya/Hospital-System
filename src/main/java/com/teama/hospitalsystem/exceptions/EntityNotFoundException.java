package com.teama.hospitalsystem.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(Class<?> aClass, String paramName, String paramValue ) {
        super(generateMessage(aClass.getSimpleName(), paramName, paramValue));
    }

    private static String generateMessage(String className, String paramName, String paramValue) {
        return className + "was not found entity by parameter {" + paramName + " = " + paramValue + "}";
    }
}