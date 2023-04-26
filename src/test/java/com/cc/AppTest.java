package com.cc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;
import com.cc.domain.Customer;
import com.cc.domain.Food;
import com.cc.domain.FoodMenu;
import com.cc.domain.ShoppingBag;
import com.cc.domain.TakeOutSimulator;

public class AppTest {

    FoodMenu foodMenu = new FoodMenu();

    @Test
    public void foodmenu() {

        // Today's Menu Option
        // 1. Enjoy Arepa: Reina Pepiada, Cost: $5
        // 2. Enjoy Arepa: Pelua, Cost: $3
        // 3. Enjoy Arepa: Domino, Cost: $2

        assertEquals("get the Food object at index in menu and return it",
                Food.class, foodMenu.getFood(3).getClass());

        assertNull("return null if index is out of bounds",
                foodMenu.getFood(0));

        assertNull("return null if index is out of bounds",
                foodMenu.getFood(-0));

        assertNull("return null if index is out of bounds",
                foodMenu.getFood(4));

        assertEquals("return the Food object in menu with the lowest price",
                2, foodMenu.getLowestCostFood().getPrice(), 0);

        assertEquals("return the Food object in menu with the lowest price",
                2, foodMenu.getLowestCostFood().getPrice(), 0);
    }

    ShoppingBag<Food> shoppingBag = new ShoppingBag<>();

    @Test
    public void shoppingBag() {

        // Add food to shopingBag list
        // "Arepa", "Reina Pepiada", price: 5
        shoppingBag.addItem(foodMenu.getFood(1));
        shoppingBag.addItem(foodMenu.getFood(1));
        // "Arepa", "Pelua", price: 3
        shoppingBag.addItem(foodMenu.getFood(2));
        // "Arepa", "Domino", price: 2.
        shoppingBag.addItem(foodMenu.getFood(3));

        assertEquals(
                " Sum up all of the total prices to find the grand total of everything in shoppingBag. Return the grand total.",
                15, shoppingBag.getTotalPrice());

    }

    public Scanner input(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        return new Scanner(System.in);

    }

    public TakeOutSimulator takeOutSimulator(String customerMoney, String selection) {
        TakeOutSimulator takeOutSimulator = new TakeOutSimulator(
                new Customer("Simon Diaz", Integer.parseInt(customerMoney)), input(selection));
        return takeOutSimulator;
    }

    public void shouldSimulate(){
    }

    @Test
    public void takeOutSimulatorTest() {

        assertEquals(
                "returns true if selection(input) is 1 and the customer has enough money to buy the lowest cost food item",
                true, takeOutSimulator("4", "1").shouldSimulate());

        assertEquals("returns false if selection(input) is 0 or the customer does not have enough money",
                false, takeOutSimulator("0", "0").shouldSimulate());

        assertEquals("returns false if selection(input) is 0 or the customer does not have enough money",
                false, takeOutSimulator("0", "0").shouldSimulate());
                        
        // Today's Menu Option
        // 1. Enjoy Arepa: Reina Pepiada, Cost: $5
        // 2. Enjoy Arepa: Pelua, Cost: $3
        // 3. Enjoy Arepa: Domino, Cost: $2

        assertEquals("gets the food item using selection from menu and returns it if it’s not null",
                "Pelua", takeOutSimulator("10", "2").getMenuSelection().getDescription());

        assertEquals("returns true if selection is 1",
                true, takeOutSimulator("10", "1").isStillOrderingFood());

        assertEquals("returns false if selection is 0",
                false, takeOutSimulator("10", "0").isStillOrderingFood());

    }

}
