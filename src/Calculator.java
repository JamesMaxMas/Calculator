import java.util.*;

public class Calculator {


    private long lastValue;
    private long value;
    private String valueBitString;
    private String valueString;
    private MemSize memorySize;
    private NumSystem system;
    public long current;
    public Map<String, List<String>> allowedInput = new HashMap<>();
    private Map<String, Long> hexINput = new HashMap<>();
    public Display display = new Display(this);
    public static class Operations {

        public enum Direction {
            LEFT, RIGHT
        }

        public static long add(long a, long b, MemSize memSize) {
            switch (memSize) {
                case BYTE:
                    return (byte) ((byte) a + (byte) b);
                case WORD:
                    return (short) ((short) a + (short) b);
                case DWORD:
                    return ((int) a + (int) b);
                default:
                    return a + b; // QWORD
            }
        }

        public static long subtract(long a, long b, MemSize memSize) {
            switch (memSize) {
                case BYTE:
                    return (byte) ((byte) a - (byte) b);
                case WORD:
                    return (short) ((short) a - (short) b);
                case DWORD:
                    return ((int) a - (int) b);
                default:
                    return a - b; // QWORD
            }
        }

        public static long multiply(long a, long b, MemSize memSize) {
            switch (memSize) {
                case BYTE:
                    return (byte) ((byte) a * (byte) b);
                case WORD:
                    return (short) ((short) a * (short) b);
                case DWORD:
                    return (int) ((int) a * (int) b);
                default:
                    return a * b; // QWORD
            }
        }

        public static long divide(long a, long b, MemSize memSize) {
            switch (memSize) {
                case BYTE:

                    return (byte) ((byte) a / (byte) b);
                case WORD:
                    return (short) ((short) a / (short) b);
                case DWORD:
                    return (int) ((int) a / (int) b);
                default:
                    return a / b; // QWORD
            }
        }

        public static long changeSign(long a) {
            return -a;
        }

        public static long OR(long a, long b, MemSize memSize) {
            switch (memSize) {
                case BYTE:
                    return (byte) ((byte) a | (byte) b);
                case WORD:
                    return (short) ((short) a | (short) b);
                case DWORD:
                    return (int) ((int) a | (int) b);
                default:
                    return a | b; // QWORD
            }
        }

        public static long AND(long a, long b, MemSize memSize) {
            switch (memSize) {
                case BYTE:
                    return (byte) ((byte) a & (byte) b);
                case WORD:
                    return (short) ((short) a & (short) b);
                case DWORD:
                    return (int) ((int) a & (int) b);
                default:
                    return a & b; // QWORD
            }
        }

        public static long XOR(long a, long b, MemSize memSize) {
            switch (memSize) {
                case BYTE:
                    return (byte) ((byte) a ^ (byte) b);
                case WORD:
                    return (short) ((short) a ^ (short) b);
                case DWORD:
                    return (int) ((int) a ^ (int) b);
                default:
                    return a ^ b; // QWORD
            }
        }

        public static long NOT(long a, MemSize memSize) {
            switch (memSize) {
                case BYTE:
                    return (byte) ~((byte) a);
                case WORD:
                    return (short) ~((short) a);
                case DWORD:
                    return (int) ~((int) a);
                default:
                    return ~a; // QWORD
            }
        }

        public static long bitShift(Direction direction, long a, MemSize size) {
            long result = 0;
            if (direction == Direction.LEFT) {
                result = a << 1;
                if ((a & size.mostSignificantBit()) == size.mostSignificantBit()) {
                    result |= 1;
                }
            } else {
                result = a >> 1;
                if ((a & 1) == 1) {
                    result |= size.mostSignificantBit();
                }
            }
            return result;
        }

        public static long bitShift(Direction direction, long a, int amount, MemSize size) {
            if (amount >= size.toInt()) {
                throw new ArithmeticException("Size ERR!");
            }

            if (direction == Direction.LEFT) {
                return a << amount;
            } else {
                return a >> amount;
            }
        }

        public static long mod(long a, long b, MemSize memSize) {
            switch (memSize) {
                case BYTE:
                    return (byte) ((byte) a % (byte) b);
                case WORD:
                    return (short) ((short) a % (short) b);
                case DWORD:
                    return (int) ((int) a % (int) b);
                default:
                    return a % b; // QWORD
            }
        }
    }

    public Calculator() {
        this.memorySize = MemSize.QWORD;
        this.system = NumSystem.DEC;
        this.current = 0;
        this.value = 0;
        this.valueString = "";
        allowedInput.put("BIN", Arrays.asList("0", "1"));
        allowedInput.put("OCT", Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7"));
        allowedInput.put("DEC", Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        allowedInput.put("HEX", Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"));
    }

    public MemSize getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(MemSize memorySize) {
        this.memorySize = memorySize;
    }

    public NumSystem getSystem() {
        return system;
    }

    public void setSystem(NumSystem system) {
        System.out.println(value);
        valueString = Long.toString(value, system.toInt()).toUpperCase();
        this.system = system;
        display.setDisplayLabel(valueString);
    }

    public void setCurrent(long current) {
        this.current = current;
    }
    public long getCurrent() {  return this.current; }

    public long getValue() {return value;}

    public String getValueString() {
        return valueString;
    }
    public void clear() {
        current = 0;
        value = 0;
        valueString = "";
        display.setDisplayLabel(valueString);
    }

    public void numericInput(String input) {

        StringBuilder builder = new StringBuilder(valueString);
        switch (system){
            case BIN:
                if (allowedInput.get("BIN").contains(input)){
                    lastValue = value;
                    valueString = builder.append(input).toString();
                    value = Long.parseLong(valueString, 2);
                }
                break;
            case OCT:
                if (allowedInput.get("OCT").contains(input)){
                    lastValue = value;
                    valueString = builder.append(input).toString();
                    value = Long.parseLong(valueString, 8);
                }
                break;
            case DEC:
                if (allowedInput.get("DEC").contains(input)){
                    lastValue = value;
                    valueString = builder.append(input).toString();
                    value = Long.parseLong(valueString, 10);
                }
                break;
            case HEX:
                if (allowedInput.get("HEX").contains(input)){
                    lastValue = value;
                    valueString = builder.append(input).toString();
                    value = Long.parseLong(valueString, 16);
                }
                break;
        }
        //System.out.println("12");
        //System.out.println("Value string"+valueString);\
        //todo test
         if (valueString.charAt(0) == '0' && valueString.length()>1){
             valueString = valueString.substring(1);
         }
        display.setDisplayLabel(valueString);
    }
    public void changeSign(){

    }

}
