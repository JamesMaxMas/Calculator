import javax.swing.*;
import java.util.*;
import java.util.function.Function;
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
//todo funkcje pamięci kalkulatora i E
public class Calculator {

    private boolean nextResetDisplay;
    private long lastValue;

    public void setValue(long value) {
        this.value = value;
    }

    private long value;
    private String valueBitString;
    private String valueString;
    private MemSize memorySize;
    private NumSystem system;
    public long current;
    public Map<String, List<String>> allowedInput = new HashMap<>();
    private Map<String, Long> hexINput = new HashMap<>();
    public Display display = new Display(this);
//    TriFunction<Long, Long, Long, MemSize> action;
    Action action;



    public void wykonaj(){
//        System.out.println(this.value);
//        System.out.println(this.current);
//        System.out.println(value);

        this.value = action.wykonaj(this.current,this.value, this.memorySize);

        if (system == NumSystem.DEC){
            valueString = Long.toString(value, system.toInt()).toUpperCase();
        }else {
            valueString = Long.toUnsignedString(value, system.toInt()).toUpperCase();
        }
//        System.out.println("valueString");
//        System.out.println(valueString);
//        System.out.println(value);
        if (valueString.charAt(0) == '0' && valueString.length()>1){
            valueString = valueString.substring(1);
        }
        display.setDisplayLabel(valueString);

    }
    public void addAction(
            Action a
    ){
        action = a;
        this.current = value;
        nextResetDisplay = true;


    }

    public Calculator() {
        this.memorySize = MemSize.QWORD;
        this.system = NumSystem.DEC;
//        setSystem(NumSystem.DEC);
//        setMemorySize(MemSize.QWORD);
        this.current = 0;
        this.value = 0;
        this.valueString = "0";
        nextResetDisplay = false;
        allowedInput.put("BIN", Arrays.asList("0", "1"));
        allowedInput.put("OCT", Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7"));
        allowedInput.put("DEC", Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        allowedInput.put("HEX", Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"));

        //display.display();
        display.update();
    }

    public MemSize getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(MemSize memorySize) {
        this.memorySize = memorySize;
        display.update();
        nextResetDisplay = true;
    }

    public NumSystem getSystem() {
        return system;
    }

    public void setSystem(NumSystem system) {
        //System.out.println(value);
        if (system == NumSystem.DEC){
            valueString = Long.toString(value, system.toInt()).toUpperCase();
        }else {
            valueString = Long.toUnsignedString(value, system.toInt()).toUpperCase();
        }
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
        valueString = "0";
        display.setDisplayLabel(valueString);
    }

    public void numericInput(String input) {
        if (nextResetDisplay){
            nextResetDisplay = false;
            value = 0;
            valueString = "0";
            display.setDisplayLabel(valueString);
        }
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
        switch (memorySize) {
            case BYTE:
                if (value >= 128){
                    value = lastValue;
                    valueString = Long.toString(value, system.toInt());
                }
                break;
            case WORD:
                if (value >= 32767){
                    value = lastValue;
                    valueString = Long.toString(value, system.toInt());
                }
                break;
            case DWORD:
                if (value >= 2147483647){
                    value = lastValue;
                    valueString = Long.toString(value, system.toInt());
                }
                break;
            case QWORD:
                if (value >= 9223372036854775807L){
                    value = lastValue;
                    valueString = Long.toString(value, system.toInt());
                }
                break;
        }

        //todo test
         if (valueString.charAt(0) == '0' && valueString.length()>1){
             valueString = valueString.substring(1);
         }
        display.setDisplayLabel(valueString);
    }
    public void changeSign(){
        if (system == NumSystem.DEC){
            value = -value;
            valueString = Long.toString(value, system.toInt()).toUpperCase();
        }else {
            value = -value;
//            System.out.println(value);
//            String s = Long.toBinaryString(value);
//            int desiredLength = memorySize.toInt();
//            if (s.length() > desiredLength) {
//                s = s.substring(s.length() - desiredLength);
//            }
//            System.out.println(s);
//            valueString = s;//= Long.toString(value, system.toInt());
//            long i2 = Long.parseUnsignedLong(valueString, 2); // Parsowanie jako liczby bez znaku
//            System.out.println(i2);
//            if ((i2 & (1L << 63)) != 0) { // Sprawdzanie, czy najwyższy bit jest ustawiony
//                i2 = i2 - (1L << 64); // Korekcja dla liczby ujemnej
//            }
//            if (value < 0 ){
//                i2+=1;
//            }
//            value = i2;
            valueString = Long.toUnsignedString(value, system.toInt()).toUpperCase();
        }

        if (valueString.charAt(0) == '0' && valueString.length()>1){
            valueString = valueString.substring(1);
        }
        display.setDisplayLabel(valueString);

    }

}
