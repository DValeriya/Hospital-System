package com.teama.hospitalsystem.exceptions;

import javax.mail.Address;
import java.util.Arrays;

public class MessageNotSendException extends RuntimeException {
    public MessageNotSendException(){
        super();
    }
    public MessageNotSendException(String message){
        super(formMessage(message));
    }
    public MessageNotSendException(Address[] address){
        super(formMessage(address));
    }
    private static String formMessage(Address[] address){
        StringBuilder toReturn = new StringBuilder("Cant send message by this addresses: ");
        for (Address i: address
             ) {
            toReturn.append(i.toString());
        }
        return toReturn.toString();
    }
    private static String formMessage(String message){
        return "Message wasn't sent:" + message;
    }
}
