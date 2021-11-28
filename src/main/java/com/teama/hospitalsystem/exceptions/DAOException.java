package com.teama.hospitalsystem.exceptions;

import org.springframework.dao.DataAccessException;

public class DAOException extends DataAccessException{

    public DAOException(String msg) {
        super(msg);
    }
}
