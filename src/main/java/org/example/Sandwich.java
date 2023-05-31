package org.example;

import java.util.List;

public class Sandwich {
    private SandwichSize sandwichSize;
    private Bread breadType;
    private List<Meat> meatType;
    private List<Cheese> cheeseType;
    private List<RegularToppings> toppingType;
    private List<Sauce> sauceType;
    private boolean isToasted;
    private boolean hasExtraMeat;
    private boolean hasExtraCheese;

    public Sandwich(SandwichSize sandwichSize, Bread breadType, List<Meat> meatType,
                    List<Cheese> cheeseType, List<RegularToppings> toppingType, List<Sauce> sauceType,
                    boolean isToasted, boolean hasExtraMeat, boolean hasExtraCheese) {
        this.sandwichSize = sandwichSize;
        this.breadType = breadType;
        this.meatType = meatType;
        this.cheeseType = cheeseType;
        this.toppingType = toppingType;
        this.sauceType = sauceType;
        this.isToasted = isToasted;
        this.hasExtraMeat = hasExtraMeat;
        this.hasExtraCheese = hasExtraCheese;
    }

    public SandwichSize getSandwichSize() {
        return sandwichSize;
    }

    public Bread getBreadType() {
        return breadType;
    }

    public List<Meat> getMeatType() {
        return meatType;
    }

    public List<Cheese> getCheeseType() {
        return cheeseType;
    }

    public List<RegularToppings> getToppingType() {
        return toppingType;
    }

    public List<Sauce> getSauceType() {
        return sauceType;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public boolean isHasExtraMeat() {
        return hasExtraMeat;
    }

    public boolean isHasExtraCheese() {
        return hasExtraCheese;
    }


}
