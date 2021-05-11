package com.mycompany.exception;

import com.mycompany.common.Messages;

/**
 *
 * @author dimpi
 */
public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super(Messages.INVALID_INPUT_ERR);
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
