package com.teachmeskills.final_assignment.operations;

import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.constants.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class FileOperation {

/**
 * Performs file operations on documents in the specified directory.
 * This method scans the given directory and its subdirectories for files matching certain criteria.
 * It categorizes the files into three groups: checks, invoices, and orders.
 * Valid files are copied to designated validation paths, while invalid files are moved to corresponding invalid paths.
 * Valid files are listed in Lists appropriately named checks, invoices, and orders.
 * Lists of checks, invoices, and orders are entered into the Map.
 * @return A Map<String, List<File>> containing categorized file lists
 */
    public static Map<String, List<File>> fileOperation(){
        //File directory = gettingFolderPath();
        File directory = new File(Constants.PATH_DOCUMENTS);
        Logger.logInfo("Getting folders " + directory.getName() + " at work");

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist or is not a folder.");
        }
        Map<String, List<File>> map = new HashMap<>();
        List<File> checkList = new ArrayList<>();
        List<File> invoiceList = new ArrayList<>();
        List<File> orderList = new ArrayList<>();
        File[] subDirectories = directory.listFiles();
        if (subDirectories != null) {
            for (File subDirectory : subDirectories) {
                Logger.logInfo("Getting a subfolder " + subDirectory.getName() + " at work");
                if (subDirectory.isDirectory()) {
                    File[] files = subDirectory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt") && file.getName().toLowerCase().contains("bill")) {
                                Logger.logInfo("Working with a file " + file.getName());
                                checkList.add(file);
                                copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.VALID_CHECK_PATH + File.separator + file.getName()));
                            } else if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt") && file.getName().toLowerCase().contains("invoice")) {
                                Logger.logInfo("Working with a file " + file.getName());
                                invoiceList.add(file);
                                copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.VALID_INVOICE_PATH + File.separator + file.getName()));
                            } else if (file.isFile() && file.getName().contains("2024") && file.getName().endsWith(".txt") && file.getName().toLowerCase().contains("order")) {
                                Logger.logInfo("Working with a file " + file.getName());
                                orderList.add(file);
                                copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.VALID_ORDERS_PATH + File.separator + file.getName()));
                            }else {
                                if (subDirectory.getName().toLowerCase().contains("checks")) {
                                    Logger.logInfo("Working with a file " + file.getName());
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.INVALID_CHECK_PATH + File.separator + file.getName()));
                                }else if (subDirectory.getName().toLowerCase().contains("invoices")) {
                                    Logger.logInfo("Working with a file " + file.getName());
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.INVALID_INVOICE_PATH + File.separator + file.getName()));
                                }else if (subDirectory.getName().toLowerCase().contains("orders")) {
                                    Logger.logInfo("Working with a file " + file.getName());
                                    copyFile(Paths.get(file.getAbsolutePath()), Paths.get(Constants.INVALID_ORDERS_PATH + File.separator + file.getName()));
                                }
                            }
                        }
                    }else {
                        System.out.println("There are no files in the directory.");
                    }
                }
            }
        }
        map.put("checks", checkList);
        map.put("invoices", invoiceList);
        map.put("orders", orderList);
        Logger.logInfo("Valid files are included in map");
        Logger.logInfo("________________________________");
        return  map;
    }

    public static void copyFile(Path sourcePath, Path destPath) {
        try {
            Logger.logInfo("Copied " + sourcePath + " to " + destPath);
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Logger.logException(e);
        }
    }
}






