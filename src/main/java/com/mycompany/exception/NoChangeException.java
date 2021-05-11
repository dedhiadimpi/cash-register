package com.mycompany.exception;

import com.mycompany.common.Messages;

/**
 *
 * @author dimpi
 */
public class NoChangeException extends Exception {

    public NoChangeException() {
        super(Messages.NO_CHANGE_ERR);
    }

    public NoChangeException(String message) {
        super(message);
    }
}
