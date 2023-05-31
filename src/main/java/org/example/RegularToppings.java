package org.example;

public enum RegularToppings {
    LETTUCE("Lettuce"),
    PEPPERS("Peppers"),
    ONIONS("Onions"),
    TOMATOES("Tomatoes"),
    JALAPENOS("Jalapenos"),
    CUCUMBER("Cucumbers"),
    PICKLES("Pickles"),
    GUACAMOLES("Guacamole"),
    MUSHROOMS("Mushrooms");

    private String regularToppings;

    RegularToppings(String regularToppings) {
        this.regularToppings = regularToppings;
    }

    public String getRegularToppings() {
        return regularToppings;
    }
}
