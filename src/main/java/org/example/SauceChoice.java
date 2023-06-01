package org.example;

public enum SauceChoice {
    MAYO("Mayo", 0),
    MUSTARD("Mustard", 0),
    KETCHUP("Ketchup", 0),
    RANCH("Ranch", 0),
    THOUSANDISLANDS("Thousand Islands", 0),
    VINAGRETTE("Vinaigrette", 0);

    private String sauce;
    private double price;

    SauceChoice(String sauce, double price) {
        this.sauce = sauce;
        this.price = price;
    }

    public String getSauce() {
        return sauce;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}





