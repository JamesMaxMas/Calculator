
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        //Display display = new Display(calc);
        //calc.numericInput("5");
        //calc.display.display();
        //calc.setSystem(NumSystem.stringToNumSystem("Bin"));
        //calc.setSystem(NumSystem.stringToNumSystem("Dec"));
        long i = -5;
        System.out.println(Integer.toBinaryString((int)i));
    }
}