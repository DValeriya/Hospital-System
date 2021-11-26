package com.teama.hospitalsystem.exceptions;

public class EntityNotCreatedException extends RuntimeException{

    public EntityNotCreatedException(Class<?> aClass, Object object) {
        super(generateMessage(aClass.getSimpleName(), object));
    }

    private static String generateMessage(String className, Object object) {
        return className + "was not created entity " + object.toString();
    }
}
