package org.example;

public enum BreadChoice {
    WHITE("White Bread",0),
    WHEAT("Wheat Bread", 0),

    RYE("Rye Bread", 0),
    WRAP("Multigrain Bread", 0);

    private final String displayName;
    private final double price;

    BreadChoice(String displayName, double price) {
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