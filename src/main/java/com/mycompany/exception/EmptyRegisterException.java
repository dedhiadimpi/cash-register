package com.mycompany.exception;

import com.mycompany.common.Messages;

/**
 *
 * @author dimpi
 */
public class EmptyRegisterException extends Exception {

    public EmptyRegisterException() {
        super(Messages.EMPTY_REGISTER_ERR);
    }
}
