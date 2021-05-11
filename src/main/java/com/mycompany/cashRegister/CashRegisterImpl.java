package com.mycompany.cashRegister;

import com.mycompany.common.Messages;
import com.mycompany.exception.EmptyRegisterException;
import com.mycompany.exception.InvalidInputException;
import com.mycompany.exception.NoChangeException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dimpi
 */
//Implementation for Cash Regiser
public class CashRegisterImpl implements CashRegister {

    //Cash register is stored in a map with denomination as key and bills as value
    static Map<Integer, Integer> register;

    public CashRegisterImpl() {
        //instantiating the cash register map
        //When a new object of cashRegisterImpl is created, a new cash register map is formed
        register = new LinkedHashMap<>();
    }

    //display the cash resgiter in $<total> <# of 20’s> <# of 10’s> <# of 5’s> <# of 2’s> <# of 1’s> format
    @Override
    public void show() throws EmptyRegisterException {
        //If register is empty, throw an exception
        if (register.size() <= 0) {
            throw new EmptyRegisterException();
        }
        //Add the total amount in the string
        String output = "$" + totalAmount() + " ";
        //Loop through the map and add the bill in string
        for (int i : register.values()) {
            output += i + " ";
        }
        //display the cash register string on console
        System.out.println(output.trim());
    }

    //add the bills in cash register
    @Override
    public void put(String[] input) throws InvalidInputException, EmptyRegisterException {
        try {
            //if user input's less bills, throw exception
            if (input.length != 6) {
                throw new InvalidInputException();
            }
            //if the user input bill is negative, throw exception
            for (int i = 1; i <= 5; i++) {
                int bill = Integer.parseInt(input[i]);
                if (bill < 0) {
                    throw new InvalidInputException(Messages.NEGATIVE_INPUT_ERR);
                }
            }
            //the first denomination is 20
            int denomination = 20;
            //loop through the user input, and add the bill in correct denomination
            for (int i = 1; i <= 5; i++) {
                int bill = Integer.parseInt(input[i]);
                //add the bill to correct denomination in map
                if (register.containsKey(denomination)) {
                    register.put(denomination, register.get(denomination) + bill);
                } else {
                    register.put(denomination, bill);
                }
                //divide the denomination to get the next denomination
                denomination /= 2;
            }
            //display the current state of cash register
            show();
        } catch (NumberFormatException e) {
            //throw exception if user inputs non-digit
            System.out.println(Messages.INVALID_NUMBER_ERR);
        }
    }

    //remove the bills of specified denomination from teh cash register
    @Override
    public void take(String[] input) throws InvalidInputException, EmptyRegisterException {
        try {
            //if user input's less bills, throw exception
            if (input.length != 6) {
                throw new InvalidInputException();
            }

            //if cash register is empty, throw exception
            if (register.size() == 0) {
                throw new EmptyRegisterException();
            }

            //the first denomination is 20
            int denomination = 20;

            for (int i = 1; i <= 5; i++) {
                int bill = Integer.parseInt(input[i]);
                //if the user input bill is negative, throw exception
                if (bill < 0) {
                    throw new InvalidInputException(Messages.NEGATIVE_INPUT_ERR);
                }
                //if there are insufficient bills to remove, throw exception
                if (register.get(denomination) - bill < 0) {
                    throw new InvalidInputException(Messages.INSUFFICIENT_DENOMINATION_ERR + " '" + denomination + "'");
                }
                denomination /= 2;
            }

            //the first denomination is 20
            denomination = 20;

            //loop through the user input, and remove the bills from correct denomination
            for (int i = 1; i <= 5; i++) {
                int bill = Integer.parseInt(input[i]);
                if (register.containsKey(denomination)) {
                    //remove bills of correct denomination from cash register
                    register.put(denomination, register.get(denomination) - bill);
                }
                //divide the denomination to get the next denomination
                denomination /= 2;
            }
            //display the current state of cash register
            show();
        } catch (NumberFormatException e) {
            //throw exception if user inputs non-digit
            System.out.println(Messages.INVALID_NUMBER_ERR);
        }
    }

    //return the change required by the user as per denomination
    @Override
    public void change(int amount) throws InvalidInputException, NoChangeException {
        try {
            //if user input amount is negative, throw exception
            if (amount < 0) {
                throw new InvalidInputException(Messages.NEGATIVE_INPUT_ERR);
            }
            //if user input amount is greater than the amount available in cash register, throw exception
            if (amount > totalAmount()) {
                throw new NoChangeException(Messages.INSUFFICIENT_BALANCE_ERR);
            }
            //create an array that contains n times denomination, n = count (number of bills)
            List<Integer> denominations = new ArrayList<>();
            int denomination = 20;
            //this array will be used to backtrack and find the combination of denomination equal to the change amount requested by user
            for (int i : register.values()) {
                for (int j = 1; j <= i; j++) {
                    //add the denomination n times to an array for backtracking. n = count (number of bills)
                    denominations.add(denomination);
                }
                denomination /= 2;
            }
            //this is a recursive call to find the combination of denomination
            //If no change found, throw exception
            if (!calculateChange(0, amount, denominations, new ArrayList())) {
                throw new NoChangeException();
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.INVALID_NUMBER_ERR);
        }
    }

    //recursive function that would find different combinations and return true if combination can make up the amount requsted by user
    public boolean calculateChange(int start, int amount, List<Integer> denominations, List<Integer> result) {
        //if amount found, display the denomination, remove the bills from cash register and return true
        if (amount == 0) {
            Map<Integer, Integer> output = new LinkedHashMap<>();
            for (int key : register.keySet()) {
                output.put(key, 0);
            }
            //add the count of denomination in output array and decrease the count of denomiantion from cash register
            for (int i : result) {
                register.put(i, register.get(i) - 1);
                output.put(i, output.get(i) + 1);
            }

            //display the denominations that make up the change
            for (int i : output.values()) {
                System.out.print(i + " ");
            }
            System.out.println("");
            return true;
        }
        //if amount is negative, means change not found, return false
        if (amount < 0) {
            return false;
        }

        //loop through the array that contains all denominations, add number to another array for creating combinations
        for (int i = start; i < denominations.size(); i++) {
            result.add(denominations.get(i));
            //recursively call the function by decreasing the amount
            if (calculateChange(i + 1, amount - denominations.get(i), denominations, result)) {
                return true;
            };
            //remove the lastly added denomination for backtracking
            result.remove(result.size() - 1);
        }
        return false;
    }

    //calculate the total amount available in cash register
    public int totalAmount() {
        int sum = 0;
        for (int i : register.keySet()) {
            sum = sum + (i * register.get(i));
        }
        return sum;
    }

    //exit the program
    @Override
    public void quit() {
        System.out.println(Messages.QUIT_MESSAGE);
        System.exit(0);
    }
}
