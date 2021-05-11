package com.mycompany.TestCashRegister;

import com.mycompany.cashRegister.Main;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author dimpi
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Main.class)
public class MainTest {

    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;

    public MainTest() {
    }

    @BeforeClass
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterClass
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCommand() throws IOException, Exception {
        BufferedReader br = Mockito.mock(BufferedReader.class);
        PowerMockito.whenNew(BufferedReader.class).withAnyArguments().thenReturn(br);
        Mockito.when(br.readLine()).thenReturn("put 1 2 3 4 5").thenReturn(null);
        String[] args = null;
        Main.main(args);
        assertEquals("$68 1 2 3 4 5", outContent.toString().trim());
    }
}
