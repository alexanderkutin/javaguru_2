package com.javaguru.shoppinglist.userinterface;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final List<Action> actions;

    @Autowired
    public ConsoleUI(List<Action> actions) {
        this.actions = actions;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int response = 0;

        while (response >= 0) {
            printMenu();
            try {
                response = Integer.parseInt(scanner.nextLine());
                actions.get(response).execute();
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i));
        }
    }
}
