package com.teachmeskils.final_assignment.operations;

import com.teachmeskils.final_assignment.constants.Constants;

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
            System.err.println("Ошибка при перемещении файла: " + e.getMessage());
        }
    }

    public static Map<String, List<File>> convertListOfFilesToMap(){
        //File directory = gettingFolderPath();
        File directory = new File(Constants.PATH_DOCUMENTS);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Указанная директория не существует или это не папка.");
        }

        Map<String, List<File>> map = new HashMap<>();
        List<File> checkList = new ArrayList<>();
        List<File> invoiceList = new ArrayList<>();
        List<File> orderList = new ArrayList<>();
        File[] subDirectories = directory.listFiles();
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
                    } else {
                        System.out.println("В директории нет файлов.");
                    }
                }
            }
        }
        map.put("checks", checkList);
        map.put("invoices", invoiceList);
        map.put("orders", orderList);
        return  map;
    }
}






