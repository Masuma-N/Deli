package org.example;

public enum Chips {

    REGULAR("Regular", 1.50);

    private final String chipType;
    private final double chipPrice;

    Chips(String chipType, double chipPrice) {
        this.chipType = chipType;
        this.chipPrice = chipPrice;
    }

    public String getChipType() {
        return chipType;
    }

    public double getChipPrice() {
        return chipPrice;
    }
}
