package com.teachmeskils.final_assignment.storage;

import com.teachmeskils.final_assignment.operations.EncryptDecrypt;
import com.teachmeskils.final_assignment.registration.Registration;

import java.util.Objects;

public class MockStorage {

    /*
    private String login = "admin";

    private String password = "admin";

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }*/

    Registration registration = new Registration();

    String loginForMock = registration.getLoginForRegistration().toLowerCase();
    String passwordForMock = registration.registration().toLowerCase();

    private final String login = EncryptDecrypt.encrypt(loginForMock);
    private final String password = EncryptDecrypt.encrypt(passwordForMock);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockStorage that = (MockStorage) o;
        return Objects.equals(loginForMock, that.loginForMock) && Objects.equals(passwordForMock, that.passwordForMock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginForMock, passwordForMock);
    }

    public String getLogin() {
        return EncryptDecrypt.decrypt(login);
    }

    public String getPassword() {
        return EncryptDecrypt.decrypt(password);
    }
}
