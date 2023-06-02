package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserInterface {
    private static final double[] SANDWICH_SIZE = {4, 8, 12}; // Available sandwich sizes in inches
    private static final double[] SANDWICH_SIZE_PRICES = {5.50, 7.00, 8.50}; // Prices for each sandwich size
    private static List<String> orderEntries = new ArrayList<>(); // List to store order entries
    public static Scanner scanner = new Scanner(System.in);
    private double totalPrice;

    public void display() {
        displayHome();
    }

    private void displayHome() {

        System.out.println("\u001B[38;2;0;255;0m ___  ___ _    ___     ___ ___ ___  _   _ ___     \u001B[0m");
        System.out.println("\u001B[38;2;0;255;0m|   \\| __| |  |_ _|__ / __|_ _/ _ \\| | | / __|    \u001B[0m");
        System.out.println("\u001B[38;2;0;255;0m| |) | _|| |__ | |___| (__ | | (_) | |_| \\__ \\    \u001B[0m");
        System.out.println("\u001B[38;2;0;255;0m|___/|___|____|___|__ \\___|___\\___/_\\___/|___/___ \u001B[0m");
        System.out.println("\u001B[38;2;0;255;0m / __| /_\\ | \\| |   \\ \\    / /_ _/ __| || | __/ __|\u001B[0m");
        System.out.println("\u001B[38;2;0;255;0m \\__ \\/ _ \\| .` | |) \\ \\/\\/ / | | (__| __ | _|\\__ \\\u001B[0m");
        System.out.println("\u001B[38;2;0;255;0m |___/_/ \\_\\_|\\_|___/ \\_/\\_/ |___\\___|_||_|___|___/\u001B[0m");
        System.out.println("\n Welcome to our Deli!  \n Choose an option from our menu. ");

        System.out.println("\n \n Our Menu: ");
        //inputs will display the menu
        System.out.println("New Order [1]");
        System.out.println("Exit [2]");
        String Userinput = scanner.nextLine();

        switch (Userinput) {
            case "1" -> startOrder();
            case "2" -> {
                System.out.println("Goodbye, have a nice day! ");
                System.exit(0);
            }
            default -> System.out.println("Invalid input");

        }


    }

    private void startOrder() {
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
                case 5:
                    //Signature sandwich
                    addSignatureSandwich();
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

    private void addSignatureSandwich() {
        System.out.println("Select the signature sandwich:");
        for (SignatureSandwichChoice sandwichChoice : SignatureSandwichChoice.values()) {
            System.out.println(sandwichChoice.ordinal() + 1 + ") " + sandwichChoice.getDisplayName() + " - $" + sandwichChoice.getPrice());
        }

        int choice = scanner.nextInt();
        if (choice < 1 || choice > SignatureSandwichChoice.values().length) {
            System.out.println("Invalid choice. Signature sandwich not added to the order.");
            return;
        }

        SignatureSandwichChoice selectedSandwich = SignatureSandwichChoice.values()[choice - 1];
        double sandwichPrice = selectedSandwich.getPrice();

        orderEntries.add("Signature Sandwich - " + selectedSandwich.getDisplayName() + " - $" + sandwichPrice);
        totalPrice += sandwichPrice;

        System.out.println("Selected sandwich: " + selectedSandwich.getDisplayName() + " - $" + sandwichPrice);
        System.out.println("Signature sandwich added to the order.");
    }

    private void cancelOrder() {
        System.out.println("Your order was cancelled!");
    }

    private void checkout() {

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


    private void addChips() {

        System.out.println("Choose the chips to add to your order:");

        String[] chipsOptions = {"Regular", "BBQ", "Salt and Vinegar", "Sour Cream and Onion"};
        double[] chipsPrices = {1.50, 1.75, 1.75, 1.75};

        for (int i = 0; i < chipsOptions.length; i++) {
            System.out.println((i + 1) + ". " + chipsOptions[i] + " ($" + chipsPrices[i] + ")");
        }

        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= chipsOptions.length) {
            orderEntries.add(chipsOptions[choice - 1] + " - $" + chipsPrices[choice - 1]);
            totalPrice += chipsPrices[choice - 1];
            System.out.println(chipsOptions[choice - 1] + " added to the order.");
            System.out.println("It will be an extra $" + chipsPrices[choice - 1] + ".");
        } else {
            System.out.println("Invalid choice. Chips not added to the order.");
        }
    }

    private void addDrink() {
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

        System.out.println("Select the drink flavor:");
        for (FlavorChoice flavorChoice : FlavorChoice.values()) {
            System.out.println(flavorChoice.ordinal() + 1 + ") " + flavorChoice.getFlavor() + " - $" + flavorChoice.getPrice());
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

        System.out.println("Selected drink: " + selectedDrink.getSize() + " - $" + drinkPrice);
        System.out.println("Drink added to the order.");

    }

    private void addSandwich() {
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
        List<String> cheese = new ArrayList<>();
        double cheesePrice = 0.0;

        for (String cheeseChoice : cheeseChoices) {
            int choice = Integer.parseInt(cheeseChoice.trim());
            if (choice >= 1 && choice <= CheeseChoice.values().length) {
                CheeseChoice selectedCheese = CheeseChoice.values()[choice - 1];
                cheese.add(selectedCheese.getDisplayName());
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

        // Prompt for additional cheese option
        System.out.println("Add extra cheese? (y/n)");
        String extraCheeseChoice = scanner.next();
        boolean extraCheese = extraMeatChoice.equalsIgnoreCase("y");
        if (extraMeat) {
            cheesePrice += extraCheeseCost;
        }

        double sandwichTotalPrice = sandwichSizePrice + breadPrice + meatPrice;
        orderEntries.add("Sandwich - Size: " + sandwichSize + "\" - $" + sandwichTotalPrice);
        totalPrice += sandwichTotalPrice;

        System.out.println("Sandwich added to the order.");
        System.out.println("Total price of sandwich is " + totalPrice);
    }

    private double getCheesePriceForSize(double price, double sandwichSize) {
        double cheesePrice = 0;
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



    private void displayOptions() {
        System.out.println("\u001B[34m------------- Order Screen----------\u001B[0m");
        System.out.println("\u001B[34mChoose an option:\u001B[0m");
        System.out.println("\u001B[34m1) Add Sandwich\u001B[0m");
        System.out.println("\u001B[34m2) Add Drink\u001B[0m");
        System.out.println("\u001B[34m3) Add Chips\u001B[0m");
        System.out.println("\u001B[34m4) Checkout\u001B[0m");
        System.out.println("\u001B[34m0) Cancel Order\u001B[0m");
        System.out.println("OR");
        System.out.println("\u001B[34m5) Add Signature Sandwich\u001B[0m");
        System.out.println("\u001B[34m-------------------------------------\u001B[0m");

    }


}

