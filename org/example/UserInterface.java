package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
                    checkOut();
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
        System.out.println("Select the Cheese:");
        for (CheeseChoice cheeseChoice : CheeseChoice.values()) {
            System.out.println((cheeseChoice.ordinal() + 1) + ") " + cheeseChoice.getDisplayName());
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
        ;


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
        System.out.println("Add extra cheese? (y/n)");
        String extraCheeseChoice = scanner.next();
        boolean extraCheese = extraCheeseChoice.equalsIgnoreCase("y");
        if (extraCheese) {
            cheesePrice += extraCheeseCost;
        }

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
        // Prompt for RegularToppingChoice selection
        System.out.println("Select the Regular Toppings (comma-separated):");
        for (RegularToppingChoice regularToppingChoice : RegularToppingChoice.values()) {
            System.out.println((regularToppingChoice.ordinal() + 1) + ") " + regularToppingChoice.getRegulartoppings());
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
      //promt user for Side selection
        // Prompt for Side choice
        System.out.println("Select the Side choices (comma-separated):");
        for (SideChoice sideChoice : SideChoice.values()) {
            System.out.println((sideChoice.ordinal() + 1) + ") " + sideChoice.getSideChoice());
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





        System.out.println("Selected sandwich size: " + sandwichSize + "\"");
        System.out.println("Sandwich size price: $" + sandwichSizePrice);
        System.out.println("Selected bread: " + selectedBread.getDisplayName());
        System.out.println("Bread price: $" + breadPrice);
        System.out.println("Selected meats: " + Arrays.toString(meats.toArray()).replace("[", "").replace("]", ""));



        System.out.println("Meat price: $" + meatPrice);
        System.out.println("Selected Cheese: " + Arrays.toString(cheese.toArray()).replace("[", "").replace("]", ""));

        System.out.println("Cheese price :" + cheesePrice);
        System.out.println("Selected Sauce: " + Arrays.toString(selectedSauces.toArray()).replace("[", "").replace("]", ""));
        System.out.println("Selected regular toppings: " + Arrays.toString(selectedRegularToppings.toArray()).replace("[", "").replace("]", ""));

        System.out.println("Regular toppings price: $" + regularToppingsPrice);
        System.out.println("Selected side choices: " + Arrays.toString(selectedSideChoices.toArray()).replace("[", "").replace("]", ""));

        System.out.println("Total side choices price: $" + sideChoicePrice);


        double sandwichTotalPrice = sandwichSizePrice + breadPrice + meatPrice +cheesePrice;
        orderEntries.add("Sandwich - Size: " + sandwichSize + "\" - $" + sandwichTotalPrice);
        totalPrice += sandwichTotalPrice;


        System.out.println("Total price of sandwich is " +totalPrice);
        System.out.println("Sandwich added to the order.");
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
    private static void addChips() {
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

    private static void checkOut() {
        System.out.println("Order Summary:");
        for (String entry : orderEntries) {
            System.out.println(entry);
        }
        System.out.println("Total Price: $" + totalPrice);

         //Generate unique file name using timestamp

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);
        String fileName = "order_" + timestamp + ".csv";

        // Save order to CSV file
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String entry : orderEntries) {
                fileWriter.append("Receipt\n");
                fileWriter.append(entry).append("\n");
            }
            fileWriter.append("Total Price: $").append(String.valueOf(totalPrice));
            System.out.println("Order saved to the CSV file: " + fileName);
            System.out.println("Would you like to order anything Else");
            System.out.println();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the order to the CSV file.");
        }
        // Clear order entries and total price
        orderEntries.clear();
        totalPrice = 0.0;


    }

    private static void cancelOrder() {
        System.out.println("Order canceled.");
    }
}