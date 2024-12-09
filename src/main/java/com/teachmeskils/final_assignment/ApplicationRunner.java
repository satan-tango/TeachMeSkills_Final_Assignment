package com.teachmeskils.final_assignment;

import com.teachmeskils.final_assignment.authentication.AuthenticationAndRegistration;
import com.teachmeskils.final_assignment.execption.NumberNotFoundException;
import com.teachmeskils.final_assignment.execption.TotalLineNotFoundException;

import java.io.IOException;

public class ApplicationRunner {

    public static void main(String[] args) throws NumberNotFoundException, TotalLineNotFoundException, IOException {
//        Map<String, List<File>> documents = FileOperation.convertListOfFilesToMap();
//        CalculationTotalTurnover.reportCalculationTotalTurnover(documents);
        AuthenticationAndRegistration authentication = new AuthenticationAndRegistration();
        authentication.regLoginAndPassword();
        authentication.auth();
    }
}
