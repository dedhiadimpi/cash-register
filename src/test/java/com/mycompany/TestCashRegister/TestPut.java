package com.mycompany.TestCashRegister;

import com.mycompany.cashRegister.CashRegisterImpl;
import com.mycompany.common.Messages;
import com.mycompany.exception.EmptyRegisterException;
import com.mycompany.exception.InvalidInputException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dimpi
 */
public class TestPut {

    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;
    private static CashRegisterImpl obj;

    public TestPut() {
    }

    @BeforeClass
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterClass
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Before
    public void beforeEachTest() {
        obj = new CashRegisterImpl();
        outContent.reset();
    }

    @Test
    public void testIncompleteUserInput() {
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> obj.put(new String[]{"put", "1", "2", "3", "4"}));
        assertEquals(Messages.INVALID_INPUT_ERR, thrown.getMessage());
    }

    @Test
    public void testInvalidUserInput() {
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> obj.put(new String[]{"put", "1", "2", "3", "4", "-5"}));
        assertEquals(Messages.NEGATIVE_INPUT_ERR, thrown.getMessage());
    }

    @Test
    public void testNonDigitUserInput() throws InvalidInputException, EmptyRegisterException {
        obj.put(new String[]{"put", "e", "2", "3", "4", "5"});
        assertEquals(Messages.INVALID_NUMBER_ERR, outContent.toString().trim());
    }

    @Test
    public void testCorrectUserInput() throws InvalidInputException, EmptyRegisterException {
        obj.put(new String[]{"put", "1", "2", "3", "4", "5"});
        assertEquals("$68 1 2 3 4 5", outContent.toString().trim());
    }

    @Test
    public void testCorrectUserInputWithZeroBill() throws InvalidInputException, EmptyRegisterException {
        obj.put(new String[]{"put", "0", "0", "0", "4", "0"});
        assertEquals("$8 0 0 0 4 0", outContent.toString().trim());
    }
}
