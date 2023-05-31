package org.example;
import java.sql.SQLOutput;
import java.util.Scanner;


public class UserInterface {
    public static Scanner scanner= new Scanner(System.in);
    public void display(){
        displayHome();
    }
    private void displayHome() {


        System.out.println("Welcome to our Deli!  \n How may I help you? ");

        System.out.println("\n \n Our Menu: ");
        //inputs will display the menu
        System.out.println("New Order [1]");
        System.out.println("Exit [2]");
        String Userinput = scanner.nextLine();

        switch(Userinput){
            case "1" ->startOrder();
            case "2" -> {
                System.out.println("Goodbye, have a nice day! Please..");
                System.exit(0);
            }
            default -> System.out.println();

        }



    }

    private void startOrder() {
    }


}