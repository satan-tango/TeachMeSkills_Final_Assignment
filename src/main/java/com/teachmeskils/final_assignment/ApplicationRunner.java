package com.teachmeskils.final_assignment;

import com.teachmeskils.final_assignment.service.CalculationTotalTurnover;

public class ApplicationRunner {

    public static void main(String[] args) {
        System.out.println(CalculationTotalTurnover.extractTotalFromLine("Bill total amount EURO 299,01"));
    }
}
