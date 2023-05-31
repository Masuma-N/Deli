package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final double[] SANDWICH_SIZE = {4, 8, 12};
    private static final double[] SANDWICH_SIZE_PRICES = {5.50, 7.00, 8.50};
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
//                   break;
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
                //break;
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
        System.out.println("Adding a sandwich to the order...");

        // Prompt for sandwich size
        System.out.println("Select the sandwich size:");
        for (int i = 0; i < SANDWICH_SIZE.length; i++) {
            System.out.println((i + 1) + ") " + SANDWICH_SIZE[i] + "\"");
        }
        int sizeChoice = scanner.nextInt();
        if (sizeChoice < 1 || sizeChoice > SANDWICH_SIZE.length) {
            System.out.println("Invalid choice. Cancelling sandwich addition.");
            return;
        }
        double sandwichSize = SANDWICH_SIZE[sizeChoice - 1];
        double sandwichPrice = SANDWICH_SIZE_PRICES[sizeChoice - 1];

        System.out.println("Selected sandwich size: " + sandwichSize + "\"");
        System.out.println("Sandwich price: $" + sandwichPrice);
    }
    private static void addDrink() {

    }
    private static void addChips() {
    }
    private static void checkout() {
    }
    private static void cancelOrder() {
    }

    }
