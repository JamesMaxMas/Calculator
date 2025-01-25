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

    @Test
    public void testBinaryInput() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.BIN);

        calculator.numericInput("1");
        calculator.numericInput("0");
        calculator.numericInput("1");

        assertEquals("101", calculator.getValueString());
        assertEquals(5, calculator.getValue());
    }

    @Test
    public void testInvalidBinaryInput() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.BIN);

        calculator.numericInput("2"); // Invalid binary input

        assertEquals("0", calculator.getValueString());
        assertEquals(0, calculator.getValue());
    }

    @Test
    public void testOctalInput() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.OCT);

        calculator.numericInput("7");
        calculator.numericInput("3");
        calculator.numericInput("4");

        assertEquals("734", calculator.getValueString());
        assertEquals(476, calculator.getValue());
    }

    @Test
    public void testDecimalInput() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.DEC);

        calculator.numericInput("1");
        calculator.numericInput("5");
        calculator.numericInput("9");

        assertEquals("159", calculator.getValueString());
        assertEquals(159, calculator.getValue());
    }

    @Test
    public void testHexadecimalInput() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.HEX);

        calculator.numericInput("A");
        calculator.numericInput("F");
        calculator.numericInput("1");

        assertEquals("AF1", calculator.getValueString());
        assertEquals(2801, calculator.getValue());
    }

    @Test
    public void testMemorySizeByteOverflow() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.DEC);
        calculator.setMemorySize(MemSize.BYTE);

        calculator.numericInput("1");
        calculator.numericInput("2");
        calculator.numericInput("8");

        assertEquals("12", calculator.getValueString());
        assertEquals(12, calculator.getValue());
    }

    @Test
    public void testLeadingZeroRemoval() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.DEC);

        calculator.numericInput("0");
        calculator.numericInput("0");
        calculator.numericInput("5");

        assertEquals("5", calculator.getValueString());
    }

    @Test
    public void testBinaryTruncationForMemorySize() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.BIN);
        calculator.setMemorySize(MemSize.WORD);

        calculator.setValue(0b11111111111111111); // 17 bits

        String s = Long.toBinaryString(calculator.getValue());
        int desiredLength = calculator.getMemorySize().toInt();
        if (s.length() > desiredLength) {
            s = s.substring(s.length() - desiredLength);
        }

        assertEquals("1111111111111111", s); // Truncated to 16 bits for WORD
    }

    @Test
    public void testNoTruncationForSmallerValue() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.BIN);
        calculator.setMemorySize(MemSize.BYTE);

        calculator.setValue(0b10101010); // 8 bits, within BYTE range

        String s = Long.toBinaryString(calculator.getValue());
        int desiredLength = calculator.getMemorySize().toInt();
        if (s.length() > desiredLength) {
            s = s.substring(s.length() - desiredLength);
        }

        assertEquals("10101010", s); // No truncation needed
    }
    @Test
    public void test2() {
        Calculator calculator = new Calculator();
        calculator.setMemorySize(MemSize.BYTE);
        calculator.numericInput("5");
        calculator.changeSign();
        calculator.setSystem(NumSystem.OCT);
        assertEquals("373", calculator.display.label.getText()); // No truncation needed
    }
    @Test
    public void test3() {
        Calculator calculator = new Calculator();
        calculator.setMemorySize(MemSize.BYTE);
        calculator.numericInput("5");
        calculator.setSystem(NumSystem.OCT);//zamienic kolejność
        calculator.changeSign();
        assertEquals("373", calculator.display.label.getText()); // No truncation needed
    }

    @Test
    public void testAddAction() {
        Add add = new Add();
        long result = add.wykonaj(10, 20, MemSize.DWORD);
        assertEquals(30, result);
    }

    @Test
    public void testSubAction() {
        Sub sub = new Sub();
        long result = sub.wykonaj(30, 20, MemSize.DWORD);
        assertEquals(10, result);
    }

    @Test
    public void testANDAction() {
        AND and = new AND();
        long result = and.wykonaj(0b1100, 0b1010, MemSize.DWORD);
        assertEquals(0b1000, result);
    }

    @Test
    public void testORAction() {
        OR or = new OR();
        long result = or.wykonaj(0b1100, 0b1010, MemSize.DWORD);
        assertEquals(0b1110, result);
    }

    @Test
    public void testXORAction() {
        XOR xor = new XOR();
        long result = xor.wykonaj(0b1100, 0b1010, MemSize.DWORD);
        assertEquals(0b0110, result);
    }

    @Test
    public void testMULAction() {
        MUL mul = new MUL();
        long result = mul.wykonaj(5, 4, MemSize.DWORD);
        assertEquals(20, result);
    }

    @Test
    public void testMODAction() {
        MOD mod = new MOD();
        long result = mod.wykonaj(10, 3, MemSize.DWORD);
        assertEquals(1, result);
    }

    @Test
    public void testDIVAction() {
        DIV div = new DIV();
        long result = div.wykonaj(10, 2, MemSize.DWORD);
        assertEquals(5, result);
    }

    @Test
    public void testLSHAction() {
        LSH lsh = new LSH();
        long result = lsh.wykonaj(0b101, 2, MemSize.DWORD);
        assertEquals(0b10100, result);
    }

    @Test
    public void testRSHAction() {
        RSH rsh = new RSH();
        long result = rsh.wykonaj(0b10100, 2, MemSize.DWORD);
        assertEquals(0b101, result);
    }

    @Test
    public void testWykonajAction() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.DEC);
        calculator.setMemorySize(MemSize.DWORD);

        Add add = new Add();
        calculator.numericInput("5");
        calculator.addAction(add);
        calculator.numericInput("5");
        calculator.wykonaj();

        assertEquals("10", calculator.getValueString());
        assertEquals(10, calculator.getValue());
    }

    @Test
    public void testWykonajWithHexadecimal() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.HEX);
        calculator.setMemorySize(MemSize.DWORD);
        calculator.numericInput("A");
        Add add = new Add();

        calculator.addAction(add);
        calculator.numericInput("A");
        calculator.wykonaj();

        assertEquals("14", calculator.getValueString());
        assertEquals(20, calculator.getValue());
    }

    @Test
    public void testAddActionSequence() {
        Calculator calculator = new Calculator();
        calculator.setSystem(NumSystem.DEC);
        calculator.setMemorySize(MemSize.DWORD);

        Add add = new Add();
        calculator.numericInput("1");
        calculator.numericInput("0");
        calculator.addAction(add);
        calculator.numericInput("1");
        calculator.numericInput("5");
        calculator.wykonaj();
        assertEquals("25", calculator.getValueString());
        assertEquals(25, calculator.getValue());
    }
}
