
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    //todo przyciski kalkulatora(pamięć codanie itd), testy, zmiana znaków inna niż sys10 // wyświetlacz bitowy
    //todo jest problem przy wpisywaniu liczby większej niż przyjmuje long
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.display.display();
//        System.out.println(parseSignedBinary("11111010", 2));
//        String s = "9999999999999999999";
//        long l = Long.parseLong(s, 10);
//        String s = Long.toBinaryString(-5);
//        int desiredLength = 8;
//        if (s.length() > desiredLength) {
//            s = s.substring(s.length() - desiredLength);
//        }
//        System.out.println(s);
////        s = s.substring(1);
//        long i2 = Long.parseUnsignedLong(s, 2); // Parsowanie jako liczby bez znaku
//        s = Long.toString(i2, 8);
//        System.out.println(s);
//        String binary = "1111111111111111111111111111111111111111111111111111111111111011";
//        long result = Long.parseUnsignedLong(binary, 2);
//
//        System.out.println("result");
//        System.out.println(result);
//        System.out.println(Long.toUnsignedString(result, 8).toUpperCase()); // Wyjście: 18446744073709551611
//        if ((i2 & (1L << 63)) != 0) { // Sprawdzanie, czy najwyższy bit jest ustawiony
//            i2 = i2 - (1L << 64); // Korekcja dla liczby ujemnej
//        }
//        if (-5 < 0 ){
//            i2+=1;
//        }

//        calc.clear();
        //calc.display.display();
//        long test1 = Operations.subtract(5,7,MemSize.QWORD);
//        System.out.println(Long.toString(test1, 10));


        //label.setText(calc.getValueString());
//        calc.setValue(-5L);
//        calc.setMemorySize(MemSize.DWORD);
//        System.out.println(calc.getMemorySize().toInt());
//        String s = Long.toBinaryString(calc.getValue());
//        int desiredLength = calc.getMemorySize().toInt();
//        if (s.length() > desiredLength) {
//            s = s.substring(s.length() - desiredLength);
//        }
//        System.out.println(s);
////        System.out.println(s2);
//        String paddedString = String.format("%" + desiredLength + "s", s).replace(' ', '0');
//        System.out.println(paddedString);
//        String s1, s2;
//
//        if (paddedString.length() <= 32) {
//            s1 = "";
//            s2 = paddedString;
//        }
//        else {
//            s1 = paddedString.substring(0, paddedString.length() - 32); // Wszystko przed ostatnimi 32 znakami
//            s2 = paddedString.substring(paddedString.length() - 32);
//        }
//        System.out.println(s1);
//        System.out.println(s2);
//
//        StringBuilder sb = new StringBuilder();
//        for (int j = 0;j < s1.length(); j++) {
//            sb.append(s1.charAt(j));
//            // Po każdych czterech znakach dodaj dwie spacje, jeśli nie jest to koniec stringa
//            if ((j + 1) % 4 == 0 && j + 1 < s1.length()) {
//                sb.append("  ");
//            }
//        }
//        s1 = sb.toString();
//
//        sb = new StringBuilder();
//        for (int j = 0;j < s2.length(); j++) {
//            sb.append(s2.charAt(j));
//            // Po każdych czterech znakach dodaj dwie spacje, jeśli nie jest to koniec stringa
//            if ((j + 1) % 4 == 0 && j + 1 < s2.length()) {
//                sb.append("  ");
//            }
//        }
//        s2 = sb.toString();
////        labelBit1.setText(s1);
////        labelBit2.setText(s2);
//        System.out.println(s1);
//        System.out.println(s2);
//        long i = 6;
//        String s = Long.toBinaryString((long)i);
//        System.out.println(Long.toBinaryString((long)i));
//        long i2 = Long.parseUnsignedLong(s, 2); // Parsowanie jako liczby bez znaku
//
//        if ((i2 & (1L << 63)) != 0) { // Sprawdzanie, czy najwyższy bit jest ustawiony
//            i2 = i2 - (1L << 64); // Korekcja dla liczby ujemnej
//        }
//        if (i < 0 ){
//            i2+=1;
//        }
//        System.out.println(i2);
//
////        String s = Long.toBinaryString(calc.getValue());
//        System.out.println(s);
//        calc.setMemorySize(MemSize.BYTE);

//        calc.addAction(new Add());
    }
}