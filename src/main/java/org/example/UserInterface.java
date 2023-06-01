package org.example;
import java.sql.SQLOutput;
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

        System.out.println("\u001B[33m ___  ___ _    ___     ___ ___ ___  _   _ ___     \u001B[0m");
        System.out.println("\u001B[33m|   \\| __| |  |_ _|__ / __|_ _/ _ \\| | | / __|    \u001B[0m");
        System.out.println("\u001B[33m| |) | _|| |__ | |___| (__ | | (_) | |_| \\__ \\    \u001B[0m");
        System.out.println("\u001B[33m|___/|___|____|___|__ \\___|___\\___/_\\___/|___/___ \u001B[0m");
        System.out.println("\u001B[33m / __| /_\\ | \\| |   \\ \\    / /_ _/ __| || | __/ __|\u001B[0m");
        System.out.println("\u001B[33m \\__ \\/ _ \\| .` | |) \\ \\/\\/ / | | (__| __ | _|\\__ \\\u001B[0m");
        System.out.println("\u001B[33m |___/_/ \\_\\_|\\_|___/ \\_/\\_/ |___\\___|_||_|___|___/\u001B[0m");
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

    private void cancelOrder() {
    }

    private void checkout() {
        System.out.println("Order Summary");
        for (String entry : orderEntries) {
            System.out.println(entry);
        }
        System.out.println("Total Price: $" + totalPrice);
    }

    private void addChips() {
        System.out.println("Would you like to add chips to your order? (Y/N)");

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

        // Prompt the user for cheese selection
        System.out.println("Select the cheese:");
        System.out.println("1) American ");
        System.out.println("2) Provolone ");
        System.out.println("3) Cheddar ");
        System.out.println("4 Swiss");


        // Calculate cost of Cheese based on sandwich size
        int cheeseChoice = scanner.nextInt();
        double cheesePrice = 0.0;
        if (sandwichSize == 4) {
            cheesePrice = 0.75;
        } else if (sandwichSize == 8) {
            cheesePrice = 1.50;
        } else if (sandwichSize == 12) {
            cheesePrice = 2.25;
        }


        System.out.println("Selected sandwich size: " + sandwichSize + "\"");
        System.out.println("Sandwich size price: $" + sandwichSizePrice);
        System.out.println("Selected bread: " + selectedBread.getDisplayName());
        System.out.println("Bread price: $" + breadPrice);
        System.out.println("Selected meats: " + meats);
        System.out.println("Meat price: $" + meatPrice);
        if (cheeseChoice == 1) {
            System.out.println("American Cheese");
        } else if (cheeseChoice == 2) {
            System.out.println("Provolone Cheese");
        } else if (cheeseChoice == 3) {
            System.out.println("Cheddar Cheese");
        } else if (cheeseChoice == 3) {
            System.out.println("Swiss Cheese");

        }
        // System.out.println(cheeseChoice);
        System.out.println("Cheese price :" + cheesePrice);

        double sandwichTotalPrice = sandwichSizePrice + breadPrice + meatPrice;
        orderEntries.add("Sandwich - Size: " + sandwichSize + "\" - $" + sandwichTotalPrice);
        totalPrice += sandwichTotalPrice;

        System.out.println("Sandwich added to the order.");
        System.out.println("Total price of sandwich is " + totalPrice);
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
        System.out.println("------------- Order Screen----------");
        System.out.println("Choose an option:");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.println("-------------------------------------");

    }


}

