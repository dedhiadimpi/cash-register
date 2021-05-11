package com.mycompany.TestCashRegister;

import com.mycompany.cashRegister.CashRegisterImpl;
import com.mycompany.common.Messages;
import com.mycompany.exception.EmptyRegisterException;
import com.mycompany.exception.InvalidInputException;
import com.mycompany.exception.NoChangeException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dimpi
 */
public class TestChange {

    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;
    private static CashRegisterImpl obj;

    public TestChange() {
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
    public void testNegativeAmount() {
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> obj.change(-1));
        assertEquals(Messages.NEGATIVE_INPUT_ERR, thrown.getMessage());
    }

    @Test
    public void testHigherAmount() {
        NoChangeException thrown = assertThrows(NoChangeException.class, () -> obj.change(100));
        assertEquals(Messages.INSUFFICIENT_BALANCE_ERR, thrown.getMessage());
    }

    @Test
    public void testCorrectAmount() throws InvalidInputException, EmptyRegisterException, NoChangeException {
        obj.put(new String[]{"put", "0", "0", "2", "0", "3"});
        outContent.reset();
        obj.change(8);
        assertEquals("0 0 1 0 3", outContent.toString().trim());
    }

    @Test
    public void testNoChange() throws InvalidInputException, EmptyRegisterException, NoChangeException {
        obj.put(new String[]{"put", "0", "0", "2", "0", "3"});
        NoChangeException thrown = assertThrows(NoChangeException.class, () -> obj.change(4));
        assertEquals(Messages.NO_CHANGE_ERR, thrown.getMessage());
    }
}
