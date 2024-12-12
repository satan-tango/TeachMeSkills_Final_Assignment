package com.teachmeskills.final_assignment.authentication;

import com.teachmeskills.final_assignment.constants.Constants;
import com.teachmeskills.final_assignment.execption.VerificationFailedException;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.operations.Encryption;
import com.teachmeskills.final_assignment.operations.QRCodeOperation;
import com.teachmeskills.final_assignment.session.ApplicationSession;
import com.teachmeskills.final_assignment.storage.MockStorage;

import java.util.Scanner;

public class Authentication {

    Scanner scanner = new Scanner(System.in);

    MockStorage mockStorage = new MockStorage();

    public ApplicationSession auth() throws VerificationFailedException {

        System.out.print("Enter Login: ");
        String loginForAuthentication = scanner.nextLine();

        Logger.logInfo("The login was entered");

        System.out.print("Enter password: ");
        String passwordForAuthentication = scanner.nextLine();

        Logger.logInfo("The password was entered");

        if (loginForAuthentication.matches(Constants.LOGIN_VERIFICATION)) {
            Logger.logInfo("Login has been verified");

            if (passwordForAuthentication.matches(Constants.PASSWORD_VERIFICATION)) {
                Logger.logInfo("Password has been verified");

                if (loginForAuthentication.toLowerCase().equals(Encryption.decrypt(mockStorage.getLogin())) && passwordForAuthentication.equals(Encryption.decrypt(mockStorage.getPassword()))) {
                    Logger.logInfo("Data verification passed");

                    if (QRCodeOperation.QrAuth().equals("Success")) {
                        Logger.logInfo("QR code verification passed");

                        System.out.println("Authentication success.");

                        ApplicationSession applicationSession = new ApplicationSession();
                        applicationSession.session();
                        return applicationSession;
                    } else {
                        Logger.logException(new VerificationFailedException("QR code verification don't passed","dfd"));
                        Logger.logInfo("QR code verification don't passed");
                        throw new VerificationFailedException("QR code verification don't passed","dfd");
                    }

                } else {
                    Logger.logInfo("Data verification don't passed");
                    throw new RuntimeException("The data has not been verified.");
                }
            } else {
                Logger.logInfo("Password verification failed");
                throw new RuntimeException("Password verification failed");
            }
        } else {
            Logger.logInfo("Login verification failed");
            throw new RuntimeException("Login verification failed");
        }
    }
}
