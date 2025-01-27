import javax.swing.*;
import java.util.*;
//todo funkcje pamięci kalkulatora
public class Calculator {


    private boolean nextResetDisplay;
    private long lastValue;
    private long memory;
    public void setValue(long value) {
        this.value = value;
    }

    public long getMemory() {
        return memory;
    }

    public void setMemory(long memory) {
        this.memory = memory;
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
    Action action;



    public void wykonaj(){
        this.value = action.wykonaj(this.current,this.value, this.memorySize);
        if (system == NumSystem.DEC){
            valueString = Long.toString(value, system.toInt()).toUpperCase();
        }else {
            valueString = Long.toUnsignedString(value, system.toInt()).toUpperCase();
        }
        if (valueString.charAt(0) == '0' && valueString.length()>1){
            valueString = valueString.substring(1);
        }
        display.setDisplayLabel(valueString);

    }
    public void addAction(Action a){
        action = a;
        this.current = value;
        nextResetDisplay = true;
    }

    public Calculator() {
        this.memorySize = MemSize.QWORD;
        this.system = NumSystem.DEC;
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
        if (system == NumSystem.DEC ){
            System.out.println(value);
            String s = Long.toBinaryString(value);
            int desiredLength = memorySize.toInt();
            if (s.length() > desiredLength) {
                s = s.substring(s.length() - desiredLength);
            }
//            System.out.println(s);
            long i2 = NumSystem.parseSignedBinary(s, 2); // Parsowanie jako liczby bez znaku
//            System.out.println(i2);
            value = i2;
            valueString = Long.toString(value, system.toInt()).toUpperCase();
        }else {
            System.out.println("test");

            String s = Long.toBinaryString(value);
            int desiredLength = memorySize.toInt();
            if (s.length() > desiredLength) {
                s = s.substring(s.length() - desiredLength);
            }
            System.out.println(s);
            long i2 = Long.parseUnsignedLong(s, 2); // Parsowanie jako liczby bez znaku
            System.out.println(i2);
            value = i2;
            valueString = Long.toUnsignedString(value, system.toInt()).toUpperCase();

        }

        display.update();
        nextResetDisplay = true;
    }

    public NumSystem getSystem() {
        return system;
    }

    public void setSystem(NumSystem system) {
//        System.out.println(value);
        value = memorySize.convertValue(value);
//        System.out.println(value);
        if (system == NumSystem.DEC){
            System.out.println(value);
            valueString = Long.toString(value, system.toInt()).toUpperCase();
        }else {
            String s = Long.toBinaryString(value);
            int desiredLength = memorySize.toInt();
             if (s.length() > desiredLength) {
                s = s.substring(s.length() - desiredLength);
             }
            System.out.println(s);
            long i2 = Long.parseUnsignedLong(s, 2); // Parsowanie jako liczby bez znaku
            System.out.println(i2);
            value = i2;
            valueString = Long.toUnsignedString(value, system.toInt()).toUpperCase();

        }
        System.out.println(valueString);
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
        if (system == NumSystem.DEC){
        switch (memorySize) {
            case BYTE:
                if (value >= 128){
                    value = lastValue;
                    valueString = Long.toString(value, system.toInt()).toUpperCase();;
                }
                break;
            case WORD:
                if (value >= 32767){
                    value = lastValue;
                    valueString = Long.toString(value, system.toInt()).toUpperCase();;
                }
                break;
            case DWORD:
                if (value >= 2147483647){
                    value = lastValue;
                    valueString = Long.toString(value, system.toInt()).toUpperCase();;
                }
                break;
            case QWORD:
                if (value >= 9223372036854775807L){
                    value = lastValue;
                    valueString = Long.toString(value, system.toInt()).toUpperCase();;
                }
                break;
        }

            }
        else{
            switch (memorySize) {
                case BYTE:
                    if (value > 255) { // Maksymalna wartość unsigned dla BYTE
                        value = lastValue;
                        valueString = Long.toString(value, system.toInt()).toUpperCase();
                    }
                    break;
                case WORD:
                    if (value > 65535) { // Maksymalna wartość unsigned dla WORD
                        value = lastValue;
                        valueString = Long.toString(value, system.toInt()).toUpperCase();
                    }
                    break;
                case DWORD:
                    if (value > 4294967295L) { // Maksymalna wartość unsigned dla DWORD
                        value = lastValue;
                        valueString = Long.toString(value, system.toInt()).toUpperCase();
                    }
                    break;
//                case QWORD:
//                    if (value > 18446744073709551615L) { // Maksymalna wartość unsigned dla QWORD
//                        value = lastValue;
//                        valueString = Long.toString(value, system.toInt()).toUpperCase();
//                    }
//                    break;
            }
        }


         if (valueString.charAt(0) == '0' && valueString.length()>1){
             valueString = valueString.substring(1);
         }
        display.setDisplayLabel(valueString);
    }
    public void changeSign(){
        value = -value;
        if (system == NumSystem.DEC){
            valueString = Long.toString(value, system.toInt()).toUpperCase();
        }else {
            String s = Long.toBinaryString(value);
            int desiredLength = memorySize.toInt();
            if (s.length() > desiredLength) {
                s = s.substring(s.length() - desiredLength);
            }
            System.out.println(s);
            long i2 = Long.parseUnsignedLong(s, 2); // Parsowanie jako liczby bez znaku
            System.out.println(i2);
            value = i2;
            valueString = Long.toUnsignedString(value, system.toInt()).toUpperCase();

        }
        System.out.println(valueString);
        this.system = system;
        display.setDisplayLabel(valueString);
        if (valueString.charAt(0) == '0' && valueString.length()>1){
            valueString = valueString.substring(1);
        }
        display.setDisplayLabel(valueString);

    }

    public void clear() {
        current = 0;
        value = 0;
        valueString = "0";
        action = null;
        display.setDisplayLabel(valueString);
    }
    public void mc() {
        memory = 0;
    }
    public void calculateValueString(){
        if (system == NumSystem.DEC){
            valueString = Long.toString(value, system.toInt()).toUpperCase();
        }else {
            String s = Long.toBinaryString(value);
            int desiredLength = memorySize.toInt();
            if (s.length() > desiredLength) {
                s = s.substring(s.length() - desiredLength);
            }
            System.out.println(s);
            long i2 = Long.parseUnsignedLong(s, 2); // Parsowanie jako liczby bez znaku
            System.out.println(i2);
            value = i2;
            valueString = Long.toUnsignedString(value, system.toInt()).toUpperCase();
        }
    }
    // Memory Recall (MR): odczytuje pamięć
    public void mr() {
        value =  memory;
        calculateValueString();
        display.setDisplayLabel(valueString);
    }

    // Memory Store (MS): zapisuje aktualny wpis do pamięci
    public void ms() {
        memory = value;
    }

    // Memory Add (M+): dodaje aktualny wpis do pamięci
    public void mPlus() {
        memory += value;
    }

    // Memory Subtract (M-): odejmuje aktualny wpis od pamięci
    public void mMinus() {
        memory -= value;
    }

    // Clear Entry (CE): czyści bieżące dane
    public void ce() {
        value=0;
        valueString = "0";
        display.setDisplayLabel(valueString);
    }

    public void backspace() {
        calculateValueString();
        if (!valueString.isEmpty()) {
            valueString = valueString.substring(0, valueString.length() - 1);
        }
        value = Long.parseLong(valueString, system.toInt());
        display.setDisplayLabel(valueString);
    }
    public void RoL(){
        value = Operations.bitShift(Operations.Direction.LEFT,value,  memorySize);
        calculateValueString();
        display.setDisplayLabel(valueString);
    }
    public void RoR(){
        value = Operations.bitShift(Operations.Direction.RIGHT,value,  memorySize);
        calculateValueString();
        display.setDisplayLabel(valueString);
    }
    public void Not(){
        value = Operations.NOT(value, memorySize);
        calculateValueString();
        display.setDisplayLabel(valueString);
    }



}
