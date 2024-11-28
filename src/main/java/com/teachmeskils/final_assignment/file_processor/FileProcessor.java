package com.teachmeskils.final_assignment.file_processor;


import java.io.*;
import java.util.*;

public class FileProcessor {

    public static File gettingFolderPath(){
        try (Scanner scanner = new Scanner(System.in)) {
            String folderPath = scanner.nextLine();
            return new File(folderPath);
        }
    }

    public static Map<String, List<File>> fileReader() {
            File directory = gettingFolderPath();
            if (!directory.exists() || !directory.isDirectory()) {
                System.out.println("Указанная директория не существует или это не папка.");
            }
            try {
                File[] subDirectories = directory.listFiles();
                Map<String, List<File>> map = new HashMap<>();

                for (File subDirectory : subDirectories) {
                    if (subDirectory.isDirectory()) {
                        File[] files = subDirectory.listFiles();
                        List<File> checkList = new ArrayList<>();
                        List<File> invoiceList = new ArrayList<>();
                        List<File> orderList = new ArrayList<>();
                        if (files != null) {
                            for (File file : files) {
                                if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt")) {
                                    if (subDirectory.getName().contains("checks")) {
                                        checkList.add(file);
                                        map.put(subDirectory.getName(), checkList);
//                                            Path path = Paths.get(file.getAbsolutePath());
//                                            Path destPath = Paths.get(Constant.VALID_CHECK_PATH + File.separator + file.getName());
//                                            Files.copy(path, destPath, StandardCopyOption.REPLACE_EXISTING);
                                    } else if (subDirectory.getName().contains("invoices")) {
                                        invoiceList.add(file);
                                        map.put(subDirectory.getName(), invoiceList);
//                                            Path path = Paths.get(file.getAbsolutePath());
//                                            Path destPath = Paths.get(Constant.VALID_INVOICE_PATH + File.separator + file.getName());
//                                            Files.copy(path, destPath, StandardCopyOption.REPLACE_EXISTING);
                                    } else if (subDirectory.getName().contains("orders")) {
                                        orderList.add(file);
                                        map.put(subDirectory.getName(), orderList);
//                                            Path path = Paths.get(file.getAbsolutePath());
//                                            Path destPath = Paths.get(Constant.VALID_ORDERS_PATH + File.separator + file.getName());
//                                            Files.copy(path, destPath, StandardCopyOption.REPLACE_EXISTING);
                                    }
                                }
                            }
                        } else {
                            System.out.println("В директории нет файлов.");
                        }
                    }
                }
                return map;
            } catch (Exception e) {
                System.out.println("Произошла ошибка при чтении файлов: " + e.getMessage());
            }
            return null;
    }

}






