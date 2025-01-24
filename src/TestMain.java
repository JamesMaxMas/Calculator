import org.junit.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestMain {

    public Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void testDefaultValues() {
        assertEquals(NumSystem.DEC, calc.getSystem());
        assertEquals(MemSize.QWORD, calc.getMemorySize());
    }

    @Test
    public void testAdd() {
        // Default settings
        assertEquals(10, Operations.add(5, 5, calc.getMemorySize()));
        assertEquals(Long.MIN_VALUE, Operations.add(Long.MAX_VALUE, 1, calc.getMemorySize()));
        assertEquals(Long.MAX_VALUE, Operations.add(Long.MIN_VALUE, -1, calc.getMemorySize()));

        // Setting memory size to DWORD
        calc.setMemorySize(MemSize.DWORD);
        assertEquals(10, Operations.add(5, 5, calc.getMemorySize()));
        assertEquals(Integer.MIN_VALUE, Operations.add(Integer.MAX_VALUE, 1, calc.getMemorySize()));
        assertEquals(Integer.MAX_VALUE, Operations.add(Integer.MIN_VALUE, -1, calc.getMemorySize()));

        // Setting memory size to WORD
        calc.setMemorySize(MemSize.WORD);
        assertEquals(10, Operations.add(5, 5, calc.getMemorySize()));
        assertEquals(Short.MIN_VALUE, Operations.add(Short.MAX_VALUE, 1, calc.getMemorySize()));
        assertEquals(Short.MAX_VALUE, Operations.add(Short.MIN_VALUE, -1, calc.getMemorySize()));

        // Setting memory size to BYTE
        calc.setMemorySize(MemSize.BYTE);
        assertEquals(10, Operations.add(5, 5, calc.getMemorySize()));
        assertEquals(Byte.MIN_VALUE, Operations.add(Byte.MAX_VALUE, 1, calc.getMemorySize()));
        assertEquals(Byte.MAX_VALUE, Operations.add(Byte.MIN_VALUE, -1, calc.getMemorySize()));
    }

    @Test
    public void testSubtract() {
        // Default settings
        assertEquals(0, Operations.subtract(5, 5, calc.getMemorySize()));
        assertEquals(Long.MAX_VALUE, Operations.subtract(Long.MIN_VALUE, 1, calc.getMemorySize()));
        assertEquals(Long.MIN_VALUE, Operations.subtract(Long.MAX_VALUE, -1, calc.getMemorySize()));

        // Setting memory to DWORD
        calc.setMemorySize(MemSize.DWORD);
        assertEquals(0, Operations.subtract(5, 5, calc.getMemorySize()));
        assertEquals(Integer.MAX_VALUE, Operations.subtract(Integer.MIN_VALUE, 1, calc.getMemorySize()));
        assertEquals(Integer.MIN_VALUE, Operations.subtract(Integer.MAX_VALUE, -1, calc.getMemorySize()));

        // Setting memory to WORD
        calc.setMemorySize(MemSize.WORD);
        assertEquals(0, Operations.subtract(5, 5, calc.getMemorySize()));
        assertEquals(Short.MAX_VALUE, Operations.subtract(Short.MIN_VALUE, 1, calc.getMemorySize()));
        assertEquals(Short.MIN_VALUE, Operations.subtract(Short.MAX_VALUE, -1, calc.getMemorySize()));

        // Setting memory to BYTE
        calc.setMemorySize(MemSize.BYTE);
        assertEquals(0, Operations.subtract(5, 5, calc.getMemorySize()));
        assertEquals(Byte.MAX_VALUE, Operations.subtract(Byte.MIN_VALUE, 1, calc.getMemorySize()));
        assertEquals(Byte.MIN_VALUE, Operations.subtract(Byte.MAX_VALUE, -1, calc.getMemorySize()));

    }

    @Test
    public void testMultiply() {
        assertEquals(25, Operations.multiply(5, 5, calc.getMemorySize()));
        assertEquals(1, Operations.multiply(Long.MAX_VALUE, Long.MAX_VALUE, calc.getMemorySize()));
        assertEquals(0, Operations.multiply(Long.MIN_VALUE, Long.MIN_VALUE, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(25, Operations.multiply(5, 5, calc.getMemorySize()));
        assertEquals(1, Operations.multiply(Integer.MAX_VALUE, Integer.MAX_VALUE, calc.getMemorySize()));
        assertEquals(0, Operations.multiply(Integer.MIN_VALUE, Integer.MIN_VALUE, calc.getMemorySize()));

        calc.setMemorySize(MemSize.WORD);
        assertEquals(25, Operations.multiply(5, 5, calc.getMemorySize()));
        assertEquals(1, Operations.multiply(Short.MAX_VALUE, Short.MAX_VALUE, calc.getMemorySize()));
        assertEquals(0, Operations.multiply(Short.MIN_VALUE, Short.MIN_VALUE, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(25, Operations.multiply(5, 5, calc.getMemorySize()));
        assertEquals(1, Operations.multiply(Byte.MAX_VALUE, Byte.MAX_VALUE, calc.getMemorySize()));
        assertEquals(0, Operations.multiply(Byte.MIN_VALUE, Byte.MIN_VALUE, calc.getMemorySize()));
    }

    @Test
    public void testDivide() {
        assertThrows(ArithmeticException.class, () -> Operations.divide(5, 0, calc.getMemorySize()));
        assertEquals(1, Operations.divide(5, 5, calc.getMemorySize()));
        assertEquals(1, Operations.divide(3, 2, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertThrows(ArithmeticException.class, () -> Operations.divide(5, 0, calc.getMemorySize()));
        assertEquals(1, Operations.divide(5, 5, calc.getMemorySize()));
        assertEquals(1, Operations.divide(3, 2, calc.getMemorySize()));

        calc.setMemorySize(MemSize.WORD);
        assertThrows(ArithmeticException.class, () -> Operations.divide(5, 0, calc.getMemorySize()));
        assertEquals(1, Operations.divide(5, 5, calc.getMemorySize()));
        assertEquals(1, Operations.divide(3, 2, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertThrows(ArithmeticException.class, () -> Operations.divide(5, 0, calc.getMemorySize()));
        assertEquals(1, Operations.divide(5, 5, calc.getMemorySize()));
        assertEquals(1, Operations.divide(3, 2, calc.getMemorySize()));
    }


    @Test
    public void testChangeSign() {
        assertEquals(-5, Operations.changeSign(5));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(-5, Operations.changeSign(5));

        calc.setMemorySize(MemSize.WORD);
        assertEquals(-5, Operations.changeSign(5));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(-5, Operations.changeSign(5));
    }

    @Test
    public void testOR() {
        assertEquals(1, Operations.OR(0, 1, calc.getMemorySize()));
        assertEquals(1, Operations.OR(1, 0, calc.getMemorySize()));
        assertEquals(1, Operations.OR(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(1, Operations.OR(0, 1, calc.getMemorySize()));
        assertEquals(1, Operations.OR(1, 0, calc.getMemorySize()));
        assertEquals(1, Operations.OR(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.WORD);
        assertEquals(1, Operations.OR(0, 1, calc.getMemorySize()));
        assertEquals(1, Operations.OR(1, 0, calc.getMemorySize()));
        assertEquals(1, Operations.OR(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(1, Operations.OR(0, 1, calc.getMemorySize()));
        assertEquals(1, Operations.OR(1, 0, calc.getMemorySize()));
        assertEquals(1, Operations.OR(1, 1, calc.getMemorySize()));
    }

    @Test
    public void testAND() {
        assertEquals(0, Operations.AND(0, 1, calc.getMemorySize()));
        assertEquals(0, Operations.AND(1, 0, calc.getMemorySize()));
        assertEquals(1, Operations.AND(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(0, Operations.AND(0, 1, calc.getMemorySize()));
        assertEquals(0, Operations.AND(1, 0, calc.getMemorySize()));
        assertEquals(1, Operations.AND(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.WORD);
        assertEquals(0, Operations.AND(0, 1, calc.getMemorySize()));
        assertEquals(0, Operations.AND(1, 0, calc.getMemorySize()));
        assertEquals(1, Operations.AND(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(0, Operations.AND(0, 1, calc.getMemorySize()));
        assertEquals(0, Operations.AND(1, 0, calc.getMemorySize()));
        assertEquals(1, Operations.AND(1, 1, calc.getMemorySize()));
    }

    @Test
    public void testXOR() {
        assertEquals(1, Operations.XOR(0, 1, calc.getMemorySize()));
        assertEquals(1, Operations.XOR(1, 0, calc.getMemorySize()));
        assertEquals(0, Operations.XOR(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(1, Operations.XOR(0, 1, calc.getMemorySize()));
        assertEquals(1, Operations.XOR(1, 0, calc.getMemorySize()));
        assertEquals(0, Operations.XOR(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.WORD);
        assertEquals(1, Operations.XOR(0, 1, calc.getMemorySize()));
        assertEquals(1, Operations.XOR(1, 0, calc.getMemorySize()));
        assertEquals(0, Operations.XOR(1, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(1, Operations.XOR(0, 1, calc.getMemorySize()));
        assertEquals(1, Operations.XOR(1, 0, calc.getMemorySize()));
        assertEquals(0, Operations.XOR(1, 1, calc.getMemorySize()));
    }

    @Test
    public void testNOT() {
        assertEquals(-1, Operations.NOT(0, calc.getMemorySize()));
        assertEquals(-2, Operations.NOT(1, calc.getMemorySize()));
        assertEquals(Long.MAX_VALUE, Operations.NOT(Long.MIN_VALUE, calc.getMemorySize()));
        assertEquals(Long.MIN_VALUE, Operations.NOT(Long.MAX_VALUE, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(-1, Operations.NOT(0, calc.getMemorySize()));
        assertEquals(-2, Operations.NOT(1, calc.getMemorySize()));
        assertEquals(Integer.MAX_VALUE, Operations.NOT(Integer.MIN_VALUE, calc.getMemorySize()));
        assertEquals(Integer.MIN_VALUE, Operations.NOT(Integer.MAX_VALUE, calc.getMemorySize()));

        calc.setMemorySize(MemSize.WORD);
        assertEquals(-1, Operations.NOT(0, calc.getMemorySize()));
        assertEquals(-2, Operations.NOT(1, calc.getMemorySize()));
        assertEquals(Short.MAX_VALUE, Operations.NOT(Short.MIN_VALUE, calc.getMemorySize()));
        assertEquals(Short.MIN_VALUE, Operations.NOT(Short.MAX_VALUE, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(-1, Operations.NOT(0, calc.getMemorySize()));
        assertEquals(-2, Operations.NOT(1, calc.getMemorySize()));
        assertEquals(Byte.MAX_VALUE, Operations.NOT(Byte.MIN_VALUE, calc.getMemorySize()));
        assertEquals(Byte.MIN_VALUE, Operations.NOT(Byte.MAX_VALUE, calc.getMemorySize()));
    }

    @Test
    public void testBitShiftLeft() {
        assertEquals(8, Operations.bitShift(Operations.Direction.LEFT, 4, calc.getMemorySize()));
        assertEquals(-2, Operations.bitShift(Operations.Direction.LEFT, Long.MAX_VALUE, calc.getMemorySize()));
        assertEquals(1, Operations.bitShift(Operations.Direction.LEFT, Long.MIN_VALUE, calc.getMemorySize()));
    }

    @Test
    public void testBitShiftLeftByAmount() {
        assertEquals(8, Operations.bitShift(Operations.Direction.LEFT, 2, 2, calc.getMemorySize()));
        assertEquals(-2, Operations.bitShift(Operations.Direction.LEFT, Long.MAX_VALUE, 1, calc.getMemorySize()));
        assertEquals(0, Operations.bitShift(Operations.Direction.LEFT, Long.MIN_VALUE, 1, calc.getMemorySize()));
        assertThrows(ArithmeticException.class, () -> Operations.bitShift(Operations.Direction.LEFT, 1, 64, calc.getMemorySize()));
    }

    @Test
    public void testBitShiftRight() {
        assertEquals(2, Operations.bitShift(Operations.Direction.RIGHT, 4, calc.getMemorySize()));
        assertEquals(Long.MIN_VALUE, Operations.bitShift(Operations.Direction.RIGHT, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(2, Operations.bitShift(Operations.Direction.RIGHT, 4, calc.getMemorySize()));
        assertEquals(Integer.MIN_VALUE, (int)Operations.bitShift(Operations.Direction.RIGHT, 1, calc.getMemorySize()));


        calc.setMemorySize(MemSize.WORD);
        assertEquals(2, Operations.bitShift(Operations.Direction.RIGHT, 4, calc.getMemorySize()));
        assertEquals(Short.MIN_VALUE, (short)Operations.bitShift(Operations.Direction.RIGHT, 1, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(2, Operations.bitShift(Operations.Direction.RIGHT, 4, calc.getMemorySize()));
        assertEquals(Byte.MIN_VALUE, (byte)Operations.bitShift(Operations.Direction.RIGHT, 1, calc.getMemorySize()));
    }

    @Test
    public void testBitShiftRightByAmount() {
        assertEquals(0, Operations.bitShift(Operations.Direction.RIGHT, 2, 2, calc.getMemorySize()));
        assertEquals(0, Operations.bitShift(Operations.Direction.RIGHT, 1, 1, calc.getMemorySize()));
        assertThrows(ArithmeticException.class, () -> Operations.bitShift(Operations.Direction.LEFT, 1, 64, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(0, Operations.bitShift(Operations.Direction.RIGHT, 2, 2, calc.getMemorySize()));
        assertEquals(0, Operations.bitShift(Operations.Direction.RIGHT, 1, 1, calc.getMemorySize()));
        assertThrows(ArithmeticException.class, () -> Operations.bitShift(Operations.Direction.LEFT, 1, 64, calc.getMemorySize()));

        calc.setMemorySize(MemSize.WORD);
        assertEquals(0, Operations.bitShift(Operations.Direction.RIGHT, 2, 2, calc.getMemorySize()));
        assertEquals(0, Operations.bitShift(Operations.Direction.RIGHT, 1, 1, calc.getMemorySize()));
        assertThrows(ArithmeticException.class, () -> Operations.bitShift(Operations.Direction.LEFT, 1, 64, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(0, Operations.bitShift(Operations.Direction.RIGHT, 2, 2, calc.getMemorySize()));
        assertEquals(0, Operations.bitShift(Operations.Direction.RIGHT, 1, 1, calc.getMemorySize()));
        assertThrows(ArithmeticException.class, () -> Operations.bitShift(Operations.Direction.LEFT, 1, 64, calc.getMemorySize()));

    }

    @Test
    public void testMod() {
        assertEquals(0, Operations.mod(5, 5, calc.getMemorySize()));

        calc.setMemorySize(MemSize.DWORD);
        assertEquals(0, Operations.mod(5, 5, calc.getMemorySize()));

        calc.setMemorySize(MemSize.WORD);
        assertEquals(0, Operations.mod(5, 5, calc.getMemorySize()));

        calc.setMemorySize(MemSize.BYTE);
        assertEquals(0, Operations.mod(5, 5, calc.getMemorySize()));
    }


    @Test
    public void testSystemChangeOperations() {
        calc.setSystem(NumSystem.BIN);
        assertEquals(NumSystem.BIN, calc.getSystem());

        calc.setSystem(NumSystem.DEC);
        assertEquals(NumSystem.DEC, calc.getSystem());

        calc.setSystem(NumSystem.OCT);
        assertEquals(NumSystem.OCT, calc.getSystem());

        calc.setSystem(NumSystem.HEX);
        assertEquals(NumSystem.HEX, calc.getSystem());
    }
    @Test
    public void testSystemChangeOperationsString() {

        calc.setSystem(NumSystem.stringToNumSystem("Bin"));
        assertEquals(NumSystem.BIN, calc.getSystem());

        calc.setSystem(NumSystem.stringToNumSystem("Dec"));
        assertEquals(NumSystem.DEC, calc.getSystem());

        calc.setSystem(NumSystem.stringToNumSystem("Oct"));
        assertEquals(NumSystem.OCT, calc.getSystem());

        calc.setSystem(NumSystem.stringToNumSystem("Hex"));
        assertEquals(NumSystem.HEX, calc.getSystem());
    }
    @Test
    public void testMemorySize() {
        calc.setMemorySize(MemSize.BYTE);
        assertEquals(MemSize.BYTE, calc.getMemorySize());
        calc.setMemorySize(MemSize.WORD);
        assertEquals(MemSize.WORD, calc.getMemorySize());
        calc.setMemorySize(MemSize.DWORD);
        assertEquals(MemSize.DWORD, calc.getMemorySize());
        calc.setMemorySize(MemSize.QWORD);
        assertEquals(MemSize.QWORD, calc.getMemorySize());
    }
    @Test
    public void testMemorySizeString() {
        calc.setMemorySize(MemSize.stringToMemSize("Byte"));
        assertEquals(MemSize.BYTE, calc.getMemorySize());
        calc.setMemorySize(MemSize.stringToMemSize("Word"));
        assertEquals(MemSize.WORD, calc.getMemorySize());
        calc.setMemorySize(MemSize.stringToMemSize("Dword"));
        assertEquals(MemSize.DWORD, calc.getMemorySize());
        calc.setMemorySize(MemSize.stringToMemSize("Qword"));
        assertEquals(MemSize.QWORD, calc.getMemorySize());
    }
    @Test
    public void testSetCurrent() {
        calc.setCurrent(5);
        assertEquals(5, calc.getCurrent());
    }
    @Test
    public void testWyswietlaczAdd() {

        Frame frame = new Frame("Basic Program");
        Label label = new Label("Hello World!");

        // Aligning the label to CENTER
        label.setAlignment(Label.CENTER);

        // Adding Label and Setting
        // the Size of the Frame
        frame.add(label);
        frame.setSize(300, 300);

        // Making the Frame visible
        frame.setVisible(true);

        calc.setCurrent(Operations.add(5, 1, calc.getMemorySize()));
        label.setText(Long.toString(calc.current));
        assertEquals("6", label.getText());

    }
    @Test
    public void testInitialAllowedInput() {

        assertEquals(calc.allowedInput.get("BIN"), Arrays.asList("0", "1"));

    }
    @Test
    public void testNumericInput() {
        calc.setSystem(NumSystem.DEC);
        calc.numericInput("5");
        assertEquals(calc.getValue(), 5);

        calc.clear();

        assertEquals(calc.getValue(), 0);
        assertEquals(calc.getValueString(), "0");

        calc.setSystem(NumSystem.HEX);
        calc.numericInput("A");
        assertEquals(calc.getValue(), 10);


    }
    @Test
    public void testNumericInputDisplay() {
        calc.clear();
        assertEquals(calc.display.label.getText(), "0");
        calc.setSystem(NumSystem.DEC);
        calc.numericInput("7");
        assertEquals(calc.display.label.getText(), "7");
        calc.numericInput("7");
        assertEquals(calc.display.label.getText(), "77");
        calc.clear();
        assertEquals(calc.display.label.getText(), "0");
    }
    @Test
    public void testNumericInputDisplayHex() {
        calc.clear();
        assertEquals(calc.display.label.getText(), "0");
        calc.setSystem(NumSystem.HEX);
        calc.numericInput("A");
        assertEquals(calc.display.label.getText(), "A");
        calc.numericInput("A");
        assertEquals(calc.display.label.getText(), "AA");
        calc.clear();
        assertEquals(calc.display.label.getText(), "0");
    }
    @Test
    public void testNumericOnBoundaries() {
        calc.clear();
        assertEquals(calc.display.label.getText(), "0");
        calc.setSystem(NumSystem.DEC);
        calc.setMemorySize(MemSize.BYTE);

        // Test dla BYTE (limit 128)
        calc.numericInput("1");
        assertEquals(calc.display.label.getText(), "1");
        calc.numericInput("2");
        assertEquals(calc.display.label.getText(), "12");
        calc.numericInput("8");
        assertEquals("12", calc.display.label.getText()); // Nie powinno przekroczyć 128
        calc.clear();
        assertEquals("0", calc.display.label.getText());

        // Test dla WORD (limit 32767)
        calc.setMemorySize(MemSize.WORD);
        calc.numericInput("3");
        calc.numericInput("2");
        calc.numericInput("7");
        calc.numericInput("6");
        assertEquals("3276", calc.display.label.getText());
        calc.numericInput("7");
        assertEquals("3276", calc.display.label.getText()); // Nie powinno przekroczyć 32767
        calc.clear();
        assertEquals("0", calc.display.label.getText());

        // Test dla DWORD (limit 2147483647)
        calc.setMemorySize(MemSize.DWORD);
        calc.numericInput("2");
        calc.numericInput("1");
        calc.numericInput("4");
        calc.numericInput("7");
        calc.numericInput("4");
        calc.numericInput("8");
        calc.numericInput("3");
        calc.numericInput("6");
        calc.numericInput("4");
        assertEquals("214748364", calc.display.label.getText());
        calc.numericInput("7");
        assertEquals("214748364", calc.display.label.getText()); // Nie powinno przekroczyć 2147483647
        calc.clear();
        assertEquals("0", calc.display.label.getText());

        // Test dla QWORD (limit 9223372036854775807)
        calc.setMemorySize(MemSize.QWORD);
        calc.numericInput("9");
        calc.numericInput("2");
        calc.numericInput("2");
        calc.numericInput("3");
        calc.numericInput("3");
        calc.numericInput("7");
        calc.numericInput("2");
        calc.numericInput("0");
        calc.numericInput("3");
        calc.numericInput("6");
        calc.numericInput("8");
        calc.numericInput("5");
        calc.numericInput("4");
        calc.numericInput("7");
        calc.numericInput("7");
        calc.numericInput("5");
        calc.numericInput("8");
        calc.numericInput("0");
        assertEquals("922337203685477580", calc.display.label.getText());
        calc.numericInput("7");
        assertEquals("922337203685477580", calc.display.label.getText()); // Nie powinno przekroczyć 9223372036854775807
        calc.clear();
        assertEquals("0", calc.display.label.getText());
    }



}
