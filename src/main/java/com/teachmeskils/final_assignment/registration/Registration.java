package com.teachmeskils.final_assignment.registration;

import java.util.Scanner;

public class Registration {
    Scanner scanner = new Scanner(System.in);

    private final String loginForRegistration = scanner.nextLine();

    String passwordForRegistration;

    public String registration() {
        passwordForRegistration = scanner.nextLine();

        if (passwordForRegistration.length() >= 8) {
            return passwordForRegistration;
        }

        return "The password does not meet the requirements.";
    }

    public String getLoginForRegistration() {
        return loginForRegistration;
    }
}
