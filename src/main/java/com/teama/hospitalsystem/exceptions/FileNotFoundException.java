package com.teama.hospitalsystem.exceptions;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(){
        super();
    }
    public FileNotFoundException(String fileName, String filePath){
        super(formMessage(fileName, filePath));
    }
    private static String formMessage(String fileName, String filePath){
        return fileName + "was not founded by path: " + filePath;
    }
}
