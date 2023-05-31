package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static List<String> orderEntries = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayHome();
    }

    public static void displayHome() {
        System.out.println("Welcome to our Deli/shop! How may I help you? ");
        System.out.println("\nOur Menu: ");
        System.out.println("1) New Order");
        System.out.println("2) Exit");

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                startOrder();
                break;
            case "2":
                System.out.println("Goodbye! Have a nice day!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayHome();
        }
    }

    private static void startOrder() {
        int choice;
        do {
            displayOptions();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Sandwich
                    addSandwich();
                    break;
                case 2:
                    // Add Drink
                    addDrink();
                    break;
                case 3:
                    // Add Chips
                    addChips();
                    break;
                case 4:
                    // Checkout
                    checkout();
                    break;
                case 0:
                    // Cancel Order
                    cancelOrder();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void displayOptions() {
        System.out.println("DELI-cious - Order Screen");
        System.out.println("Choose an option:");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
    }

    private static void addSandwich() {
        // Implement the logic to add a sandwich to the order
        System.out.println("Adding a sandwich to the order...");
        // You can prompt the user for sandwich details and add them to the orderEntries list
        // Example: orderEntries.add("Sandwich: [details]");
    }

    private static void addDrink() {
        // Implement the logic to add a drink to the order
        System.out.println("Adding a drink to the order...");
        // You can prompt the user for drink details and add them to the orderEntries list
        // Example: orderEntries.add("Drink: [details]");
    }

    private static void addChips() {
        // Implement the logic to add chips to the order
        System.out.println("Adding chips to the order...");
        // You can prompt the user for chip details and add them to the orderEntries list
        // Example: orderEntries.add("Chips: [details]");
    }

    private static void checkout() {
        // Implement the logic to display the order details and calculate the total price
        System.out.println("Checkout - Order Details:");
        // Display the orderEntries list with the newest entries first
        // Calculate and display the total price of the order
        // Example:
        // for (int i = orderEntries.size() - 1; i >= 0; i--) {
        //     System.out.println(orderEntries.get(i));
        // }
        // System.out.println("Total Price: $[totalPrice]");
    }

    private static void cancelOrder() {
    }
}