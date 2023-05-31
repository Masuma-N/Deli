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
                //case 4:
                    // Checkout
                   //checkout();
                   // break;
                //case 0:
                    // Cancel Order
                    //cancelOrder();
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
        System.out.println("1) 4\"");
        System.out.println("2) 8\"");
        System.out.println("3) 12\"");
        int sizeChoice = scanner.nextInt();
        double sizePrice;
        switch (sizeChoice) {
            case 1:
                sizePrice = 5.50;
                break;
            case 2:
                sizePrice = 7.00;
                break;
            case 3:
                sizePrice = 8.50;
                break;
            default:
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
        }

        // Prompt for bread type
        System.out.println("Select the bread type:");
        System.out.println("1) White");
        System.out.println("2) Wheat");
        System.out.println("3) Rye");
        System.out.println("4) Wrap");
        int breadChoice = scanner.nextInt();
        double breadPrice;
        switch (breadChoice) {
            case 1:
                breadPrice = 0.0;
                break;
            case 2:
                breadPrice = 0.0;
                break;
            case 3:
                breadPrice = 0.0;
                break;
            case 4:
                breadPrice = 0.0;
                break;
            default:
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
        }

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
                    meatPrice += 1.00;
                    break;
                case "2":
                    meats.add("Ham");
                    meatPrice += 1.00;
                    break;
                case "3":
                    meats.add("Salami");
                    meatPrice += 1.00;
                    break;
                case "4":
                    meats.add("Roast Beef");
                    meatPrice += 1.00;
                    break;
                case "5":
                    meats.add("Chicken");
                    meatPrice += 1.00;
                    break;
                case "6":
                    meats.add("Bacon");
                    meatPrice += 1.00;
                    break;
                default:
                    System.out.println("Invalid choice. Cancelling sandwich addition.");
                    return;
            }
        }

        // Prompt for additional meat option
        System.out.println("Add extra meat? (y/n)");
        String extraMeatChoice = scanner.next();
        boolean extraMeat = extraMeatChoice.equalsIgnoreCase("y");
        if (extraMeat) {
            meatPrice += 0.50;
        }
        // Prompt for cheese selection
        System.out.println("Select the cheese:");
        System.out.println("1) American ($0.50)");
        System.out.println("2) Provolone ($0.75)");
        System.out.println("3) Swiss ($0.75)");
        int cheeseChoice = scanner.nextInt();
        double cheesePrice;
        switch (cheeseChoice) {
            case 1:
                cheesePrice = 0.50;
                break;
            case 2:
                cheesePrice = 0.75;
                break;
            case 3:
                cheesePrice = 0.75;
                break;
            default:
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
        }

// Calculate the total price
        double totalPrice = sizePrice + breadPrice + meatPrice + cheesePrice;

// Display the order details
        System.out.println("Sandwich added to the order:");
        System.out.println("Size: " + sizeChoice);
        System.out.println("Bread: " + breadChoice);
        System.out.println("Meats: " + String.join(", ", meats));
        System.out.println("Extra Meat: " + extraMeat);
        System.out.println("Cheese: " + cheeseChoice);
        System.out.println("Total Price: $" + totalPrice);

    }
    private static void addDrink() {
}
    private static void addChips() {
    }
    }