package org.example;


    public enum SauceChoice {
        MAYO("Mayo", 0),
        MUSTARD("Mustard", 0),
        KETCHUP("Ketchup", 0),
        RANCH("Ranch", 0),
        THOUSAND_ISLANDS("Thousand Islands", 0),
        VINAIGRETTE("Vinaigrette", 0);

        private final String sauce;
        private final double price;

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





