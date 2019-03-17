package com.javaguru.shoppinglist.userinterface;


import java.util.List;
import java.util.Scanner;

public class SubConsoleUI {
    private final List<Action> actions;

    public SubConsoleUI(List<Action> actions) {
        this.actions = actions;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int response = 0;

        while (response >= 0) {
            printMenu();
            try {
                response = Integer.parseInt(scanner.nextLine());
                if(response == 0)
                    break;
                actions.get(response - 1).execute();
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("0. ..");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println((i+1) + ". " + actions.get(i));
        }
    }
}
