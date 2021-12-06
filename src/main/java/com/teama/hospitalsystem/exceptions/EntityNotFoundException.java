package com.teama.hospitalsystem.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(Class<?> aClass, String paramName, String paramValue ) {
        super(generateMessage(aClass.getSimpleName(), paramName, paramValue));
    }

    public EntityNotFoundException(Class<?> aClass, String[] paramsName, String[] paramsValue){
        super(generateMessage(aClass.getSimpleName(), paramsName, paramsValue));
    }

    private static String generateMessage(String className, String paramName, String paramValue) {
        return className + "was not found entity by parameter {" + paramName + " = " + paramValue + "}";
    }

    private static String generateMessage(String classname, String[] paramsName, String[] paramsValue){
        StringBuilder toReturn = new StringBuilder(classname + "was not found entity by parameters :\n{");
        for (int i = 0; i < paramsName.length; i++){
            toReturn.append('\t').append(paramsName[i]).append('=').append(paramsValue[i]).append(";\n");
        }
        return toReturn.toString() + '}';
    }
}