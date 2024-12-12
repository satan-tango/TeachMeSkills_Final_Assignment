package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.execption.DataToCalculateTotalTurnoverNotFoundException;
import com.teachmeskills.final_assignment.execption.NumberNotFoundException;
import com.teachmeskills.final_assignment.execption.TotalLineNotFoundException;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.constants.Constants;
import com.teachmeskills.final_assignment.operations.FileOperation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This service provides the ability to calculate the total in documents.
 * To work, you need to transfer a map,the key of which will be the type of documents,
 * and the value of the documents themselves.
 * The result is saved in a separate report file.
 * Invalid files are moved to a separate folder.
 */
public class CalculationTotalTurnoverService {

    public static void reportCalculationTotalTurnover(Map<String, List<File>> data)
            throws IOException, DataToCalculateTotalTurnoverNotFoundException {
        if (data == null || data.isEmpty()) {
            Logger.logException(
                    new DataToCalculateTotalTurnoverNotFoundException("There is no data to calculate total turnover", "743d"));
            throw new DataToCalculateTotalTurnoverNotFoundException("There is no data to calculate total turnover", "743d");
        }

        double totalTurnover;
        Path path = Paths.get(Constants.PATH_REPORT);
        Files.deleteIfExists(path);

        for (Map.Entry<String, List<File>> document : data.entrySet()) {
            Logger.logInfo("Files type '" + document.getKey() + "' have been taken into processing");
            totalTurnover = calculateDocumentsTotalTurnover(document.getValue());
            writeTotalTurnover(document.getKey(), totalTurnover, document.getValue().size());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.now();
        Files.write(
                path,
                ("\n\nDate -> " + date.format(formatter)).getBytes(),
                StandardOpenOption.APPEND);

    }

    private static void writeTotalTurnover(String documentType, double total, int documentAmount) throws IOException {
        Path path = Paths.get(Constants.PATH_REPORT);
        if (!path.toFile().exists()) {
            Files.createFile(path);
            Logger.logInfo("Report file has been created");
            Files.write(
                    path,
                    ("=====================================ФИНАСОВАЯ СТАТИСТИКА=============================================\n\n").getBytes(),
                    StandardOpenOption.APPEND);
            Files.write(
                    path,
                    String.format("%-40s |%-40s |%-40s\n", "Тип", "Общая сумма", "Количество файлов").getBytes(),
                    StandardOpenOption.APPEND);
            Files.write(
                    path,
                    ("------------------------------------------------------------------------------------------------------\n").getBytes(),
                    StandardOpenOption.APPEND);
        }
        String str = String.format("%-40s |%-40s |%-40s\n", documentType, String.format("%.3f", total), documentAmount);
        Files.write(
                path,
                str.getBytes(),
                StandardOpenOption.APPEND);
        Files.write(
                path,
                ("------------------------------------------------------------------------------------------------------\n").getBytes(),
                StandardOpenOption.APPEND);
        Logger.logInfo("The total -> " + String.format("%.3f", total) + " of the files with type '"
                + documentType + "' has been written into report file");

    }

    private static double calculateDocumentsTotalTurnover(List<File> documents) throws IOException {
        List<String> lines;
        double total = 0;
        boolean isFoundedTotalLine;

        for (int i = 0; i < documents.size(); i++) {
            Logger.logInfo("File with name '" + documents.get(i).getName() + "' has been taken into processing.");
            File unsupportedDir = new File(Constants.UNSUPPORTED_FILE_PATH);
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
                    Logger.logException(new TotalLineNotFoundException("Total line was not found in file with name '"
                            + documents.get(i).getName() + "'", "733t"));

                    if (!unsupportedDir.exists()) {
                        Files.createDirectory(Paths.get(unsupportedDir.getAbsolutePath()));
                    }
                    FileOperation.copyFile(Paths.get(documents.get(i).getAbsolutePath()), Paths.get(Constants.UNSUPPORTED_FILE_PATH + File.separator + documents.get(i).getName()));
                    Logger.logInfo("File with name '" + documents.get(i).getName() + "' has been moved into unsupported folder.");
                }
                Logger.logInfo("File with name '" + documents.get(i).getName() + "' has been successfully processed.");
            } catch (NumberNotFoundException | NumberFormatException e) {
                if (!unsupportedDir.exists()) {
                    Files.createDirectory(Paths.get(unsupportedDir.getAbsolutePath()));
                }
                FileOperation.copyFile(Paths.get(documents.get(i).getAbsolutePath()), Paths.get(Constants.UNSUPPORTED_FILE_PATH + File.separator + documents.get(i).getName()));
                Logger.logInfo("File with name '" + documents.get(i).getName() + "' has been moved into unsupported folder.");
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
                item = item.replaceAll(",", "");
            }

            if (item.contains(",")) {
                item = item.replace(',', '.');
            }

            try {
                double value = Double.parseDouble(item);
                Logger.logInfo("Total has been successfully converted into double.");
                return value;
            } catch (NumberFormatException e) {
                Logger.logException(new NumberFormatException());

                throw new NumberNotFoundException(e.getMessage());
            }

        } else {
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
