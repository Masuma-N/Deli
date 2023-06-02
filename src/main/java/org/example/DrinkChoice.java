package org.example;

public enum DrinkChoice {

    SMALL("Small", 2.00),
    MEDIUM("Medium", 2.50),
    LARGE("Large", 3.00);

    private final String size;
    private final double price;

    DrinkChoice(String size, double price) {
        this.size = size;
        this.price = price;
    }

    public String getSize() {

        return size;
    }

    public double getPrice() {

        return price;
    }
}
