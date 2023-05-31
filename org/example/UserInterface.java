package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final double[] SANDWICH_SIZE = {4, 8, 12}; // Available sandwich sizes in inches
    private static final double[] SANDWICH_SIZE_PRICES = {5.50, 7.00, 8.50}; // Prices for each sandwich size
    private static List<String> orderEntries = new ArrayList<>(); // List to store order entries
    private static Scanner scanner = new Scanner(System.in);
    private static double totalPrice = 0.0;

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
        double sandwichSizePrice = SANDWICH_SIZE_PRICES[sizeChoice - 1];
        // Prompt for bread selection
        System.out.println("Select the bread:");
        for (BreadChoice breadChoice : BreadChoice.values()) {
            System.out.println(breadChoice.ordinal() + 1 + ") " + breadChoice.getDisplayName());
        }
        int breadChoiceIndex = scanner.nextInt();
        if (breadChoiceIndex < 1 || breadChoiceIndex > BreadChoice.values().length) {
            System.out.println("Invalid choice. Cancelling sandwich addition.");
            return;
        }
        BreadChoice selectedBread = BreadChoice.values()[breadChoiceIndex - 1];
        double breadPrice = selectedBread.getPrice();

        // Prompt for meat selection
        System.out.println("Select the meats:");
        for (MeatChoice meatChoice : MeatChoice.values()) {
            System.out.println(meatChoice.ordinal() + 1 + ") " + meatChoice.getDisplayName());
        }
        String meatsInput = scanner.next();
        String[] meatChoices = meatsInput.split(",");
        List<String> meats = new ArrayList<>();
        double meatPrice = 0.0;

        for (String meatChoice : meatChoices) {
            int choice = Integer.parseInt(meatChoice.trim());
            if (choice >= 1 && choice <= MeatChoice.values().length) {
                MeatChoice selectedMeat = MeatChoice.values()[choice - 1];
                meats.add(selectedMeat.getDisplayName());
                meatPrice += getMeatPriceForSize(selectedMeat.getPrice(), sandwichSize);
            } else {
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
            }
        }

        System.out.println("Selected sandwich size: " + sandwichSize + "\"");
        System.out.println("Sandwich size price: $" + sandwichSizePrice);
        System.out.println("Selected bread: " + selectedBread.getDisplayName());
        System.out.println("Bread price: $" + breadPrice);
        System.out.println("Selected meats: " + meats);
        System.out.println("Meat price: $" + meatPrice);

        double sandwichTotalPrice = sandwichSizePrice + breadPrice + meatPrice;
        orderEntries.add("Sandwich - Size: " + sandwichSize + "\" - $" + sandwichTotalPrice);
        totalPrice += sandwichTotalPrice;

        System.out.println("Sandwich added to the order.");
        System.out.println("Total price of sandwich is " +totalPrice);
    }

    private static double getMeatPriceForSize(double meatPrice, double sandwichSize) {
        if (sandwichSize == 4) {
            return meatPrice;  // Meat price for 4-inch sandwich
        } else if (sandwichSize == 8) {
            return meatPrice + 1;  // Meat price for 8-inch sandwich
        } else if (sandwichSize == 12) {
            return meatPrice + 2;  // Meat price for 12-inch sandwich
        } else {
            System.out.println("Invalid sandwich size. Default meat price will be used.");
            return meatPrice * 1.00;  // Default meat price
        }
    }

    private static void addDrink() {
        System.out.println("Adding a drink to the order...");

        System.out.println("Select the drink size:");
        for (DrinkChoice drinkChoice : DrinkChoice.values()) {
            System.out.println(drinkChoice.ordinal() + 1 + ") " + drinkChoice.getSize() + " - $" + drinkChoice.getPrice());
        }

        int sizeChoice = scanner.nextInt();
        if (sizeChoice < 1 || sizeChoice > DrinkChoice.values().length) {
            System.out.println("Invalid choice. Cancelling drink addition.");
            return;
        }

        DrinkChoice selectedDrink = DrinkChoice.values()[sizeChoice - 1];
        double drinkPrice = selectedDrink.getPrice();

        orderEntries.add("Drink - " + selectedDrink.getSize() + " - $" + drinkPrice);
        totalPrice += drinkPrice;

        System.out.println("Selected drink: " + selectedDrink.getSize() + " - $" + drinkPrice);
        System.out.println("Drink added to the order.");
    }

    private static void addChips() {
        System.out.println("Adding chips to the order...");
        System.out.println("Do you want to add chips? (Y/N)");

        String choice = scanner.next();
        if (choice.equalsIgnoreCase("Y")) {
            orderEntries.add("Chips - $1.50");
            totalPrice += 1.50;
            System.out.println("Chips added to the order.");
            System.out.println("It will be an extra $1.50.");
        } else {
            System.out.println("Chips not added to the order.");
        }
    }

    private static void checkout() {
        System.out.println("Order Summary:");
        for (String entry : orderEntries) {
            System.out.println(entry);
        }
        System.out.println("Total Price: $" + totalPrice);
    }

    private static void cancelOrder() {
        System.out.println("Order canceled.");
    }
}
