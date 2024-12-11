package com.teachmeskills.final_assignment.logger;

import com.teachmeskills.final_assignment.constants.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void logInfo(String infoMessage) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateToLog = sdf.format(date);
            String message = "[INFO] -> " + dateToLog + " -> " + infoMessage + "\n";
            Files.write(Paths.get(Constants.PATH_LOG), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //do nothing
        }
    }
    public static void logException(Exception exception) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateToLog = sdf.format(date);
            StringBuilder sb = new StringBuilder();
            sb.append("[ERROR] -> " + dateToLog + " - > " + exception.getMessage());
            sb.append("\n");

            StackTraceElement[] traceElements = exception.getStackTrace();
            for (StackTraceElement element : traceElements) {
                sb.append("      " + element);
                sb.append("\n");
            }

            Files.write(Paths.get(Constants.PATH_LOG), sb.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //do nothing
        }
    }
}
