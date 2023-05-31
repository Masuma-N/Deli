package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final double[] SANDWICH_SIZE = {4, 8, 12}; // Available sandwich sizes in inches
    private static final double[] SANDWICH_SIZE_PRICES = {5.50, 7.00, 8.50}; // Prices for each sandwich size
    private static List<String> orderEntries = new ArrayList<>(); // List to store order entries
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
                    // break;
                case 3:
                    // Add Chips
                    addChips();
                    break;
                case 4:
                    // Checkout
                    checkout();
                    break;
                //case 0:
                    // Cancel Order
                   // cancelOrder();
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

        //System.out.println("Selected sandwich size: " + sandwichSize + "\"");
        //System.out.println("Sandwich price: $" + sandwichPrice);

        // Prompt for meat selection
        System.out.println("Select the meats:");
        System.out.println("1) Steak");
        System.out.println("2) Ham");
        System.out.println("3) Salami");
        System.out.println("4) Roast Beef");
        System.out.println("5) Chicken");
        System.out.println("6) Bacon");
        String meatsInput = scanner.next();
        String[] meatChoices = meatsInput.split(",");
        List<String> meats = new ArrayList<>();
        double meatPrice = 0.0;

        for (String meatChoice : meatChoices) {
            switch (meatChoice.trim()) {
                case "1":
                    meats.add("Steak");
                    meatPrice += getMeatPriceForSize(sandwichSize);
                    break;
                case "2":
                    meats.add("Ham");
                    meatPrice += getMeatPriceForSize(sandwichSize);
                    break;
                case "3":
                    meats.add("Salami");
                    meatPrice += getMeatPriceForSize(sandwichSize);
                    break;
                case "4":
                    meats.add("Roast Beef");
                    meatPrice += getMeatPriceForSize(sandwichSize);
                    break;
                case "5":
                    meats.add("Chicken");
                    meatPrice += getMeatPriceForSize(sandwichSize);
                    break;
                case "6":
                    meats.add("Bacon");
                    meatPrice += getMeatPriceForSize(sandwichSize);
                    break;
                default:
                    System.out.println("Invalid choice. Cancelling sandwich addition.");
                    return;
            }
        }


        System.out.println("Selected sandwich size: " + sandwichSize + "\"");
        System.out.println("Sandwich price: $" + sandwichPrice);
        System.out.println("Selected meats: " + meats);
        System.out.println("Meat price: $" + meatPrice);
    }


    private static double getMeatPriceForSize(double sandwichSize) {
        if (sandwichSize == 4) {
            return 1.00;  // Meat price for 4-inch sandwich
        } else if (sandwichSize == 8) {
            return 2.00;  // Meat price for 8-inch sandwich
        } else if (sandwichSize == 12) {
            return 3.00;  // Meat price for 12-inch sandwich
        } else {
            System.out.println("Invalid sandwich size. Default meat price will be used.");
            return 1.00;  // Default meat price
        }
    }


    private static void addDrink() {
        // TODO: Implement adding a drink to the order
    }

    private static void addChips() {
        // TODO: Implement adding chips to the order
    }

    private static void checkout() {
        // TODO: Implement the order checkout
    }
}