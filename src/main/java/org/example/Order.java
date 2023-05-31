package org.example;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private LocalDateTime orderDateTime;
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List <Chips> chips;

    public Order(LocalDateTime orderDateTime, List<Sandwich> sandwiches, List<Drink> drinks, List<Chips> chips) {
        this.orderDateTime = orderDateTime;
        this.sandwiches = sandwiches;
        this.drinks = drinks;
        this.chips = chips;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }

    public void newSandwich (Sandwich sandwich){
        sandwiches.add(sandwich);
    }

    public void newDrink (Drink drink){
        drinks.add(drink);
    }

    public void newChips (Chips chips) {
        this.chips.add(chips);
    }

    public double calculateTotalCost() {
        double totalCost = 0;


        return totalCost;
    }
}
