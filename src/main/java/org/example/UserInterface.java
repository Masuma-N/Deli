package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

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
        List<MeatChoice> selectedMeats = new ArrayList<>();
        double meatPrice = 0.0;

        for (String meatChoice : meatChoices) {
            int choice = Integer.parseInt(meatChoice.trim());
            if (choice >= 1 && choice <= MeatChoice.values().length) {
                MeatChoice selectedMeat = MeatChoice.values()[choice - 1];
                selectedMeats.add(selectedMeat);
                meatPrice += getMeatPriceForSize(selectedMeat.getPrice(), sandwichSize);
            } else {
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
            }
        } //TODO: To here.

        // Calculate cost of extra meat based on sandwich size
        double extraMeatCost = 0.0;
        if (sandwichSize == 4) {
            extraMeatCost = 0.50;
        } else if (sandwichSize == 8) {
            extraMeatCost = 1.00;
        } else if (sandwichSize == 12) {
            extraMeatCost = 1.50;
        }

// Prompt for additional meat option
        System.out.println("Add extra meat? (y/n)");
        String extraMeatChoice = scanner.next();
        boolean extraMeat = extraMeatChoice.equalsIgnoreCase("y");
        if (extraMeat) {
            meatPrice += extraMeatCost;
        }



        //prompt for cheese
        System.out.println("Select the cheese:");
        for (CheeseChoice cheeseChoice : CheeseChoice.values()) {
            System.out.println(cheeseChoice.ordinal() + 1 + ") " + cheeseChoice.getDisplayName());
        }
        String cheeseInput = scanner.next();
        String[] cheeseChoices = meatsInput.split(",");
        List<String> cheeses = new ArrayList<>();
        double cheesePrice = 0.0;

        for (String cheeseChoice : cheeseChoices) {
            int choice = Integer.parseInt(cheeseChoice.trim());
            if (choice >= 1 && choice <= CheeseChoice.values().length) {
                CheeseChoice selectedCheese = CheeseChoice.values()[choice - 1];
                cheeses.add(selectedCheese.getDisplayName());
                cheesePrice += getCheesePriceForSize(selectedCheese.getPrice(), sandwichSize);

            } else {
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
            }
        }
        // Calculate cost of extra cheese based on sandwich size
        double extraCheeseCost = 0.0;
        if (sandwichSize == 4) {
            extraCheeseCost = 0.30;
        } else if (sandwichSize == 8) {
            extraCheeseCost = 0.60;
        } else if (sandwichSize == 12) {
            extraCheeseCost = 0.90;
        }

// Prompt for additional meat option
        System.out.println("Add extra cheese? (y/n)");
        String extraCheeseChoice = scanner.next();
        boolean extraCheese = extraMeatChoice.equalsIgnoreCase("y");
        if (extraMeat) {
            cheesePrice += extraCheeseCost;
        }
        //TODO: MARICARMEN: Adding Sauces selection
        // Prompt for sauce selection
        System.out.println("Select the sauces (comma-separated):");
        for (SauceChoice sauceChoice : SauceChoice.values()) {
            System.out.println(sauceChoice.ordinal() + 1 + ") " + sauceChoice.getSauce());
        }
        String saucesInput = scanner.next();
        String[] sauceChoices = saucesInput.split(",");
        List<SauceChoice> selectedSauces = new ArrayList<>();
        double saucePrice = 0.0;

        for (String sauceChoice : sauceChoices) {
            int choice = Integer.parseInt(sauceChoice.trim());
            if (choice >= 1 && choice <= SauceChoice.values().length) {
                SauceChoice selectedSauce = SauceChoice.values()[choice - 1];
                selectedSauces.add(selectedSauce);
                saucePrice += selectedSauce.getPrice();
            } else {
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
            }
        }
        //TODO: MARICARMEN:
        // Added selectedMeatsString and selectedSaucesString to format the String better.
        //Copy from Here.
        String selectedMeatsString = selectedMeats.stream()
                .map(MeatChoice::getDisplayName)
                .collect(Collectors.joining(", "));

        String selectedSaucesString = selectedSauces.stream()
                .map(SauceChoice::getSauce)
                .collect(Collectors.joining(", "));

        System.out.println("Selected sandwich size: " + sandwichSize + "\" Inches");
        System.out.println("Sandwich size price: $" + sandwichSizePrice);
        System.out.println("Selected bread: " + selectedBread.getDisplayName());
        System.out.println("Bread price: $" + breadPrice);
        System.out.println("Selected meats: " + selectedMeatsString);
        System.out.println("Meat price: $" + meatPrice);
        System.out.println("Selected cheeses: " + cheeses);
        System.out.println("Selected sauces: " + selectedSaucesString);
        System.out.println("Sauce included: $" + saucePrice);

        double sandwichTotalPrice = sandwichSizePrice + breadPrice + meatPrice + saucePrice;
        orderEntries.add("Sandwich - Size: " + sandwichSize + "\" - $" + sandwichTotalPrice);
        totalPrice += sandwichTotalPrice;

        System.out.println("Sandwich added to the order.");
        System.out.println("Total price of sandwich is $" + totalPrice);
        //TODO: To here.

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


    private static double getCheesePriceForSize(double cheesePrice, double sandwichSize) {
        if (sandwichSize == 4) {
            return cheesePrice;  // Meat price for 4-inch sandwich
        } else if (sandwichSize == 8) {
            return cheesePrice + 0.75;  // Meat price for 8-inch sandwich
        } else if (sandwichSize == 12) {
            return cheesePrice + 1.50;  // Meat price for 12-inch sandwich
        } else {
            System.out.println("Invalid sandwich size.");
            return cheesePrice * 1.00;  // Default meat price
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
        // ...existing code...

        // Generate unique file name using timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);
        String fileName = "order_" + timestamp + ".csv";

        // Save order to CSV file
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String entry : orderEntries) {
                fileWriter.append(entry).append("\n");
            }
            fileWriter.append("Total Price: $").append(String.valueOf(totalPrice));
            System.out.println("Order saved to the CSV file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the order to the CSV file.");
        }
        // Clear order entries and total price
        orderEntries.clear();
        totalPrice = 0.0;

        // Go back to the main screen
        displayHome();

    }


    private static void cancelOrder() {
        System.out.println("Order canceled.");
    }
}