package com.teachmeskils.final_assignment.execption;

public class TotalLineNotFoundException extends Exception {

    String codeException;

    public TotalLineNotFoundException(String codeException) {
        this.codeException = codeException;
    }

    public TotalLineNotFoundException(String message, String codeException) {
        super(message);
        this.codeException = codeException;
    }
}
