package com.teachmeskills.final_assignment.session;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ApplicationSession {

    private String accessToken;

    private Date expDate;


    public void session() {
        setAccessToken();
        setExpDate();
    }

    public boolean isActive() {
        if (new Date().getTime() > expDate.getTime()) {
            return false;
        } else {
            return true;
        }
    }

    private void setAccessToken() {
        accessToken = "abcdefghijklmnopqrstuvwxyz0123456789";

        this.accessToken = new Random().ints(16, 0, accessToken.length())
                .mapToObj(accessToken::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void setExpDate() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        String expDate = resourceBundle.getString("auth.expDate");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, Integer.parseInt(expDate));

        this.expDate = calendar.getTime();
    }
}
