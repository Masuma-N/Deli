# DELI-cious Sandwiches Order System

This Java class represents a user interface for a Deli ordering system. It allows users to place orders for custom and
signature sandwiches, drinks, and chips. The class provides a menu-based interface for interacting with the system. 

## Table of Contents
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Setup Instructions](#setup-instructions)
* [Key Features](#key-features)
* [User Guide](#user-guide)
  * [Screens](#screens)
  * [Additional Action](#additional-action)
* [Additional Details](#additional-details)
  * [Class Members](#class-members)
  * [Known Bugs](#known-bugs)
  * [Interesting Piece of Code](#interesting-piece-of-code)
* [Acknowledgements](#acknowlegments)
* [Authors](#authors)

## Getting Started
### Prerequisites
* Java Development Kit (JDK) Corretto 19
* IntelliJ IDE or a command prompt, command line interface, IDE with Correto 19 
### Setup Instructions
1. Clone the repository or download the file.
2. Open a command-line interface and run the main java file.
## Key Features
* Creates a new order
* Adds custom sandwiches with options for size, bread, meat, cheese, sauce, and toppings
* Adds signature sandwiches
* Adds drinks with options for flavors
* Adds chips with options for flavors
* Views order summary
* Checkout and finalizes the order

## User Guide
The user can interact with the command line interface to place an order.The application will prompt the user to select
the desired items from the available menu options. If the user selects the option to start a new order, the order menu
will be displayed, and they can add custom sandwiches, signature sandwiches, drinks, and chips to the order. Once the
order is complete, the total cost will be calculated and displayed.
### Screens
There are six (6) screens in this application:
* Home Screen
* Order Screen
* Custom Sandwich Order Screen
* Signature Sandwich Order Screen
* Drink Order Screen
* Chips Order Screen

####  [ Home Screen ]
![Home Screen Image](/Documents/README_Images/HomeScreen.png)

    From here:
    You can either type, 1, to select "New Order" from the menu 
    and this will start a new order.
    OR type, 2, to exit the program.
####  [ Order Screen ]
![Order Screen Image](/Documents/README_Images/OrderScreen.png)

    From here:
    You can either type, 1, to add a custom sandwich to your order.
    Type, 2, to add a DELI-cious signature sandwich to your order.
    Type, 3, to add a drink to your order.
    Type, 4, to add a bag of chips to your order.
    Type, 5, to checkout your order.
    Type, 0, to cancel the order and exit the program.

#### [Custom Sandwich Order Screen]
![Custom Sandwich Order Screen Image](/Documents/README_Images/CustomSandwichScreen1.png)
![Custom Sandwich Order Screen Image](/Documents/README_Images/CustomSandwichScreen2.png)
    
    From here:
    You can create your custom sandwich by following the prompts for selecting the size, bread, 
    meat, cheese, sauce, toppings, and side.

    Make sure to follow the prompts closely! 

    For the prompts that say, "Please enter a bracket option (number only), you may only enter
    1 option.

    If the prompt also has "Separate by comma, if multiple (e.g. 1,2,3)", you may enter multiple
    options. So long as its comma-separated (No Spaces).

    Once done, a summary of the custom order will show your selections and add to your order.

#### [Signature Sandwich Order Screen]
![Signature Sandwich Order Screen Image](/Documents/README_Images/SignatureSandwichScreen.png)

    From here:
    You can add a Signature Sandwich by choosing from a list of predefined signature sandwiches.
    
    Make sure to follow the prompt closely. 

    *Note: Only one sandwich may be added at a time!

#### [Drink Order Screen]
![Drink Order Screen Image](/Documents/README_Images/DrinkScreen.png)

    From here: 
    You can add a drink to the order by following the prompts for selecting the size.
    and the flavor.

    *Note: Only one size and flavor may be added at a time!

#### [Chips Order Screen]
![Chips Order Screen Image](/Documents/README_Images/ChipsScreen.png)

    From here: 
    You can add a bag of chips to the order by following the prompts for 
    selecting the flavor type.

    *Note: Only one chip flavor may be added at a time!

### Additional Action
* Checkout
  *  Receipt creation

#### [Checkout]
![Checkout Order Screen Image](/Documents/README_Images/Checkout.png)

    From here:
    When you enter, 5, to checkout your order, the command line will show the receipt 
    saved successfully.

#### [Receipt File]
![Checkout Order Screen Image](/Documents/README_Images/ReceiptCopy.png)

    Over Here: You can see the receipt.csv saved in the Receipts 
    directory under the date and time it was created.


## Additional Details
### Class Members:
#### Constants
* 'SANDWICH_SIZE' : An array of available sandwich sizes in inches( 4' , 8', 12').
* 'SANDWICH_SIZE_PRICES' : An array of prices for each sandwich size.
* 'scanner': A 'Scanner' object for user input.

#### Fields
* 'orderEntries' : A 'List' to store order entries.
* 'totalPrice' : A 'double' to store the total price of the order.

#### Methods
* 'display()' : Displays the main menu.
*  'displayHome()' : Displays the Deli logo and the main menu options.
*  'startOrder()' : Displays the order menu and handles the user input.
*  'addSandwich()' : Prompts the user to customize a sandwich and adds it to the order.
*  'getMeatPriceForSize(price, sandwichSize)': Calculates the cost of meat based on the sandwich size.
*  Other helper methods to handle different order options, such as adding signature sandwiches, drinks, chips,
* and handling invalid input.
### Known Bugs:
    !!! There may be error handling issues with invalid inputs that will 
    exit or crash the program.
### Interesting Piece of Code:
#### - ASCII DELI-cious Sandwiches Logo
![ASCII Logo Image](/Documents/README_Images/DELIciousSandwichesASCIILogo.png)
#### - FileWriter
![FileWrite Image](/Documents/README_Images/FileWriterSnip.png)
#### - Order Summary 
![Order Summary Image](/Documents/README_Images/OrderSummarySnip.png)
##### - Result: Printed Order Summary

![Order Summary Printed Image](/Documents/README_Images/OrderSummaryPrintedSnip.png)
## Acknowledgements
We would like to acknowledge the following individuals for their contributions and support to this project:
* **Paul Kimball**
* **LC Section 10**
* **YearUp & Pluralsight Staff**

## Authors
The DELI-cious Sandwiches Order System is developed and maintained by the following team members:
* ### Karma Gurung 
    Email: kgurung@appdev.yearup.org
* ### Tshering Dolma 
    Email: tdolma@appdev.yearup.org
* ### Masuma Talukder
    Email: mtalukder@appdev.yearup.org
* ### Maricarmen Miron 
    Email: mmiron@appdev.yearup.org