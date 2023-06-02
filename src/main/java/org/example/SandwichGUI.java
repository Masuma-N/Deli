package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





    public class SandwichGUI extends JFrame {
        private Sandwiches sandwich;

        private JCheckBox[] meatCheckboxes;
        private JCheckBox[] cheeseCheckboxes;
        private JCheckBox[] sauceCheckboxes;




        public SandwichGUI() {
            // Create a new sandwich object
            sandwich = new Sandwiches();



            // Initialize the frame
            setTitle("Sandwich GUI");
            setSize(700, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(5, 2));

            // Create a glowing title label
            JLabel titleLabel = new JLabel("Deli-cious Sandwiches");
            Font titleFont = new Font("Arial", Font.BOLD, 24);
            titleLabel.setFont(titleFont);
            titleLabel.setForeground(Color.WHITE);

            // Create a timer to toggle the glow effect
            Timer timer = new Timer(1000, new ActionListener() {
                private boolean glowOn = true;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (glowOn) {
                        titleLabel.setForeground(Color.PINK);
                        glowOn = false;
                    } else {
                        titleLabel.setForeground(Color.WHITE);
                        glowOn = true;
                    }
                }
            });
            timer.start();

            // Add the glowing title label to the frame
            JPanel titlePanel = new JPanel();
            //sets background for title
            titlePanel.setBackground(Color.BLACK);
            titlePanel.add(titleLabel);
            add(titlePanel);

            // Create and add checkboxes for sandwich size
            JPanel sizePanel = new JPanel();
            JLabel sizeLabel = new JLabel("Select sandwich size: ");

            sizePanel.add(sizeLabel);

            String[] sizeOptions = {"4-inch", "8-inch", "12-inch"};
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

            // Create and add checkboxes for meats
            JPanel meatPanel = new JPanel();
            JLabel meatLabel = new JLabel("Select meats: ");
            meatPanel.add(meatLabel);


            String[] meatOptions = {"Salami", "Roast Beef", "Chicken", "Bacon","Extra Meat"};
            meatCheckboxes = new JCheckBox[meatOptions.length];
            for (int i = 0; i < meatOptions.length; i++) {
                meatCheckboxes[i] = new JCheckBox(meatOptions[i]);
                int finalI = i;
                meatCheckboxes[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedMeat = meatCheckboxes[finalI].getText();
                        if (meatCheckboxes[finalI].isSelected()) {
                            sandwich.addMeat(selectedMeat);
                        } else {
                            sandwich.removeMeat(selectedMeat);
                        }
                    }
                });
                meatPanel.add(meatCheckboxes[i]);
            }
            add(meatPanel);

            // Create and add checkboxes for cheeses
            JPanel cheesePanel = new JPanel();
            JLabel cheeseLabel = new JLabel("Select cheeses: ");
            cheesePanel.add(cheeseLabel);

            String[] cheeseOptions = {"American", "Provolone", "Cheddar", "Swiss","Extra cheese"};
            cheeseCheckboxes = new JCheckBox[cheeseOptions.length];
            for (int i = 0; i < cheeseOptions.length; i++) {
                cheeseCheckboxes[i] = new JCheckBox(cheeseOptions[i]);
                int finalI = i;
                cheeseCheckboxes[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedCheese = cheeseCheckboxes[finalI].getText();
                        if (cheeseCheckboxes[finalI].isSelected()) {
                            sandwich.addCheese(selectedCheese);
                        } else {
                            sandwich.removeCheese(selectedCheese);
                        }
                    }
                });
                cheesePanel.add(cheeseCheckboxes[i]);
            }
            add(cheesePanel);
            // Create and add checkboxes for toppings
            JPanel toppingsPanel = new JPanel();
            JLabel toppingsLabel = new JLabel("Select toppings: ");
            toppingsPanel.add(toppingsLabel);

            String[] toppingsOptions = {"Lettuce", "Tomatoes", "Onions", "Guac","Jalapenos","Pickles","Mushrooms","Cucumbers","Peppers"};
            JCheckBox[] toppingsCheckboxes = new JCheckBox[toppingsOptions.length];
            for (int i = 0; i < toppingsOptions.length; i++) {
                toppingsCheckboxes[i] = new JCheckBox(toppingsOptions[i]);
                int finalI = i;
                toppingsCheckboxes[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedTopping = toppingsCheckboxes[finalI].getText();
                        if (toppingsCheckboxes[finalI].isSelected()) {
                            sandwich.addTopping(selectedTopping);
                        } else {
                            sandwich.removeTopping(selectedTopping);
                        }
                    }
                });
                toppingsPanel.add(toppingsCheckboxes[i]);
            }
            add(toppingsPanel);

            // Create and add checkboxes for sauces
            JPanel saucePanel = new JPanel();
            JLabel sauceLabel = new JLabel("Select sauces: ");
            saucePanel.add(sauceLabel);

            String[] sauceOptions = {"Mayo", "Mustard", "Ketchup", "Ranch","Thousand Islands","Vinaigrette"};
            sauceCheckboxes = new JCheckBox[sauceOptions.length];
            for(int i = 0; i < sauceOptions.length; i++) {
                sauceCheckboxes[i] = new JCheckBox(sauceOptions[i]);
                int finalI = i;
                sauceCheckboxes[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedSauce = sauceCheckboxes[finalI].getText();
                        if (sauceCheckboxes[finalI].isSelected()) {
                            sandwich.addSauce(selectedSauce);
                        } else {
                            sandwich.removeSauce(selectedSauce);
                        }
                    }
                });
                saucePanel.add(sauceCheckboxes[i]);
            }
            add(saucePanel);

            // Display the frame
            setVisible(true);
            // Create and add the next button
            JPanel buttonPanel = new JPanel();
            JButton nextButton = new JButton("Next");
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the next page for drink and chips options
                    dispose(); // Close the current frame

                    // Create and show the new frame for drink and chips options
                    DrinkAndChipsGUI drinkAndChipsGUI = new DrinkAndChipsGUI(sandwich);
                    drinkAndChipsGUI.setVisible(true);
                }
            });
            buttonPanel.add(nextButton);
            add(buttonPanel);

            // Display the frame
            setVisible(true);
        }



        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SandwichGUI();
                }
            });
        }
    }


