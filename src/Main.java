
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        //Display display = new Display(calc);
        calc.numericInput("5");
        calc.display.display();
    }
}