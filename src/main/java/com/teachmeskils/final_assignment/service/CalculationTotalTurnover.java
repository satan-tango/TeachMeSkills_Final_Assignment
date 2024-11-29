package com.teachmeskils.final_assignment.service;

import com.teachmeskils.final_assignment.constants.Constants;
import com.teachmeskils.final_assignment.execption.NumberNotFoundException;
import com.teachmeskils.final_assignment.execption.TotalLineNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculationTotalTurnover {

    public static void reportCalculationTotalTurnover(Map<String, List<File>> data)
            throws TotalLineNotFoundException, NumberNotFoundException, IOException {
        double totalTurnover = 0;
        Path path = Paths.get(Constants.PATH_REPORT);
        Files.deleteIfExists(path);

        for (Map.Entry<String, List<File>> documents : data.entrySet()) {
            totalTurnover = calculateDocumentsTotalTurnover(documents.getValue());
            writeTotalTurnover(documents.getKey(), totalTurnover);
        }
        Files.write(
                path,
                ("\n\n" + new Date()).getBytes(),
                StandardOpenOption.APPEND);
    }

    private static void writeTotalTurnover(String documentType, double total) throws IOException {
        Path path = Paths.get(Constants.PATH_REPORT);
        if (!path.toFile().exists()) {
            Files.createFile(path);
        }
        Files.write(
                path,
                (documentType.toUpperCase() + " total -> " + String.format("%.3f", total) + "\n").getBytes(),
                StandardOpenOption.APPEND);

    }

    private static double calculateDocumentsTotalTurnover(List<File> documents) throws
            NumberNotFoundException, TotalLineNotFoundException {
        List<String> lines;
        double total = 0;
        boolean isFoundedTotalLine;
        System.out.println(documents.size());

        for (int i = 0; i < documents.size(); i++) {
            try {
                lines = Files.readAllLines(Paths.get(documents.get(i).getPath()));
                isFoundedTotalLine = false;

                for (int j = lines.size() - 1; j >= 0; j--) {
                    if (lines.get(j).toLowerCase().contains("total")) {
                        total += total + extractTotalFromLine(lines.get(j));
                        isFoundedTotalLine = true;
                        break;
                    }
                }

                if (!isFoundedTotalLine) {
                    throw new TotalLineNotFoundException("Total line was not found", "733t");
                }
            } catch (IOException e) {
                //TODO логирование: файл не найден
            }
        }

        return total;
    }

    private static double extractTotalFromLine(String line) throws NumberNotFoundException {
        String item;
        Pattern pattern = Pattern.compile(Constants.REG_EX_NUMBERS_DOT_COMMA);
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
                throw new NumberFormatException();

            }

        } else {
            //TODO  Не удалось найти число. Скопировать файл в отдельную папку
            throw new NumberNotFoundException("Number was not found", "700n");
        }
    }

}
