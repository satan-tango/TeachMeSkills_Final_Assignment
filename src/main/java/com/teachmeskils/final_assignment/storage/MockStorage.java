package com.teachmeskils.final_assignment.storage;

import com.teachmeskils.final_assignment.authentication.AuthenticationAndRegistration;
import com.teachmeskils.final_assignment.operations.Encryption;

import java.util.ArrayList;
import java.util.List;

public class MockStorage {

    static AuthenticationAndRegistration authentication = new AuthenticationAndRegistration();

    List<String> listOfLogins = new ArrayList<>();
    List<String> listOfPasswords = new ArrayList<>();

    public void recordToBaseLogin(){
        listOfLogins.add(Encryption.encrypt(authentication.regForLogin()));

    }

    public void recordToBasePassword(){
        listOfPasswords.add(Encryption.encrypt(authentication.regForPassword()));

    }

    public List<String> getListOfLogins() {
        return listOfLogins;
    }

    public List<String> getListOfPasswords() {
        return listOfPasswords;
    }
}
