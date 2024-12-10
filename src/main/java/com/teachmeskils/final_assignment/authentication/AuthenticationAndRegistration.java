package com.teachmeskils.final_assignment.authentication;

import com.google.zxing.WriterException;
import com.teachmeskils.final_assignment.operations.Encryption;
import com.teachmeskils.final_assignment.operations.QRCode;
import com.teachmeskils.final_assignment.session.ApplicationSession;
import com.teachmeskils.final_assignment.storage.MockStorage;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class AuthenticationAndRegistration {
    Scanner scanner = new Scanner(System.in);

    private String login;
    private String password;
    private String confirmPassword;

    MockStorage mockStorage = new MockStorage();

    ApplicationSession applicationSession = new ApplicationSession();

    QRCode qrCode = new QRCode();

    private final String secretCode;

    public AuthenticationAndRegistration() {
        this.secretCode = QRCode.generateSecretKey();
    }

    public String regForLogin() {

        System.out.println("Enter login for registration: ");

        login = scanner.nextLine();

        return login;
    }

    public String regForPassword() {

        System.out.println("Enter password for registration: ");

        password = scanner.nextLine();

        System.out.println("Confirm password : ");

        confirmPassword = scanner.nextLine();

        if (password.equals(confirmPassword)) {
            String barCodeForQR = QRCode.getGoogleAuthenticatorBarCode(secretCode, login, password);

            try {
                QRCode.createQRCode(barCodeForQR, "QRCode.png", 400, 400);
                System.out.println("Registration success.");
                return password;

            } catch (WriterException | IOException e) {
                throw new RuntimeException("QR code error.");
            }

        } else {
            throw new RuntimeException("Passwords don't match.");
        }
    }

    public void auth() {

        System.out.println("Enter login: ");
        String authLogin = scanner.nextLine();

        System.out.println("Enter password: ");
        String authPassword = scanner.nextLine();

        if (mockStorage.getListOfLogins() != null && mockStorage.getListOfPasswords() != null) {
            for (int i = 0; i < mockStorage.getListOfLogins().size(); i++) {
                for (int j = 0; j < mockStorage.getListOfPasswords().size(); j++) {

                    String loginFromMock = mockStorage.getListOfLogins().get(i);
                    String passwordFromMock = mockStorage.getListOfPasswords().get(j);

                    if (authLogin.equals(Encryption.decrypt(loginFromMock)) && authPassword.equals(Encryption.decrypt(passwordFromMock))) {
//                        String resultOfQRCheck = qrCode.check2FA(secretCode);
//
//                        if (resultOfQRCheck.equals("Logged in successfully")) {
//                            applicationSession.session();
//
//                            System.out.println("Auth is successful.");
//
//                        } else {
//                            throw new RuntimeException("Auth failed (Exception in QR validate.");
//                        }
                        System.out.println("Success.");
                    } else {
                        throw new RuntimeException("Auth failed.");
                    }
                }
            }

        } else {
            throw new RuntimeException("Storage is empty.");
        }
    }

    public void regLoginAndPassword() {

        mockStorage.recordToBaseLogin();
        mockStorage.recordToBasePassword();

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationAndRegistration that = (AuthenticationAndRegistration) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(confirmPassword, that.confirmPassword) && Objects.equals(secretCode, that.secretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, confirmPassword, secretCode);
    }
}
