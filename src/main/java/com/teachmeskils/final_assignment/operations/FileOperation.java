package com.teachmeskils.final_assignment.operations;

import com.teachmeskils.final_assignment.constants.Constants;
import com.teachmeskils.final_assignment.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class FileOperation {

    public static void copyFile(Path sourcePath, Path destPath) {
        try {
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Logger.logException(e);
        }
    }

    public static Map<String, List<File>> fileOperation(){
        Logger.logInfo("Работа с папкой начата");
        //File directory = gettingFolderPath();
        File directory = new File(Constants.PATH_DOCUMENTS);
        Logger.logInfo("Получение папки в работу");

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Указанная директория не существует или это не папка.");
        }
        Logger.logInfo("Получение подпапки в работу");

        Map<String, List<File>> map = new HashMap<>();
        List<File> checkList = new ArrayList<>();
        List<File> invoiceList = new ArrayList<>();
        List<File> orderList = new ArrayList<>();
        File[] subDirectories = directory.listFiles();
        Logger.logInfo("Получение файлов в работу");
        Logger.logInfo("Работа с файлами начата");
        if (subDirectories != null) {
            for (File subDirectory : subDirectories) {
                if (subDirectory.isDirectory()) {
                    File[] files = subDirectory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt") && file.getName().toLowerCase().contains("bill")) {
                                    checkList.add(file);
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.VALID_CHECK_PATH + File.separator + file.getName()));
                            } else if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt") && file.getName().toLowerCase().contains("invoice")) {
                                    invoiceList.add(file);
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.VALID_INVOICE_PATH + File.separator + file.getName()));
                            } else if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt") && file.getName().toLowerCase().contains("order")) {
                                    orderList.add(file);
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.VALID_ORDERS_PATH + File.separator + file.getName()));
                            }else {
                                if (subDirectory.getName().toLowerCase().contains("checks")) {
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.INVALID_CHECK_PATH + File.separator + file.getName()));
                                }else if (subDirectory.getName().toLowerCase().contains("invoices")) {
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.INVALID_INVOICE_PATH + File.separator + file.getName()));
                                }else if (subDirectory.getName().toLowerCase().contains("orders")) {
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.INVALID_ORDERS_PATH + File.separator + file.getName()));
                                }
                            }
                        }
                    }else {
                        System.out.println("В директории нет файлов.");
                    }
                }
            }
        }
        Logger.logInfo("Валидные файлы скопированы в папку valid_data");
        Logger.logInfo("Невалидные файлы скопированы в папку invalid_data");
        Logger.logInfo("Работа с попдпапкой закончена");
        Logger.logInfo("Работа с папкой закончена");
        map.put("checks", checkList);
        map.put("invoices", invoiceList);
        map.put("orders", orderList);
        Logger.logInfo("Валидные файлы занесены в map");
        Logger.logInfo("Работа с файлами закончена");
        Logger.logInfo("________________________________");
        return  map;
    }
}






