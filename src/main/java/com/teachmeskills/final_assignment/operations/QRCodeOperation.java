package com.teachmeskills.final_assignment.operations;

import com.google.zxing.WriterException;

import java.io.IOException;

public class QRCodeOperation {
    public static String QrAuth(){

        String secretKey = QRCode.generateSecretKey();
        String email = "teachmeskills@gmail.com";
        String companyName = "TeachMeSkills C32 course project";
        String barCodeUrl = QRCode.getGoogleAuthenticatorBarCode(secretKey, email, companyName);

        try {
            QRCode.createQRCode(barCodeUrl, "QRCode.png", 400, 400);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("QR code generation error");
        }

        try{
            QRCode.check2FA(secretKey);
            return "Success";
        } catch (Exception e) {
            throw new RuntimeException("QR code verification error");
        }
    }
}
