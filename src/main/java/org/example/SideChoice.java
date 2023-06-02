package org.example;

public enum SideChoice {
    AU_JUS("au jus", 0.0),
    SAUCE("sauce", 0.0);

    private String sideChoice;
    private double price;

    SideChoice(String sideChoice, double price) {
        this.sideChoice = sideChoice;
        this.price = price;
    }

    public String getSideChoice() {
        return sideChoice;
    }

    public double getPrice() {
        return price;
    }
}