package org.example;

public enum CheeseChoice {

    AMERICAN("American Cheese", 0.75),
    PROVOLONE("Provolone Cheese", 0.75),
    CHEDDAR("Cheddar Cheese", 0.75),
    SWISS("Swiss Cheese", 0.75);

    private final String displayName;
    private final double price;

    CheeseChoice(String displayName, double price) {
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