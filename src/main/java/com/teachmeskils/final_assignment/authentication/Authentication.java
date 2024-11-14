package com.teachmeskils.final_assignment.authentication;

import com.teachmeskils.final_assignment.storage.MockStorage;

import java.util.Scanner;

public class Authentication {
    Scanner scanner = new Scanner(System.in);

    String loginForAuth = scanner.nextLine();
    String passwordForAuth = scanner.nextLine();

    MockStorage mockStorage = new MockStorage();

    public String auth() {
        if (loginForAuth.equalsIgnoreCase(mockStorage.getLogin()) & passwordForAuth.equalsIgnoreCase(mockStorage.getPassword())) {
            return "Yes"; // Тут наверное должен генерироваться токен
        } else {
            return "No";
        }
    }
}
