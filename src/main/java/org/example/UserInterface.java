package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static List<String> orderEntries = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public void display() {
        displayHome();
    }

    private void displayHome() {
        //inputs will display the menu
        System.out.println("""
                Welcome to our Deli!\s
                                
                How may I help you?
                [1] Start New Order
                [2] Exit\s
                """);
        String Userinput = scanner.nextLine();
        switch (Userinput) {
            case "1" -> startOrder();
            case "2" -> {
                System.out.println("Goodbye, have a nice day!");
                System.exit(0);
            }
            default -> System.out.println("Invalid entry.");
        }
    }

    private void startOrder() {
//        System.out.println("Sorry! Nothing here yet.");
        String usersChoice;

        do {
            System.out.println("""
                    DELI-cious - Order Screen
                    Choose an option:
                    [1] Add Sandwich
                    [2] Add Drink
                    [3] Add Chips
                    [4] Checkout
                    [0] Cancel\s
                    """);

            usersChoice = scanner.nextLine();
            switch (usersChoice) {
                case "1" -> addSandwich();
                case "2" -> addDrink();
                case "3" -> addChips();
                case "4" -> checkOut();
                case "0" -> cancelOrder();
                default -> System.out.println("Invalid entry.");
            }
        } while (!usersChoice.equals("0") && !usersChoice.equals("4"));
    }

    private static void addSandwich() {
        //TODO: Implement logic to add a sandwich to the order.
        // Prompt for sandwich size
        System.out.println("""
                Let's add a sandwich!
                
                What size sandwich would you like?
                [1] 4"
                [2] 8"
                [3] 12"
                """);//SUGGESTION: Maybe include a return to menu prompt.
        String usersSize = scanner.nextLine();
        int size;
        switch (usersSize) {
            case "1" -> {}
            case "2" -> {}
            case "3" -> {}//SUGGESTION: Maybe add a "return to menu" method.
            default -> System.out.println("Invalid entry.");
        }
        //TODO: Prompt for sandwich bread.
        System.out.println("""
                Great! What type of bread would you like?
                [1] White
                [2] Wheat
                [3] Rye
                [4] Wrap
                """);
        String usersBread = scanner.nextLine();
        String bread;
        switch (usersSize) {
            case "1" -> {}
            case "2" -> {}
            case "3" -> {}
            case "4" -> {} //SUGGESTION: Maybe add a "return to menu" method.
            default -> System.out.println("Invalid entry.");
        }
        //TODO: Prompt for sandwich meat.
        System.out.println("""
                Great! What type of meat would you like?
                * Add a comma between multiple types, "1,2,3"
                [1] Steak
                [2] Ham
                [3] Salami
                [4] Roast Beef
                [5] Chicken
                [6] Bacon\s
                """);
        String usersMeats = scanner.next();
        String[] meatsChosen = usersMeats.split(",");


    }

    private static void addDrink() {
        //TODO: Implement logic to add a drink to the order.

    }

    private static void addChips() {
        //TODO: Implement logic to add a bag of chips to the order.

    }

    private static void checkOut() {
        //TODO: Implement logic to checkout an order.

    }

    private static void cancelOrder() {
        //TODO: Implement logic to cancel the order.

    }
}