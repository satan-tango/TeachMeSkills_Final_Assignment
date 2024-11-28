package com.teachmeskils.final_assignment.execption;

public class ContainsNotAllowedSymbolsException extends Exception {

    String codeException;

    public ContainsNotAllowedSymbolsException(String codeException) {
        this.codeException = codeException;
    }

    public ContainsNotAllowedSymbolsException(String message, String codeException) {
        super(message);
        this.codeException = codeException;
    }
}
