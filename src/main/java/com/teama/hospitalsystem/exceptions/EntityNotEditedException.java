package com.teama.hospitalsystem.exceptions;

public class EntityNotEditedException extends RuntimeException{

    public EntityNotEditedException (Class<?> aClass, Object object) {
        super(generateMessage(aClass.getSimpleName(), object));
    }

    private static String generateMessage(String className, Object object) {
        return className + "was not edited entity " + object.toString();
    }
}
