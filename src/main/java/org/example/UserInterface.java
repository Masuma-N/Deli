package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final double[] SANDWICH_SIZE = {4, 8, 12}; // Available sandwich sizes in inches
    private static final double[] SANDWICH_SIZE_PRICES = {5.50, 7.00, 8.50}; // Prices for each sandwich size
    private static List<String> orderEntries = new ArrayList<>(); // List to store order entries
    public static Scanner scanner = new Scanner(System.in);
    private static double totalPrice = 0.0;

    public void display() {
        displayHome();
    }

    private void displayHome() {
        System.out.println("\u001B[38;2;0;0;255m ___  ___ _    ___     ___ ___ ___  _   _ ___     \u001B[0m");
        System.out.println("\u001B[38;2;0;0;255m|   \\| __| |  |_ _|__ / __|_ _/ _ \\| | | / __|    \u001B[0m");
        System.out.println("\u001B[38;2;0;0;255m| |) | _|| |__ | |___| (__ | | (_) | |_| \\__ \\    \u001B[0m");
        System.out.println("\u001B[38;2;0;0;255m|___/|___|____|___|__ \\___|___\\___/_\\___/|___/___ \u001B[0m");
        System.out.println("\u001B[38;2;0;0;255m / __| /_\\ | \\| |   \\ \\    / /_ _/ __| || | __/ __|\u001B[0m");
        System.out.println("\u001B[38;2;0;0;255m \\__ \\/ _ \\| .` | |) \\ \\/\\/ / | | (__| __ | _|\\__ \\\u001B[0m");
        System.out.println("\u001B[38;2;0;0;139m |___/_/ \\_\\_|\\_|___/ \\_/\\_/ |___\\___|_||_|___|___/\u001B[0m");
        System.out.println("""
                                
                Welcome to our Deli!
                Please enter a bracket option (number only).
                                
                                MAIN MENU
                ---------------------------------------------
                [1] New Order
                [2] Exit
                """);
        boolean isValidInput = false;
        while (!isValidInput) {
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    startOrder();
                    break;
                case "2":
                    System.out.println("Thank you! Come again!");
                    System.exit(0);
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void startOrder() {
        int userInput;
        do {
            horizontalLine();
            System.out.println("""
                    Try the best sandwiches in town!
                    Please enter a bracket option (number only).
                     
                                       ORDER MENU
                    ---------------------------------------------
                    [1] Add Custom Sandwich
                    [2] Add Signature Sandwich
                    [3] Add Drink
                    [4] Add Chips
                    [5] Checkout
                    [0] Exit
                    """);
            while (!scanner.hasNextInt()) {
                invalidInput();
                scanner.nextLine(); // Consume invalid input
            }
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1 -> addSandwich();
                case 2 -> addSignatureSandwich();
                case 3 -> addDrink();
                case 4 -> addChips();
                case 5 -> checkOut();
                case 0 -> cancelOrder();
                default -> invalidInput();
            }
        } while (userInput != 0);
    }

    private static void addSandwich() {

        // Prompt for SANDWICH SIZE
        System.out.println("\u001B[38;2;0;0;255mAdding custom sandwich...\u001B[0m");
        System.out.println("\u001B[38;2;173;216;230mSelect a sandwich size: \nPlease enter a bracket option (number only).\u001B[0m");
        for (int i = 0; i < SANDWICH_SIZE.length; i++) {
            System.out.println("[" + (i + 1) + "] " + SANDWICH_SIZE[i] + "\"");
        }
        int sizeChoice = scanner.nextInt();
        if (sizeChoice < 1 || sizeChoice > SANDWICH_SIZE.length) {
            System.out.println("Invalid choice. Cancelling sandwich addition.");
            return;
        }
        double sandwichSize = SANDWICH_SIZE[sizeChoice - 1];
        double sandwichSizePrice = SANDWICH_SIZE_PRICES[sizeChoice - 1];


        // Prompt for BREAD CHOICE
        System.out.println("\u001B[38;2;173;216;230mSelect a bread: \nPlease enter a bracket option (number only).\u001B[0m");
        for (BreadChoice breadChoice : BreadChoice.values()) {
            System.out.println("[" + (breadChoice.ordinal() + 1) + "] " + breadChoice.getDisplayName());
        }
        int breadChoiceIndex = scanner.nextInt();
        if (breadChoiceIndex < 1 || breadChoiceIndex > BreadChoice.values().length) {
            System.out.println("Invalid choice. Cancelling sandwich addition.");
            return;
        }
        BreadChoice selectedBread = BreadChoice.values()[breadChoiceIndex - 1];
        double breadPrice = selectedBread.getPrice();


        // Prompt for MEAT CHOICE
        System.out.println("\u001B[38;2;173;216;230mSelect meat(s): \nPlease enter a bracket option (number only).\nSeparate by comma, if multiple (e.g. 1,2,3)\u001B[0m");
        for (MeatChoice meatChoice : MeatChoice.values()) {
            System.out.println("[" + (meatChoice.ordinal() + 1) + "] " + meatChoice.getDisplayName());
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

        // Calculates cost of extra meat based on sandwich size
        double extraMeatCost = 0.0;
        if (sandwichSize == 4) {
            extraMeatCost = 0.50;
        } else if (sandwichSize == 8) {
            extraMeatCost = 1.00;
        } else if (sandwichSize == 12) {
            extraMeatCost = 1.50;
        }

        // Prompt for EXTRA MEAT
        System.out.println("\u001B[38;2;173;216;230mAdd extra meat? (y/n)\u001B[0m");
        String extraMeatChoice = scanner.next();
        boolean extraMeat = extraMeatChoice.equalsIgnoreCase("y");
        if (extraMeat) {
            meatPrice += extraMeatCost;
        }


        // Prompt for CHEESE CHOICE
        System.out.println("\u001B[38;2;173;216;230mSelect cheese(s): \nPlease enter a bracket option (number only).\nSeparate by comma, if multiple (e.g. 1,2,3)\u001B[0m");
        for (CheeseChoice cheeseChoice : CheeseChoice.values()) {
            System.out.println("[" + (cheeseChoice.ordinal() + 1) + "] " + cheeseChoice.getDisplayName());
        }

        String cheeseInput = scanner.next();
        String[] cheeseChoices = cheeseInput.split(",");
        List<String> cheese = new ArrayList<>();

        for (String choice : cheeseChoices) {
            int cheeseChoiceIndex = Integer.parseInt(choice.trim());
            if (cheeseChoiceIndex >= 1 && cheeseChoiceIndex <= CheeseChoice.values().length) {
                CheeseChoice selectedCheese = CheeseChoice.values()[cheeseChoiceIndex - 1];
                cheese.add(selectedCheese.getDisplayName());
            } else {
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
            }
        }

        // Calculate cost of Cheese based on sandwich size
        double cheesePrice = 0.0;
        if (sandwichSize == 4) {
            cheesePrice = 0.75;
        } else if (sandwichSize == 8) {
            cheesePrice = 1.50;
        } else if (sandwichSize == 12) {
            cheesePrice = 2.25;
        }

        // Calculate the cost of extra cheese based on bread size
        double extraCheeseCost = 0.0;
        if (sandwichSize == 4) {
            extraCheeseCost = 0.30;
        } else if (sandwichSize == 8) {
            extraCheeseCost = 0.60;
        } else if (sandwichSize == 12) {
            extraCheeseCost = 0.90;
        }


        // Prompt for additional cheese option
        System.out.println("\u001B[38;2;173;216;230mAdd extra cheese? (y/n)\u001B[0m");
        String extraCheeseChoice = scanner.next();
        boolean extraCheese = extraCheeseChoice.equalsIgnoreCase("y");
        if (extraCheese) {
            cheesePrice += extraCheeseCost;
        }

        // Prompt for SAUCE CHOICE
        System.out.println("\u001B[38;2;173;216;230mSelect sauce(s): \nPlease enter a bracket option (number only).\nSeparate by comma, if multiple (e.g. 1,2,3)\u001B[0m");
        for (SauceChoice sauceChoice : SauceChoice.values()) {
            System.out.println("[" + (sauceChoice.ordinal() + 1) + "] " + sauceChoice.getSauceName());
        }
        String saucesInput = scanner.next();
        String[] sauceChoices = saucesInput.split(",");
        List<String> selectedSauces = new ArrayList<>();
        double saucePrice = 0.0;

        for (String sauceChoice : sauceChoices) {
            int choice = Integer.parseInt(sauceChoice.trim());
            if (choice >= 1 && choice <= SauceChoice.values().length) {
                SauceChoice selectedSauce = SauceChoice.values()[choice - 1];
                selectedSauces.add(selectedSauce.getSauceName());
                saucePrice += selectedSauce.getPrice();
            } else {
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
            }
        }
        // Prompt for REGULAR TOPPING CHOICE
        System.out.println("\u001B[38;2;173;216;230mSelect regular topping(s): \nPlease enter a bracket option (number only).\nSeparate by comma, if multiple (e.g. 1,2,3)\u001B[0m");
        for (RegularToppingChoice regularToppingChoice : RegularToppingChoice.values()) {
            System.out.println("[" + (regularToppingChoice.ordinal() + 1) + "] " + regularToppingChoice.getRegulartoppings());
        }
        String regularToppingsInput = scanner.next();
        String[] regularToppingsChoices = regularToppingsInput.split(",");
        List<String> selectedRegularToppings = new ArrayList<>();
        double regularToppingsPrice = 0.0;

        for (String choice : regularToppingsChoices) {
            int regularToppingChoiceIndex = Integer.parseInt(choice.trim());
            if (regularToppingChoiceIndex >= 1 && regularToppingChoiceIndex <= RegularToppingChoice.values().length) {
                RegularToppingChoice selectedRegularTopping = RegularToppingChoice.values()[regularToppingChoiceIndex - 1];
                selectedRegularToppings.add(selectedRegularTopping.getRegulartoppings());
                regularToppingsPrice += selectedRegularTopping.getPrice();
            } else {
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
            }
        }
        // Prompt for SIDE CHOICE
        System.out.println("\u001B[38;2;173;216;230mSelect side(s): \nPlease enter a bracket option (number only).\nSeparate by comma, if multiple (e.g. 1,2,3)\u001B[0m");
        for (SideChoice sideChoice : SideChoice.values()) {
            System.out.println("[" + (sideChoice.ordinal() + 1) + "] " + sideChoice.getSideChoice());
        }
        String sideChoicesInput = scanner.next();
        String[] sideChoicesArray = sideChoicesInput.split(",");
        List<String> selectedSideChoices = new ArrayList<>();
        double sideChoicePrice = 0.0;

        for (String choice : sideChoicesArray) {
            int sideChoiceIndex = Integer.parseInt(choice.trim());
            if (sideChoiceIndex >= 1 && sideChoiceIndex <= SideChoice.values().length) {
                SideChoice selectedSideChoice = SideChoice.values()[sideChoiceIndex - 1];
                selectedSideChoices.add(selectedSideChoice.getSideChoice());
                sideChoicePrice += selectedSideChoice.getPrice();
            } else {
                System.out.println("Invalid choice. Cancelling sandwich addition.");
                return;
            }
        }
        System.out.println("\u001B[38;2;173;216;230mSANDWICH SUMMARY: \u001B[0m");
        System.out.println("Selected sandwich size: " + sandwichSize + "\" Inches");
        System.out.println("Sandwich size price: $" + sandwichSizePrice);
        System.out.println("Selected bread: " + selectedBread.getDisplayName());
        System.out.println("Bread price: $" + breadPrice);
        System.out.println("Selected meats: " + Arrays.toString(meats.toArray()).replace("[", "").replace("]", ""));
        System.out.println("Meat price: $" + meatPrice);
        System.out.println("Selected Cheese: " + Arrays.toString(cheese.toArray()).replace("[", "").replace("]", ""));
        System.out.println("Cheese price: $" + cheesePrice);
        System.out.println("Selected Sauce: " + Arrays.toString(selectedSauces.toArray()).replace("[", "").replace("]", ""));
        System.out.println("Sauce price: $" + saucePrice);
        System.out.println("Selected regular toppings: " + Arrays.toString(selectedRegularToppings.toArray()).replace("[", "").replace("]", ""));
        System.out.println("Regular toppings price: $" + regularToppingsPrice);
        System.out.println("Selected side choices: " + Arrays.toString(selectedSideChoices.toArray()).replace("[", "").replace("]", ""));
        System.out.println("Total side choices price: $" + sideChoicePrice);

        double sandwichTotalPrice = sandwichSizePrice + breadPrice + meatPrice + cheesePrice;
        orderEntries.add("Sandwich - Size: " + sandwichSize + "\" - $" + sandwichTotalPrice);
        totalPrice += sandwichTotalPrice;

        System.out.println("\u001B[38;2;173;216;230mTotal price of sandwich is $" + totalPrice + "\u001B[0m");
        System.out.println("\u001B[38;2;0;0;255mSandwich added to the order.\u001B[0m");
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

    private void addSignatureSandwich() {
        System.out.println("\u001B[38;2;0;0;255mAdding signature sandwich...\u001B[0m");
        System.out.println("\u001B[38;2;173;216;230mSelect a sandwich: \nPlease enter a bracket option (number only).\u001B[0m");
        for (SignatureSandwichChoice sandwichChoice : SignatureSandwichChoice.values()) {
            System.out.println("[" + (sandwichChoice.ordinal() + 1) + "] " + sandwichChoice.getDisplayName() + " - $" + sandwichChoice.getPrice());
        }

        int choice = scanner.nextInt();
        if (choice < 1 || choice > SignatureSandwichChoice.values().length) {
            System.out.println("Invalid choice. Signature sandwich not added to the order.");
            return;
        }

        SignatureSandwichChoice selectedSandwich = SignatureSandwichChoice.values()[choice - 1];
        double sandwichPrice = selectedSandwich.getPrice();

        orderEntries.add("Signature Sandwich - " + selectedSandwich.getDisplayName() + ": $" + sandwichPrice);
        totalPrice += sandwichPrice;

        System.out.println("Selected signature sandwich: " + selectedSandwich.getDisplayName() + "\nSignature sandwich price: $" + sandwichPrice);
        System.out.println("\u001B[38;2;0;0;255mSignature sandwich added to the order.\u001B[0m");
    }

    private static void addDrink() {
        System.out.println("\u001B[38;2;0;0;255mAdding drink...\u001B[0m");
        System.out.println("\u001B[38;2;173;216;230mSelect drink size: \nPlease enter a bracket option (number only).\u001B[0m");
        for (DrinkChoice drinkChoice : DrinkChoice.values()) {
            System.out.println("[" + (drinkChoice.ordinal() + 1) + "] " + drinkChoice.getSize() + " - $" + drinkChoice.getPrice());
        }

        int sizeChoice = scanner.nextInt();
        if (sizeChoice < 1 || sizeChoice > DrinkChoice.values().length) {
            System.out.println("Invalid choice. Cancelling drink addition.");
            return;
        }

        DrinkChoice selectedDrink = DrinkChoice.values()[sizeChoice - 1];
        double drinkPrice = selectedDrink.getPrice();

        System.out.println("\u001B[38;2;173;216;230mSelect the drink flavor: \u001B[0m");
        for (FlavorChoice flavorChoice : FlavorChoice.values()) {
            System.out.println("[" + (flavorChoice.ordinal() + 1) + "] " + flavorChoice.getFlavor() + " - $" + flavorChoice.getPrice());
        }

        int flavorChoice = scanner.nextInt();
        if (flavorChoice < 1 || flavorChoice > FlavorChoice.values().length) {
            System.out.println("Invalid choice. Cancelling drink addition.");
            return;
        }

        FlavorChoice selectedFlavor = FlavorChoice.values()[flavorChoice - 1];
        double flavorPrice = selectedFlavor.getPrice();


        orderEntries.add("Drink - " + selectedDrink.getSize() + " - $" + drinkPrice);
        totalPrice += drinkPrice;

        System.out.println("Selected drink: " + selectedDrink.getSize() + " " + selectedFlavor.getFlavor() + "\nDrink price: $" + drinkPrice);
        System.out.println("\u001B[38;2;0;0;255mDrink added to the order.\u001B[0m");
    }

    private static void addChips() {
        System.out.println("\u001B[38;2;0;0;255mAdding chips...\u001B[0m");
        System.out.println("\u001B[38;2;173;216;230mSelect chips: \nPlease enter a bracket option (number only).\u001B[0m");

        String[] chipsOptions = {"Original", "BBQ", "Salt and Vinegar", "Sour Cream and Onion"};
        double[] chipsPrices = {1.50, 1.75, 1.75, 1.75};

        for (int i = 0; i < chipsOptions.length; i++) {
            System.out.println("[" + (i + 1) + "] " + chipsOptions[i] + " - $" + chipsPrices[i]);
        }

        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= chipsOptions.length) {
            orderEntries.add(chipsOptions[choice - 1] + " - $" + chipsPrices[choice - 1]);
            totalPrice += chipsPrices[choice - 1];
            System.out.println("That will be an extra $" + chipsPrices[choice - 1] + ".");
            System.out.println("\u001B[38;2;0;0;255m"+chipsOptions[choice - 1] + " chips added to the order.\u001B[0m");
        } else {
            System.out.println("Invalid choice. Chips not added to the order.");
        }
    }

    private static void checkOut() {

        System.out.println("\u001B[38;2;0;0;255mOrder Summary: \u001B[0m");
        for (String entry : orderEntries) {
            System.out.println(entry);
        }
        System.out.println("\u001B[38;2;173;216;230mTotal Price: $" + totalPrice + "\u001B[0m");
        try {
            // Create the directory if it doesn't exist
            String directoryPath = "Receipts";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            // Generate the file name using the current date and time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
            String fileName = directoryPath + "/" + now.format(formatter) + ".csv";

            // Write the receipt to the file
            FileWriter writer = new FileWriter(fileName);
            writer.write("DELI-cious Sandwiches Receipt\n");
            writer.write(now.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + "\n");
            writer.write(now.format(DateTimeFormatter.ofPattern("HH:mm")) + "\n");
            writer.write("---------------------------------------------\n");
            writer.write("Item\t\t\tItem Price\n");
            for (String entry : orderEntries) {
                writer.write(entry + "\n");
            }
            writer.write("---------------------------------------------\n");
            writer.write("Total Price: " + totalPrice + "\n");
            writer.close();

            System.out.println("\u001B[38;2;0;0;255mReceipt saved successfully!\u001B[0m");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt.");
            e.printStackTrace();
        }
    }

    private void cancelOrder() {
        System.out.println("Thank you! Have a good day!");
        System.exit(0);
    }

    public void invalidInput() {
        System.out.println("\u001B[38;2;139;0;0mInvalid input. Please try again.\u001B[0m");
    }

    public void horizontalLine() {
        System.out.println("\u001B[38;2;0;0;139m-----------------------------------------------------------------------------------------------------------------\u001B[0m");
    }

}