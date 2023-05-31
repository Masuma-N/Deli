package org.example;

public enum Meat {
    STEAK("Steak", 1.00),
    HAM("Ham", 1.00),
    SALAMI("Salami", 1.00),
    ROASTBEEF("Roast Beef", 1.00),
    CHICKEN("Chicken", 1.00),
    BACON("Bacon", 1.00);

    private final String meatName;
    private final double meatPrice;

    Meat(String meatName, double meatPrice) {
        this.meatName = meatName;
        this.meatPrice = meatPrice;
    }

    public String getMeatName() {
        return meatName;
    }

    public double getMeatPrice() {
        return meatPrice;
    }
}
