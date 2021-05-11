package com.mycompany.cashRegister;

import com.mycompany.exception.EmptyRegisterException;
import com.mycompany.exception.InvalidInputException;
import com.mycompany.exception.NoChangeException;

/**
 *
 * @author dimpi
 */
//Interface for Cash Register
public interface CashRegister{
    public void show() throws EmptyRegisterException;
    public void put(String[] input) throws InvalidInputException, EmptyRegisterException;
    public void take(String[] input) throws InvalidInputException, EmptyRegisterException;
    public void change(int amount) throws InvalidInputException, NoChangeException;
    public void quit();
}
