package org.example;

public enum SandwichSize {
    FOURINCH("4\""),
    EIGHTINCH("8\""),
    TWELVEINCH("12\"");

    private final String sandwichSize;

    SandwichSize(String sandwichSize) {
        this.sandwichSize = sandwichSize;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
