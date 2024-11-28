package com.teachmeskils.final_assignment;

import com.teachmeskils.final_assignment.execption.ContainsNotAllowedSymbolsException;
import com.teachmeskils.final_assignment.execption.NumberNotFoundException;
import com.teachmeskils.final_assignment.service.CalculationTotalTurnover;

public class ApplicationRunner {

    public static void main(String[] args) throws NumberNotFoundException, ContainsNotAllowedSymbolsException {
        System.out.println(CalculationTotalTurnover.extractTotalFromLine("Bill total amount EURO 299.01"));
    }
}
