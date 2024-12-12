package com.teachmeskills.final_assignment.execption;

public class VerificationFailedException  extends Exception{
    String codeException;

    public VerificationFailedException(String codeException) {
        this.codeException = codeException;
    }

    public VerificationFailedException(String message, String codeException) {
        super(message);
        this.codeException = codeException;
    }
}
