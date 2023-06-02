package org.example;

public enum MeatChoice {
    STEAK("Steak", 1.00),
    HAM("Ham", 1.00),
    SALAMI("Salami", 1.00),
    ROAST_BEEF("Roast Beef", 1.00),
    CHICKEN("Chicken", 1.00),
    BACON("Bacon", 1.00);

    private final String displayName;
    private final double price;

    MeatChoice(String displayName, double price) {
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




