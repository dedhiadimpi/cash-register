package com.mycompany.cashRegister;

import com.mycompany.common.Messages;
import com.mycompany.exception.WrongCommandException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author dimpi
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //create the object of cash register
        CashRegisterImpl cashRegisterImpl = new CashRegisterImpl();
        //Read the user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while ((command = br.readLine()) != null) {
            //convert input string to array of string
            String[] input = command.split(" ");
            try {
                //switch case for performing the action as per user input
                switch (input[0]) {
                    //if user inputs 'show' command, call show method of cash register
                    case "show":
                        cashRegisterImpl.show();
                        break;
                    //if user inputs 'put' command, call put method of cash register
                    case "put":
                        cashRegisterImpl.put(input);
                        break;
                    //if user inputs 'take' command, call take method of cash register
                    case "take":
                        cashRegisterImpl.take(input);
                        break;
                    //if user inputs 'change' command, call change method of cash register
                    case "change":
                        cashRegisterImpl.change(Integer.parseInt(input[1]));
                        break;
                    //if user inputs 'quit' command, call quit method of cash register
                    case "quit":
                        cashRegisterImpl.quit();
                        break;
                    //if user inputs any other command, throw an exception
                    default:
                        throw new WrongCommandException();
                }
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_NUMBER_ERR);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
