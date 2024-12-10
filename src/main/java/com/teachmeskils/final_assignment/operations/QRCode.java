package com.teachmeskils.final_assignment.operations;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Scanner;

public class QRCode {

    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }

    public static String getGoogleAuthenticatorBarCode(String secretKey, String login, String password) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode(password + ":" + login, "UTF-8").replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
                    + "&issuer=" + URLEncoder.encode(password, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void createQRCode(String barCodeData, String filePath, int height, int width)
            throws WriterException, IOException {
        BitMatrix matrix = new QRCodeWriter().encode(barCodeData, com.google.zxing.BarcodeFormat.QR_CODE, width, height);// Corrected ny GPT
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            MatrixToImageWriter.writeToStream(matrix, "png", out);
        }
    }

    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

    public String check2FA(String secretKey){
        System.out.print("Please enter 2fA code here -> ");

        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();

        if (code.equals(QRCode.getTOTPCode(secretKey))) {
            return "Logged in successfully";
        } else {
            return "Invalid 2FA Code";
        }
    }
}