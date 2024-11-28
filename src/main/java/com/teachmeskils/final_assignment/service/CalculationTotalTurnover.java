package com.teachmeskils.final_assignment.service;

import com.teachmeskils.final_assignment.execption.NotFoundNumberException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculationTotalTurnover {

    public void executeCalculationTotalTurnover(Map<String, File[]> data) {

    }

    private int calculateDocumentsTotalTurnover(File[] documents) {
        List<String> lines = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < documents.length; i++) {
            try {
                lines = Files.readAllLines(Paths.get(documents[i].getPath()));

                for (int j = lines.size() - 1; j < 0; j--) {
                    if (lines.get(j).toLowerCase().contains("total")) {

                    }
                }
            } catch (IOException e) {
                //TODO если файла не найден
            }
        }

        return 1;
    }

    public static double extractTotalFromLine(String line, File file) throws NotFoundNumberException {
        String item;
        Pattern pattern = Pattern.compile("\\d.*\\d");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            item = matcher.group();

            if (item.contains(".") && item.contains(",")) {
                item = item.replaceAll("\\,", "");
            }

            if (item.contains(",")) {
                item = item.replace(',', '.');
            }

            try {
                double value = Double.valueOf(item);
                return value;
            } catch (NumberFormatException e) {
                //TODO Не удалось конвертировать число в double. Скопировать в отдельную папку
                throw new RuntimeException();

            }

        } else {
            //TODO  Не удалось найти число. Скопировать файл в отдельную папку
            throw new NotFoundNumberException("Number was not found", "700n");
        }
    }
}
