package org.example;

public enum FlavorChoice {
    CHOCOLATE("Chocolate", 0.00),
    VANILLA("Vanilla", 0.00),
    STRAWBERRY("Strawberry", 0.00),
    MINT("Mint", 0.00);

    private final String flavor;
    private final double price;

    FlavorChoice(String flavor, double price) {
        this.flavor = flavor;
        this.price = price;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getPrice() {
        return price;
    }
}
