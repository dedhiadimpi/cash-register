package com.mycompany.exception;

import com.mycompany.common.Messages;

/**
 *
 * @author dimpi
 */
public class WrongCommandException extends Exception {

    public WrongCommandException() {
        super(Messages.WRONG_COMMAND_ERR);
    }

    public WrongCommandException(String message) {
        super(message);
    }
}
