package com.teachmeskills.final_assignment.authentication;

import com.teachmeskills.final_assignment.constants.Constants;
import com.teachmeskills.final_assignment.execption.VerificationFailedException;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.operations.Encryption;
import com.teachmeskills.final_assignment.operations.QRCodeOperation;
import com.teachmeskills.final_assignment.session.ApplicationSession;
import com.teachmeskills.final_assignment.storage.MockStorage;

import java.util.Scanner;

/**
 * This service is an authorization.
 * To work, you need to transfer your login and password.
 * After the transfer, a QR code is generated.
 * Next, you need to use the application for two-factor authentication and enter the received data.
 * After successful authorization, a session is created.
 */

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
                        Logger.logException(new VerificationFailedException("QR code verification don't passed", "dfd"));
                        throw new VerificationFailedException("QR code verification don't passed", "482");
                    }

                } else {
                    Logger.logException(new VerificationFailedException("Data verification don't passed", "dfd"));
                    throw new VerificationFailedException("The data has not been verified.", "568");
                }
            } else {
                Logger.logException(new VerificationFailedException("Password verification failed", "dfd"));
                throw new VerificationFailedException("Password verification failed", "758");
            }
        } else {
            Logger.logException(new VerificationFailedException("Login verification failed", "dfd"));
            throw new VerificationFailedException("Login verification failed", "931");
        }
    }
}
