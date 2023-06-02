package org.example;

public enum SignatureSandwichChoice {
    ITALIAN("Italian", 9.50),
    CLUB("Club", 10.00),
    BLT("BLT", 8.50);

    private String displayName;
    private double price;

    SignatureSandwichChoice(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice() {
        return price;
    }
}
