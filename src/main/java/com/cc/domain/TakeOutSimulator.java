package com.cc.domain;

import java.util.Scanner;

public class TakeOutSimulator {

    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, Scanner input) {
        this.customer = customer;
        this.input = input;
        this.menu = (new FoodMenu());
    }

    private <T> T getOutputOnIntInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever) {

        do {
            System.out.println(userInputPrompt);

            if (input.hasNextInt()) {
                try {
                    return intUserInputRetriever.produceOutputOnIntUserInput(input.nextInt());
                } catch (IllegalArgumentException e) {
                    System.out.println(input.nextInt() + " is not a valid input, Try Again!");
                }
            } else if (!input.hasNextInt()) {
                System.out.println("Input needs to be an 'int' type");
                input.nextLine();
            }

        } while (true);

    }

    public boolean shouldSimulate() {

        String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program:";

        IntUserInputRetriever<Boolean> intUserInputRetriever = ((selection) -> {
            if (selection == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice()) {
                return true;
            } else if (selection == 0 || customer.getMoney() <= 0) {
                if (selection == 0) {
                    System.out.println("Ending simulation...");
                } else {
                    System.out.println("You don't have enough money to continue shopping :( - ending simulation...");
                }
                return false;
            } else {
                System.out.println("Selection is not 0 or 1.");
                throw new IllegalArgumentException();
            }
        });

        return getOutputOnIntInput(userPrompt, intUserInputRetriever);

    }

    public Food getMenuSelection() {

        System.out.println("Today's Menu Option");
        System.out.println(menu.toString());
        System.out.println("You have " + customer.getMoney() + " left to spend");
        String userPrompt = "Choose a menu item!: ";

        IntUserInputRetriever<Food> intUserInputRetriever = ((selection) -> {
            Food food = null;
            if (menu.getFood(selection) != null) {
                food = menu.getFood(selection);
                return food;
            } else {
                System.out.println("Menu is null");
                throw new IllegalArgumentException();
            }
        });

        return getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public boolean isStillOrderingFood() {

        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT";

        IntUserInputRetriever<Boolean> intUserInputRetriever = ((selection) -> {
            
            if (selection == 1) {
                return true;
            } else if (selection == 0) {
                return false;
            } else {
                System.out.println("Selection is not 0 or 1.");
                throw new IllegalArgumentException();
            }

        });

        return getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public void takeOutPrompt() {
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = customer.getMoney();
        boolean stillOrder = true;

        do {

            System.out.println("You have " + customerMoneyLeft + " left to spend");
            Food food = getMenuSelection();

            if (customerMoneyLeft >= food.getPrice()) {
                customerMoneyLeft -= food.getPrice();
                shoppingBag.addItem(food);
            }
            if (customerMoneyLeft < food.getPrice()) {
                System.out.println("You have " + customerMoneyLeft + " left to spend");
                System.out.println("Oops! Looks like you don't have enough for that. Choose another item or checkout.");
                stillOrder = isStillOrderingFood();
            }

        } while (stillOrder);

        checkoutCustomer(shoppingBag);

    }

    public void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
        if (shoppingBag.getTotalPrice() > 0) {
            System.out.println("Processing payment...");
            customer.setMoney(customer.getMoney() - shoppingBag.getTotalPrice());
            System.out.println("Your remaining money: " + customer.getMoney());
            System.out.println("Thank you and enjoy your food!");
        } else {
            System.out.println("Ending simulation...");
        }
    }

    public void startTakeOutSimulator() {
        System.out.println("Hello " + customer.getName() + "welcome to my restaurant!");

        do {
            if (shouldSimulate()) {
                takeOutPrompt();

            } else {
                System.exit(0);
            }

        } while (true);
    }

}
