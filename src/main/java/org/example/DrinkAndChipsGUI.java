package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrinkAndChipsGUI extends JFrame {
    private Sandwiches sandwich;

    public DrinkAndChipsGUI(Sandwiches sandwich) {
        this.sandwich = sandwich;

        // **Initialize the frame**
        setTitle("Drink and Chips GUI");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // **Create and add checkboxes for drink size**
        JPanel sizePanel = new JPanel();
        JLabel sizeLabel = new JLabel("Select drink size: ");
        sizePanel.add(sizeLabel);

        String[] sizeOptions = {"Small", "Medium", "Large"};
        JComboBox<String> sizeComboBox = new JComboBox<>(sizeOptions);
        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSize = (String) sizeComboBox.getSelectedItem();
                sandwich.setSize(selectedSize);
            }
        });
        sizePanel.add(sizeComboBox);
        add(sizePanel);

        //** Create and add checkboxes for drinks**
        JPanel drinkPanel = new JPanel();
        JLabel drinkLabel = new JLabel("Select drinks: ");
        drinkPanel.add(drinkLabel);

        String[] drinkOptions = {"Chocolate", "Vanilla", "Strawberry", "Mint"/*,"Small","Medium","Large"*/};
        JCheckBox[] drinkCheckboxes = new JCheckBox[drinkOptions.length];
        for (int i = 0; i < drinkOptions.length; i++) {
            drinkCheckboxes[i] = new JCheckBox(drinkOptions[i]);
            int finalI = i;
            drinkCheckboxes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedDrink = drinkCheckboxes[finalI].getText();
                    if (drinkCheckboxes[finalI].isSelected()) {
                        sandwich.addDrink(selectedDrink);
                    } else {
                        sandwich.removeDrink(selectedDrink);
                    }
                }
            });
            drinkPanel.add(drinkCheckboxes[i]);
        }
        add(drinkPanel);

        // **Create and add checkboxes for chips**
        JPanel chipsPanel = new JPanel();
        JLabel chipsLabel = new JLabel("Select chips: ");
        chipsPanel.add(chipsLabel);

        String[] chipsOptions = {"Lays", "Doritos", "Pringles", "Cheetos", "Ruffles"};
        JCheckBox[] chipsCheckboxes = new JCheckBox[chipsOptions.length];
        for (int i = 0; i < chipsOptions.length; i++) {
            chipsCheckboxes[i] = new JCheckBox(chipsOptions[i]);
            int finalI = i;
            chipsCheckboxes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedChips = chipsCheckboxes[finalI].getText();
                    if (chipsCheckboxes[finalI].isSelected()) {
                        sandwich.addChips(selectedChips);
                    } else {
                        sandwich.removeChips(selectedChips);
                    }
                }
            });
            chipsPanel.add(chipsCheckboxes[i]);
        }
        add(chipsPanel);

        //** Create and add the order button**
        JPanel buttonPanel = new JPanel();
        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions for placing the order
                // displays a confirmation message
                JOptionPane.showMessageDialog(DrinkAndChipsGUI.this, "Order placed successfully!");

                // Close the current frame
                dispose();
            }
        });
        buttonPanel.add(orderButton);
        add(buttonPanel);

        // Display the frame
        setVisible(true);
    }
}
