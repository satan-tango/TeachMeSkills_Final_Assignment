package com.teachmeskills.final_assignment;

//import com.teachmeskills.final_assignment.authentication.AuthenticationAndRegistration;

import com.teachmeskills.final_assignment.constants.Constants;
import com.teachmeskills.final_assignment.execption.DataToCalculateTotalTurnoverNotFoundException;
import com.teachmeskills.final_assignment.execption.NumberNotFoundException;
import com.teachmeskills.final_assignment.execption.TotalLineNotFoundException;
import com.teachmeskills.final_assignment.operations.FileOperation;
import com.teachmeskills.final_assignment.service.AWSService;
import com.teachmeskills.final_assignment.service.CalculationTotalTurnover;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ApplicationRunner {

    public static void main(String[] args) throws NumberNotFoundException, TotalLineNotFoundException, IOException, DataToCalculateTotalTurnoverNotFoundException {

   /*     AuthenticationAndRegistration authenticationAndRegistration = new AuthenticationAndRegistration();
        authenticationAndRegistration.regLoginAndPassword();

        authenticationAndRegistration.auth();*/
        Map<String, List<File>> documents = FileOperation.fileOperation();
        CalculationTotalTurnover.reportCalculationTotalTurnover(documents);
        File report = new File(Constants.PATH_REPORT);
        if (report.exists()) {
            AWSService.pushDataToAWS(report);
        }
    }
}
