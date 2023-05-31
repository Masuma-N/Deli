package org.example;

public enum Sauce {
    MAYO("Mayo"),
    MUSTARD("Mustard"),
    KETCHUP("Ketchup"),
    RANCH("Ranch"),
    THOUSANDISLANDS("Thousand Islands"),
    VINAGRETTE("Vinaigrette");

    private String sauce;

    Sauce(String sauce) {
        this.sauce = sauce;
    }

    public String getSauce() {
        return sauce;
    }
}
