package org.example;

public enum RegularToppingChoice {

        LETTUCE("Lettuce", 0.0),
        PEPPERS("Peppers", 0.0),
        ONIONS("Onions", 0.0),
        TOMATOES("Tomatoes", 0.0),
        JALAPENOS("Jalapenos", 0.0),
        CUCUMBERS("Cucumbers", 0.0),
        PICKLES("Pickles", 0.0),
        GUACAMOLE("Guacamole", 0.0),
        MUSHROOMS("Mushrooms", 0.0);

        private final String regulartoppings;
        private final double price;

        RegularToppingChoice(String regulartoppings, double price) {
                this.regulartoppings = regulartoppings;
                this.price = price;
        }

        public String getRegulartoppings() {
                return regulartoppings;
        }

        public double getPrice() {
                return price;
        }

        @Override
        public String toString() {
                return super.toString();
        }
}
