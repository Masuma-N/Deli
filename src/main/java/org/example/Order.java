package org.example;

public class Order {
    private static double sandwichBasePricesmall = 5.50;
    private static double sandwichBasePricemedium = 7.00;
    private static double sandwichBasePricelarge = 8.50;
    private static double meatBasePricesmall = 1.00;
    private static double meatBasePricemedium = 2.00;
    private static double meatBasePricelarge = 3.00;
    private static double extraMeatPricesmall = 0.50;
    private static double extraMeatPricemedium = 1.00;
    private static double extraMeatPricelarge = 1.50;

    private static double cheeseBasePricesmall = 0.75;
    private static double cheeseBasePricemedium = 1.50;
    private static double cheeseBasePricelarge = 2.25;
    private static double extraCheesePrice= 0;


    public static double calculateSandwichPrice(String size, List<String> meats, boolean extraMeat, List<String> cheeses, boolean extraCheese) {
        double totalPrice = 0.0;

        switch (size) {
            case "4\"":
                totalPrice +=  sandwichBasePricesmall;
                if (extraMeat) {
                    totalPrice += extraMeatPricesmall ;
                } else {
                    totalPrice += meatBasePricesmall ;
                }
                if (extraCheese) {
                    extraCheesePrice= 0.30;
                    totalPrice += extraCheesePrice;
                } else {
                    totalPrice += cheeseBasePricesmall;
                }
                break;
            case "8\"":
                totalPrice += sandwichBasePricemedium;
                if (extraMeat) {
                    totalPrice += extraMeatPricemedium ;
                } else {
                    totalPrice += meatBasePricemedium;
                }
                if (extraCheese) {
                    extraCheesePrice= 0.60;
                    totalPrice += extraCheesePrice;
                } else {
                    totalPrice += cheeseBasePricemedium;
                }
                break;
            case "12\"":
                totalPrice += sandwichBasePricelarge;
                if (extraMeat) {
                    totalPrice += extraMeatPricelarge;
                } else {
                    totalPrice += meatBasePricelarge;
                }
                if (extraCheese) {
                    extraCheesePrice=0.90;
                    totalPrice += extraCheesePrice;
                } else {
                    totalPrice += cheeseBasePricelarge;
                }
                break;
            default:
                System.out.println("Invalid sandwich size. Cancelling sandwich addition.");
                return 0.0;
        }

        return totalPrice;


    }




}
