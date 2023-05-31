package org.example;

import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);

    public void display() {
        displayHome();
    }

    private void displayHome() {
        //inputs will display the menu
        System.out.println("""
                Welcome to our Deli!\s
                                
                How may I help you?
                [1] Start New Order
                [2] Exit\s
                """);
        String Userinput = scanner.nextLine();

        switch (Userinput) {
            case "1" -> startOrder();
            case "2" -> {
                System.out.println("Goodbye, have a nice day!");
                System.exit(0);
            }
            default -> System.out.println();
        }
    }

    private void startOrder() {
        System.out.println("Sorry! Nothing here yet.");
    }


}