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
        Date currentTime = new Date();
        if (currentTime.getTime() > expDate.getTime()) {
            return false;
        } else {
            return true;
        }
    }

    private void setAccessToken() {
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

        this.accessToken = new Random().ints(16, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void setExpDate() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        String expDate = resourceBundle.getString("auth.expDate");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, Integer.valueOf(expDate));

        this.expDate = calendar.getTime();
    }
}
