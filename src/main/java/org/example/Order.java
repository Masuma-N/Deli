package org.example;

import static org.example.UserInterface.scanner;

public class Order {
private void displayOrder(){
    System.out.println("Order Screen");
    System.out.println("1) Add Sandwich");
    System.out.println("2) Add Drink");
    System.out.println("3) Add Chips");
    System.out.println("4) Checkout");
    System.out.println("0) Cancel Order");
    String Userinput = scanner.nextLine();

    switch(Userinput){
        case "1"-> addSandwich();
        case "2" ->addDrink();
        case "3"->addChips();
        case "4" ->checkout();
        case "0" ->cancelOrder();
        default -> System.out.println("Invalid choice. Please try again.");

    }
    }

    private void cancelOrder() {

    }

    private void checkout() {

    }

    private void addChips() {

    }

    private void addDrink() {
        System.out.println("========== Add Drink ==========");
        // Ask the user for drink details (size, flavor) and add it to the orderEntries list

    }

    private void addSandwich() {

    }
}


