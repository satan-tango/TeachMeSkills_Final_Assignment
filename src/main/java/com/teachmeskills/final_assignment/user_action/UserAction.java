package com.teachmeskills.final_assignment.user_action;

import java.util.Scanner;

public class UserAction {

    public static String getPathToData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter path to data -> ");

        return scanner.next();
    }
}
