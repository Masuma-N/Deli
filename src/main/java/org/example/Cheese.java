package org.example;

public enum Cheese {
    AMERICAN("American"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    SWISS("Swiss");

    private String cheese;

    Cheese(String cheese) {
        this.cheese = cheese;
    }

    public String getCheese() {
        return cheese;
    }
}
