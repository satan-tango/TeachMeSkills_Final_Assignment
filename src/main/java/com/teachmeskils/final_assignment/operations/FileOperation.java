package com.teachmeskils.final_assignment.operations;

import com.teachmeskils.final_assignment.constants.Constants;

import java.io.*;
import java.util.*;

public class FileOperation {

    public static File gettingFolderPath() {
        try (Scanner scanner = new Scanner(System.in)) {
            String folderPath = scanner.nextLine();
            return new File(folderPath);
        }
    }

    public static Map<String, List<File>> convertListOfFilesToMap() {
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
                            if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt")) {
                                if (file.getName().toLowerCase().contains("bill")) {
                                    checkList.add(file);
                                } else if (file.getName().toLowerCase().contains("invoice")) {
                                    invoiceList.add(file);
                                } else if (file.getName().toLowerCase().contains("order")) {
                                    orderList.add(file);
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
//    public static List<File> convertListOfFilesToMap12() {
//        File directory = gettingFolderPath();
//        if (!directory.exists() || !directory.isDirectory()) {
//            System.out.println("Указанная директория не существует или это не папка.");
//        }
//        List<File> list = new ArrayList<>();
//        try {
//            File[] subDirectories = directory.listFiles();
//            if (subDirectories != null) {
//                for (File subDirectory : subDirectories) {
//                    if (subDirectory.isDirectory()) {
//                        File[] files = subDirectory.listFiles();
//                        if (files != null) {
//                            for (File file : files) {
//                                if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt")) {
//                                    if (file.getName().toLowerCase().contains("checks")) {
//                                        list.add(file);
//                                    } else if (file.getName().toLowerCase().contains("invoices")) {
//                                        list.add(file);
//                                    } else if (file.getName().toLowerCase().contains("orders")) {
//                                        list.add(file);
//                                    }
//                                }
//                            }
//                        } else {
//                            System.out.println("В директории нет файлов.");
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Произошла ошибка при чтении файлов: " + e.getMessage());
//        }
//        return  list;
//    }
//    public static Map<String, List<File>> convertListOfFilesToMap1() {
//        List<File> list = convertListOfFilesToMap12();
//        Map<String, List<File>> map = new HashMap<>();
//        List<File> checkList = new ArrayList<>();
//        List<File> invoiceList = new ArrayList<>();
//        List<File> orderList = new ArrayList<>();
//        for (File file : list) {
//            if(file.getName().toLowerCase().contains("checks")) {
//                orderList.add(file);
//            } else if (file.getName().toLowerCase().contains("invoices")) {
//            list.add(file);
//            } else if (file.getName().toLowerCase().contains("orders")) {
//            list.add(file);
//            }
//        }
//        map.put("checks", checkList);
//        map.put("invoices", invoiceList);
//        map.put("orders", orderList);
//        return map;
//    }

}






