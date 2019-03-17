package com.javaguru.shoppinglist.userinterface;

import java.util.Scanner;

public interface Action {

    void execute();

    default String readFromConsole(String value){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + value + ":");
        return scanner.nextLine();
    }

    default String setZeroIfEmpty(String input) {
        if (input.isEmpty())
            return "0";
        return input;
    }
}
