package org.example;

public enum Drink {
    SMALL("Small", 2.00),
    MEDIUM("Medium", 2.50),
    LARGE("Large", 3.00);

    private final String drinkSize;
    private final double drinkPrice;

    Drink(String drinkSize, double drinkPrice) {
        this.drinkSize = drinkSize;
        this.drinkPrice = drinkPrice;
    }

    public String getDrinkSize() {
        return drinkSize;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }
}
