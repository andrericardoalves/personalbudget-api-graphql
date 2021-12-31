package com.alves.personalbudget.exception;

public class AttributeMandatoryException extends RuntimeException{
    public AttributeMandatoryException(String message) {
        super(message);
    }
}
