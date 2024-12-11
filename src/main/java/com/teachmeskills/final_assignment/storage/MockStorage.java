package com.teachmeskills.final_assignment.storage;


import java.util.Objects;

public class MockStorage {

    private final String login = "admin";
    private final String password = "Admin123";

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MockStorage that = (MockStorage) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
