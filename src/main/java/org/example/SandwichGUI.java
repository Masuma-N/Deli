package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//**sandwichGUI class extends JFrame class to create a window for gui**
public class SandwichGUI extends JFrame {
    //declares a private instance variable named sandwich of type Sandwiches.
    // It is used to store the sandwich object that represents the user's selected options.
    private Sandwiches sandwich;

    // declares a private instance variable named meatCheckboxes as an array of JCheckBox objects.
    // It is used to store the checkboxes for the meat options.
    private JCheckBox[] meatCheckboxes;

    //stores checkbox for cheese
    private JCheckBox[] cheeseCheckboxes;

    //stores checkbox for sauce
    private JCheckBox[] sauceCheckboxes;


    public SandwichGUI() {
        //** creates a new instance of the Sandwiches class and assigns it to the sandwich variable.**
        sandwich = new Sandwiches();


        //** Initialize the frame and set layout for grid **
        setTitle("Sandwich GUI");
        //sets size of frame
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // **Create a glowing title label**
        JLabel titleLabel = new JLabel("Deli-cious Sandwiches");
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);

        // **Create a timer to toggle the glow effect**

        //creates a new Timer object with a delay of 1000 milliseconds (1 second).
        // actionPerformed method, which will be called when the timer triggers an action event.
        Timer timer = new Timer(1000, new ActionListener() {
            private boolean glowOn = true;


        //implementation of the actionPerformed method. It takes an ActionEvent object as a parameter.
        //**overridden actionPerformed method changes the foreground color of the titleLabel component based on the glowOn boolean variable.**
        // If glowOn is true, the color is set to Color.PINK, and if glowOn is false, the color is set to Color.WHITE
            @Override
            public void actionPerformed(ActionEvent e) {
                if (glowOn) {//sets the color to pink
                    titleLabel.setForeground(Color.PINK);
                    glowOn = false;
                } else {//sets the color to white (glowing effect)
                    titleLabel.setForeground(Color.WHITE);
                    glowOn = true;
                }
            }
        });
        timer.start();

        // **Add the glowing title label to the frame**
        JPanel titlePanel = new JPanel();
        //**sets background for title**
        titlePanel.setBackground(Color.BLACK);
        //adds the titlelabel
        titlePanel.add(titleLabel);
        add(titlePanel);

        // **Create and add checkboxes for sandwich size**
        JPanel sizePanel = new JPanel();
        JLabel sizeLabel = new JLabel("Select sandwich size: ");
        //created to hold components related to selecting the sandwich size. (container for the sizeLabel)
        sizePanel.add(sizeLabel);
        //**stores choices into array**
        String[] sizeOptions = {"4-inch", "8-inch", "12-inch"};

        //**creates a JComboBox component named sizeComboBox and initializes it with an array of size options**
        JComboBox<String> sizeComboBox = new JComboBox<>(sizeOptions);

        //**ActionListener listens for action events on the sizeComboBox, which occurs when the user selects a different item from the combo box.**
        //**When the user selects a size from the combo box, the corresponding action event triggers the actionperformed method and the size is updated**
        sizeComboBox.addActionListener(new ActionListener() {

            //**actionPerformed method of the ActionListener, the selected size is retrieved from the sizeComboBox using the getSelectedItem method.**
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSize = (String) sizeComboBox.getSelectedItem();
                //selected size is passed to the setSize method
                sandwich.setSize(selectedSize);
            }
        });
        //sizePanel.add(sizeComboBox) adds the sizeComboBox component to the sizePanel panel.
        sizePanel.add(sizeComboBox);
        add(sizePanel);

        // **Create and add checkboxes for meats**
        JPanel meatPanel = new JPanel();
        JLabel meatLabel = new JLabel("Select meats: ");
        meatPanel.add(meatLabel);

        //stores meat options into array
        String[] meatOptions = {"Salami", "Roast Beef", "Chicken", "Bacon", "Extra Meat"};
        //**In each iteration, a new JCheckBox is created with the text value from the meatOptions array at index i. **
        // The newly created checkbox is assigned to the corresponding index of the meatCheckboxes array.
        meatCheckboxes = new JCheckBox[meatOptions.length];
        for (int i = 0; i < meatOptions.length; i++) {
            meatCheckboxes[i] = new JCheckBox(meatOptions[i]);
            int finalI = i;

         // ** ActionListener is added to each JCheckBox in the meatCheckboxes array.**
            //  The ActionListener is implemented as an anonymous inner class.
         //   actionPerformed method of the ActionListener, the selected meat option is retrieved from the JCheckBox using meatCheckboxes[finalI].getText(), where finalI represents the index of the current checkbox in the loop.
            meatCheckboxes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedMeat = meatCheckboxes[finalI].getText();
                    //**If the checkbox is selected (meatCheckboxes[finalI].isSelected()), the selected meat option is added to the sandwich object using sandwich.addMeat(selectedMeat). **
                    // **Otherwise, if the checkbox is deselected, the selected meat option is removed from the sandwich object using sandwich.removeMeat(selectedMeat).**
                    if (meatCheckboxes[finalI].isSelected()) {
                        sandwich.addMeat(selectedMeat);
                    } else {
                        sandwich.removeMeat(selectedMeat);
                    }
                }
            });
            meatPanel.add(meatCheckboxes[i]);
        }
        //meatPanel is added as a component to the main JFrame,
        // allowing it to be displayed as part of the graphical user interface.
        add(meatPanel);

        // Create and add checkboxes for cheeses
        JPanel cheesePanel = new JPanel();
        JLabel cheeseLabel = new JLabel("Select cheeses: ");
        cheesePanel.add(cheeseLabel);

        String[] cheeseOptions = {"American", "Provolone", "Cheddar", "Swiss", "Extra cheese"};
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

        String[] toppingsOptions = {"Lettuce", "Tomatoes", "Onions", "Guac", "Jalapenos", "Pickles", "Mushrooms", "Cucumbers", "Peppers"};
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

        String[] sauceOptions = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};
        sauceCheckboxes = new JCheckBox[sauceOptions.length];
        for (int i = 0; i < sauceOptions.length; i++) {
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


