package com.teachmeskils.final_assignment.session;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class TMSApplicationSession {

    private String accessToken;

    private Date expDate;


    public TMSApplicationSession() {
        setAccessToken();
        setExpDate();
    }

    private void setAccessToken() {
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

        this.accessToken = new Random().ints(16, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void setExpDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);

        this.expDate = calendar.getTime();
    }
}
