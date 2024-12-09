package com.teachmeskils.final_assignment.service;

import com.teachmeskils.final_assignment.constants.Constants;
import com.teachmeskils.final_assignment.execption.NumberNotFoundException;
import com.teachmeskils.final_assignment.execption.TotalLineNotFoundException;
import com.teachmeskils.final_assignment.logger.Logger;

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

        for (Map.Entry<String, List<File>> document : data.entrySet()) {
            Logger.logInfo("Files type '" + document.getKey() + "' have been taken into processing");
            totalTurnover = calculateDocumentsTotalTurnover(document.getValue());
            writeTotalTurnover(document.getKey(), totalTurnover);
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
            Logger.logInfo("Report file has been created");
            Files.write(
                    path,
                    ("=========================================ФИНАСОВАЯ СТАТИСТИКА=========================================\n").getBytes(),
                    StandardOpenOption.APPEND);
            Files.write(
                    path,
                    ("Тип                                 | Общая сумма                                 | Количество файлов\n").getBytes(),
                    StandardOpenOption.APPEND);
            Files.write(
                    path,
                    ("------------------------------------------------------------------------------------------------------\n").getBytes(),
                    StandardOpenOption.APPEND);
        }
        Files.write(
                path,
                (documentType.toLowerCase() + " total -> " + String.format("%.3f", total) + "\n").getBytes(),
                StandardOpenOption.APPEND);
        Logger.logInfo("The total -> " + String.format("%.3f", total) + " of the files with type '"
                + documentType + "' has been written into report file");

    }

    private static double calculateDocumentsTotalTurnover(List<File> documents) throws
            NumberNotFoundException, TotalLineNotFoundException {
        List<String> lines;
        double total = 0;
        boolean isFoundedTotalLine;
        System.out.println(documents.size());

        for (int i = 0; i < documents.size(); i++) {
            Logger.logInfo("File with name '" + documents.get(i).getName() + "' has been taken into processing.");
            try {
                lines = Files.readAllLines(Paths.get(documents.get(i).getPath()));
                isFoundedTotalLine = false;

                for (int j = lines.size() - 1; j >= 0; j--) {
                    if (lines.get(j).toLowerCase().contains("total")) {
                        total += extractTotalFromLine(lines.get(j));
                        isFoundedTotalLine = true;
                        break;
                    }
                }

                if (!isFoundedTotalLine) {
                    //TODO Перенести файл в невалидные
                    Logger.logException(new TotalLineNotFoundException("Total line was not found", "733t"));
                    throw new TotalLineNotFoundException("Total line was not found", "733t");
                }
                Logger.logInfo("File with name '" + documents.get(i).getName() + "' has been successfully processed.");
            } catch (IOException e) {
                Logger.logException(e);
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

            if (isContainedNotAllowedSymbols(item)) {
                Logger.logException(new NumberNotFoundException("Number was not found, contains not allowed symbols", "720n"));
                throw new NumberNotFoundException("Number was not found, contains not allowed symbols", "720n");
            }

            if (item.contains(".") && item.contains(",")) {
                item = item.replaceAll("\\,", "");
            }

            if (item.contains(",")) {
                item = item.replace(',', '.');
            }

            try {
                double value = Double.valueOf(item);
                Logger.logInfo("Total has been successfully converted into double.");
                return value;
            } catch (NumberFormatException e) {
                //TODO Не удалось конвертировать число в double. Скопировать в отдельную папку
                Logger.logException(new NumberFormatException());
                throw new NumberFormatException();
            }

        } else {
            //TODO  Не удалось найти число. Скопировать файл в отдельную папку
            Logger.logException(new NumberNotFoundException("Number was not found", "700n"));
            throw new NumberNotFoundException("Number was not found", "700n");
        }
    }

    private static boolean isContainedNotAllowedSymbols(String item) {
        char[] arr = item.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (!Character.isDigit(arr[i]) && arr[i] != '.' && arr[i] != ',') {
                return true;
            }
        }

        return false;
    }

}
