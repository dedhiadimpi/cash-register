package com.mycompany.TestCashRegister;

import com.mycompany.cashRegister.CashRegisterImpl;
import com.mycompany.common.Messages;
import com.mycompany.exception.EmptyRegisterException;
import com.mycompany.exception.InvalidInputException;
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
public class TestShow {

    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;
    private static CashRegisterImpl obj;

    public TestShow() {
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
    public void testEmptyCashRegister() {
        EmptyRegisterException thrown = assertThrows(EmptyRegisterException.class, () -> obj.show());
        assertEquals(Messages.EMPTY_REGISTER_ERR, thrown.getMessage());
    }

    @Test
    public void testShowCashRegister() throws InvalidInputException, EmptyRegisterException {
        obj.put(new String[]{"put", "1", "2", "3", "4", "5"});
        outContent.reset();
        obj.show();
        assertEquals("$68 1 2 3 4 5", outContent.toString().trim());
    }

}
