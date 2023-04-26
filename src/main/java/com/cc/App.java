package com.cc;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sql.rowset.JoinRowSet;

import com.cc.domain.Customer;
import com.cc.domain.TakeOutSimulator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner input = new Scanner(System.in);
        String customerName;
        Integer money = null;
        System.out.println("***TAKEOUT APP***");

        System.out.print("Enter your name: ");
        customerName = input.nextLine();

        System.out.print("Enter your money: ");
        boolean isString = true;
        
        do{
                try {
                    money = Integer.parseInt(input.nextLine());
                    isString = false;
                } catch (NumberFormatException e) {
                    System.err.println(" is not a valid input, Try Again!");
                    System.out.println("Input needs to be an 'int' type");            }
        }while(isString);

        Customer customer = new Customer(customerName, money);
        TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, input);
        takeOutSimulator.startTakeOutSimulator();
    }
}
