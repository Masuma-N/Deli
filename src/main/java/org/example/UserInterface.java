package src.main.java.org.example;

import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println("New Order [1]");
        System.out.println("Exit [2]");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                startOrder();
                break;
            case "2":
                System.out.println("Goodbye, have a nice day! ");
                System.exit(0);
            default:
                System.out.println("Invalid input");
                break;
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
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (choice != 4);
    }

    private void displayOptions() {
        System.out.println("\nWhat would you like to add to your order?");
        System.out.println("Add Sandwich [1]");
        System.out.println("Add Drink [2]");
        System.out.println("Add Chips [3]");
        System.out.println("Checkout [4]");
    }

    private void addSandwich() {
        System.out.println("\nAvailable sizes (in inches):");
        for (int i = 0; i < SANDWICH_SIZE.length; i++) {
            System.out.printf("%d. %.2f inches ($%.2f)\n", i + 1, SANDWICH_SIZE[i], SANDWICH_SIZE_PRICES[i]);
        }

        System.out.println("Please select a size:");
        int sizeChoice = scanner.nextInt();

        if (sizeChoice >= 1 && sizeChoice <= SANDWICH_SIZE.length) {
            double size = SANDWICH_SIZE[sizeChoice - 1];
            double price = SANDWICH_SIZE_PRICES[sizeChoice - 1];
            totalPrice += price;

            String orderEntry = String.format("Sandwich - Size: %.2f inches - Price: $%.2f", size, price);
            orderEntries.add(orderEntry);

            System.out.printf("Added Sandwich - Size: %.2f inches - Price: $%.2f\n", size, price);
        } else {
            System.out.println("Invalid input");
        }
    }

    private void addDrink() {
        System.out.println("Please enter the drink name:");
        scanner.nextLine(); // Consume newline character
        String drinkName = scanner.nextLine();

        System.out.println("Please enter the drink price:");
        double drinkPrice = scanner.nextDouble();

        totalPrice += drinkPrice;

        String orderEntry = String.format("Drink - Name: %s - Price: $%.2f", drinkName, drinkPrice);
        orderEntries.add(orderEntry);

        System.out.printf("Added Drink - Name: %s - Price: $%.2f\n", drinkName, drinkPrice);
    }

    private void addChips() {
        System.out.println("Please enter the chips name:");
        scanner.nextLine(); // Consume newline character
        String chipsName = scanner.nextLine();

        System.out.println("Please enter the chips price:");
        double chipsPrice = scanner.nextDouble();

        totalPrice += chipsPrice;

        String orderEntry = String.format("Chips - Name: %s - Price: $%.2f", chipsName, chipsPrice);
        orderEntries.add(orderEntry);

        System.out.printf("Added Chips - Name: %s - Price: $%.2f\n", chipsName, chipsPrice);
    }

    private void checkout() {
        System.out.println("\nYour order:");
        for (String orderEntry : orderEntries) {
            System.out.println(orderEntry);
        }

        System.out.printf("\nTotal Price: $%.2f\n", totalPrice);

        System.out.println("Please enter your name:");
        scanner.nextLine(); // Consume newline character
        String customerName = scanner.nextLine();

        System.out.println("Please enter your address:");
        String customerAddress = scanner.nextLine();

        System.out.println("Please enter your phone number:");
        String customerPhoneNumber = scanner.nextLine();

        String orderSummary = generateOrderSummary(customerName, customerAddress, customerPhoneNumber);

        System.out.println("\nOrder Summary:");
        System.out.println(orderSummary);

        saveOrder(orderSummary);

        System.out.println("\nThank you for your order! Enjoy your meal!");
        System.exit(0);
    }

    private String generateOrderSummary(String customerName, String customerAddress, String customerPhoneNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Summary\n");
        sb.append("--------------\n");
        sb.append("Customer Name: ").append(customerName).append("\n");
        sb.append("Address: ").append(customerAddress).append("\n");
        sb.append("Phone Number: ").append(customerPhoneNumber).append("\n");
        sb.append("Order Items:\n");
        for (String orderEntry : orderEntries) {
            sb.append("- ").append(orderEntry).append("\n");
        }
        sb.append("--------------\n");
        sb.append("Total Price: $").append(totalPrice).append("\n");

        return sb.toString();
    }

    private void saveOrder(String orderSummary) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String fileName = "order_" + dtf.format(now) + ".txt";

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(orderSummary);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the order.");
        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.display();
    }
}
