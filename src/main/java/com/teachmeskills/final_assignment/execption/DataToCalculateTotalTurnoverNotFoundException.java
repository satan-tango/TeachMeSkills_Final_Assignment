package com.teachmeskills.final_assignment.execption;

public class DataToCalculateTotalTurnoverNotFoundException extends Exception {

    public String codeException;

    public DataToCalculateTotalTurnoverNotFoundException(String codeException) {
        this.codeException = codeException;
    }

    public DataToCalculateTotalTurnoverNotFoundException(String message, String codeException) {
        super(message);
        this.codeException = codeException;
    }
}
