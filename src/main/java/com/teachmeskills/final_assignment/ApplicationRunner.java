package com.teachmeskills.final_assignment;

import com.teachmeskills.final_assignment.authentication.AuthenticationAndRegistration;
import com.teachmeskills.final_assignment.execption.DataToCalculateTotalTurnoverNotFoundException;
import com.teachmeskills.final_assignment.execption.NumberNotFoundException;
import com.teachmeskills.final_assignment.execption.TotalLineNotFoundException;
import com.teachmeskills.final_assignment.operations.FileOperation;
import com.teachmeskills.final_assignment.service.CalculationTotalTurnover;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApplicationRunner {

    public static void main(String[] args) throws NumberNotFoundException, TotalLineNotFoundException, IOException, DataToCalculateTotalTurnoverNotFoundException {

   /*     AuthenticationAndRegistration authenticationAndRegistration = new AuthenticationAndRegistration();
        authenticationAndRegistration.regLoginAndPassword();

        authenticationAndRegistration.auth();*/
        Map<String, List<File>> documents = FileOperation.fileOperation();
        CalculationTotalTurnover.reportCalculationTotalTurnover(documents);
    }
}
