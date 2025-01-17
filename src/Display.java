import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display {
    Frame frame;
    Label label;
    Button buttonEmpty;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    Button buttonE;
    Button buttonF;
    Button buttonMod;
    Button buttonOpen;
    Button buttonClose;
    Button buttonRoL;
    Button buttonRoR;
    Button buttonOr;
    Button buttonXor;
    Button buttonLsh;
    Button buttonRsh;
    Button buttonNot;
    Button buttonAnd;
    Button buttonMC;
    Button buttonMR;
    Button buttonMS;
    Button buttonMPlus;
    Button buttonMMinus;
    Button buttonDel;
    Button buttonCE;
    Button buttonCC;//C
    Button buttonSign;
    Button buttonDiv;
    Button buttonMulti;
    Button buttonMin;
    Button buttonPlus;
    Button buttonUnused1;//,
    Button buttonUnused2;// sqrt
    Button buttonUnused3;//%
    Button buttonUnused4;//1/x
    Button buttonEqual;
    Calculator calc;
    Display(Calculator calc) {
        this.calc = calc;
        frame = new Frame("Basic Program");
        label = new Label("0", Label.CENTER);

        // Ustawienie layoutu ramki na null (bez layout managera)
        frame.setLayout(null);

        // Ustawienie rozmiaru i pozycji etykiety
        label.setBounds(50, 50, 200, 30);
        frame.add(label);

        // Ustawienie rozmiaru i pozycji przycisku
        int buttonWidth = 50;
        int buttonHeight = 30;
        int startX = 20; // początkowa pozycja X
        int startY = 50; // początkowa pozycja Y
        int gapX = 10; // poziomy odstęp między przyciskami
        int gapY = 10; // pionowy odstęp między przyciskami

//        // Pierwsza kolumna
        buttonEmpty = createButton("", startX, startY, buttonWidth, buttonHeight);
//        buttonHex = createButton("Hex", startX, startY + (buttonHeight + gapY), buttonWidth, buttonHeight);
//        buttonDec = createButton("Dec", startX, startY + 2 * (buttonHeight + gapY), buttonWidth, buttonHeight);
//        buttonOct = createButton("Oct", startX, startY + 3 * (buttonHeight + gapY), buttonWidth, buttonHeight);
//        buttonBin = createButton("Bin", startX, startY + 4 * (buttonHeight + gapY), buttonWidth, buttonHeight);

        buttonMod = createButton("Mod", startX, startY, buttonWidth, buttonHeight);
        buttonA = createButton("A", startX + (buttonWidth + gapX), startY, buttonWidth, buttonHeight);
        buttonMC = createButton("MC", startX + 2 * (buttonWidth + gapX), startY, buttonWidth, buttonHeight);
        buttonMR = createButton("MR", startX + 3 * (buttonWidth + gapX), startY, buttonWidth, buttonHeight);
        buttonMS = createButton("MS", startX + 4 * (buttonWidth + gapX), startY, buttonWidth, buttonHeight);
        buttonMPlus = createButton("M+", startX + 5 * (buttonWidth + gapX), startY, buttonWidth, buttonHeight);
        buttonMMinus = createButton("M-", startX + 6 * (buttonWidth + gapX), startY, buttonWidth, buttonHeight);

        // Drugi rząd
        buttonOpen = createButton("(", startX, startY + (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonB = createButton("B", startX + (buttonWidth + gapX), startY + (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonDel = createButton("←", startX + 2 * (buttonWidth + gapX), startY + (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonCE = createButton("CE", startX + 3 * (buttonWidth + gapX), startY + (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonCC = createButton("C", startX + 4 * (buttonWidth + gapX), startY + (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonSign = createButton("±", startX + 5 * (buttonWidth + gapX), startY + (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonUnused2 = createButton("√", startX + 6 * (buttonWidth + gapX), startY + (buttonHeight + gapY), buttonWidth, buttonHeight);

        // Trzeci rząd
        buttonRoL = createButton("RoL", startX, startY + 2 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonRoR = createButton("RoR", startX + (buttonWidth + gapX), startY + 2 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button7 = createButton("7", startX + 2 * (buttonWidth + gapX), startY + 2 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button8 = createButton("8", startX + 3 * (buttonWidth + gapX), startY + 2 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button9 = createButton("9", startX + 4 * (buttonWidth + gapX), startY + 2 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonDiv = createButton("/", startX + 5 * (buttonWidth + gapX), startY + 2 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonUnused3 = createButton("%", startX + 6 * (buttonWidth + gapX), startY + 2 * (buttonHeight + gapY), buttonWidth, buttonHeight);

        // Czwarty rząd
        buttonOr = createButton("Or", startX, startY + 3 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonXor = createButton("Xor", startX + (buttonWidth + gapX), startY + 3 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button4 = createButton("4", startX + 2 * (buttonWidth + gapX), startY + 3 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button5 = createButton("5", startX + 3 * (buttonWidth + gapX), startY + 3 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button6 = createButton("6", startX + 4 * (buttonWidth + gapX), startY + 3 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonMulti = createButton("*", startX + 5 * (buttonWidth + gapX), startY + 3 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonUnused4 = createButton("1/x", startX + 6 * (buttonWidth + gapX), startY + 3 * (buttonHeight + gapY), buttonWidth, buttonHeight);

        // Piąty rząd
        buttonLsh = createButton("Lsh", startX, startY + 4 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonRsh = createButton("Rsh", startX + (buttonWidth + gapX), startY + 4 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button1 = createButton("1", startX + 2 * (buttonWidth + gapX), startY + 4 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button2 = createButton("2", startX + 3 * (buttonWidth + gapX), startY + 4 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button3 = createButton("3", startX + 4 * (buttonWidth + gapX), startY + 4 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonMin = createButton("-", startX + 5 * (buttonWidth + gapX), startY + 4 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonUnused1 = createButton(",", startX + 6 * (buttonWidth + gapX), startY + 4 * (buttonHeight + gapY), buttonWidth, buttonHeight);

        // Szósty rząd
        buttonNot = createButton("Not", startX, startY + 5 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonAnd = createButton("And", startX + (buttonWidth + gapX), startY + 5 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonF = createButton("F", startX + 2 * (buttonWidth + gapX), startY + 5 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        button0 = createButton("0", startX + 3 * (buttonWidth + gapX), startY + 5 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonEqual = createButton("=", startX + 4 * (buttonWidth + gapX), startY + 5 * (buttonHeight + gapY), buttonWidth, buttonHeight);
        buttonPlus = createButton("+", startX + 5 * (buttonWidth + gapX), startY + 5 * (buttonHeight + gapY), buttonWidth, buttonHeight);










        button5.setBounds(100, 150, 30, 25);

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calc.numericInput("5");
            }
        });
        frame.add(button5);

        // Ustawienie rozmiaru ramki
        frame.setSize(300, 300);

        // Dodanie obsługi zamykania okna
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Początkowo ramka jest niewidoczna
        frame.setVisible(false);
    }
    private Button createButton(String label, int x, int y, int width, int height) {
        Button button = new Button(label);
        button.setBounds(x, y, width, height);
        frame.add(button);
        return button;
    }
    public void display() {
        frame.setVisible(true);

    }
    public void setDisplayLabel(String text) {
        //System.out.println(text);
        //System.out.println(label.getText());
        if (text.length() > 1) {
            text= text.substring(1);
        }
        label.setText(text);
        //System.out.println(label.getText());
        //label.repaint();
    }
}
