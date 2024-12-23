package com.teachmeskills.final_assignment;


import com.teachmeskills.final_assignment.authentication.Authentication;
import com.teachmeskills.final_assignment.execption.DataToCalculateTotalTurnoverNotFoundException;
import com.teachmeskills.final_assignment.execption.VerificationFailedException;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.operations.FileOperation;
import com.teachmeskills.final_assignment.service.CalculationTotalTurnoverService;
import com.teachmeskills.final_assignment.session.ApplicationSession;
import com.teachmeskills.final_assignment.user_action.UserAction;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApplicationRunner {

    public static void main(String[] args) {

        Authentication authentication = new Authentication();
        try {
            ApplicationSession session = authentication.auth();
            if (session.isActive()) {
                String filePath = UserAction.getPathToData();
                Map<String, List<File>> documents = FileOperation.fileOperation(filePath);
                CalculationTotalTurnoverService.reportCalculationTotalTurnover(documents);
               /* File report = new File(Constants.PATH_REPORT);
                if (report.exists()) {
                    AWSService.pushDataToAWS(report);
                }*/
                Logger.logInfo("Application has worked successfully.");
                System.out.println("Application has worked successfully.");
            } else {
                System.out.println("Session has been expired.");
                Logger.logInfo("Session has been expired.");
            }

        } catch (VerificationFailedException e) {
            System.out.println("Authentication has been failed.");
        }  catch (IOException e) {
            System.out.println("Application has failed.");
            Logger.logException(e);
        } catch (DataToCalculateTotalTurnoverNotFoundException e) {
            System.out.println("Application has failed.");
        } catch (Exception e) {
            System.out.println("Application has failed.");
            Logger.logException(e);
        }
    }
}
