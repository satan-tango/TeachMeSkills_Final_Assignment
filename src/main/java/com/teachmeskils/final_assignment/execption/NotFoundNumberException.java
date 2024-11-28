package com.teachmeskils.final_assignment.execption;

public class NotFoundNumberException extends Exception {

    public String codeException;

    public NotFoundNumberException(String codeException) {
        this.codeException = codeException;
    }

    public NotFoundNumberException(String message, String codeException) {
        super(message);
        this.codeException = codeException;
    }
}
