package com.teachmeskills.final_assignment.execption;

public class NumberNotFoundException extends Exception {

    public String codeException;

    public NumberNotFoundException(String codeException) {
        this.codeException = codeException;
    }

    public NumberNotFoundException(String message, String codeException) {
        super(message);
        this.codeException = codeException;
    }
}
