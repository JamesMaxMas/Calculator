import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display {
    Frame frame;
    Label label;
    Label labelBit1;
    Label labelBit2;
    Panel buttonPanel; // Panel na przyciski
    Panel switchPanel1;
    Panel switchPanel2;
    Calculator calc;
    int buttonWidth = 50;
    int buttonHeight = 30;
    int startX = 10; // początkowa pozycja X
    int startY = 10; // początkowa pozycja Y
    int gapX = 10; // poziomy odstęp między
    int gapY = 10;
    public Display(Calculator calc) {
        this.calc = calc;
        frame = new Frame("Basic Program");
        // Etykieta wyświetlacza

        // Panel na przyciski
        buttonPanel = new Panel();
        buttonPanel.setLayout(null);
        // Dodanie przycisków do panelu
        addButton(buttonPanel, "", (e -> calc.numericInput("")));
        addButton(buttonPanel, "Mod", (e -> calc.addAction(new MOD())));
        addButton(buttonPanel, "A", (e -> calc.numericInput("A")));
        addButton(buttonPanel, "MC", (e -> calc.mc()));
        addButton(buttonPanel, "MR", (e -> calc.mr()));
        addButton(buttonPanel, "MS", (e -> calc.ms()));
        addButton(buttonPanel, "M+", (e -> calc.mPlus()));
        addButton(buttonPanel, "M-", (e -> calc.mMinus()));
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "(", (e -> calc.numericInput("")));//todo
        addButton(buttonPanel, ")", (e -> calc.numericInput("")));//todo
        addButton(buttonPanel, "B", (e -> calc.numericInput("B")));
        addButton(buttonPanel, "<--", (e -> calc.backspace()));
        addButton(buttonPanel, "CE", (e -> calc.ce()));
        addButton(buttonPanel, "C", (e -> calc.clear()));
        addButton(buttonPanel, "+/-", (e -> calc.changeSign()));
        addButton(buttonPanel, "v--", (e -> calc.numericInput("")));
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "RoL", (e -> calc.RoL()));
        addButton(buttonPanel, "RoR", (e -> calc.RoR()));
        addButton(buttonPanel, "C", (e -> calc.numericInput("C")));
        addButton(buttonPanel, "7", (e -> calc.numericInput("7")));
        addButton(buttonPanel, "8", (e -> calc.numericInput("8")));
        addButton(buttonPanel, "9", (e -> calc.numericInput("9")));
        addButton(buttonPanel, "/", (e -> calc.addAction(new DIV())));
        addButton(buttonPanel, "%", (e -> calc.numericInput("")));;
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "Or", (e -> calc.addAction(new OR())));
        addButton(buttonPanel, "Xor", (e -> calc.addAction(new XOR())));
        addButton(buttonPanel, "D", (e -> calc.numericInput("D")));
        addButton(buttonPanel, "4", (e -> calc.numericInput("4")));
        addButton(buttonPanel, "5", (e -> calc.numericInput("5")));
        addButton(buttonPanel, "6", (e -> calc.numericInput("6")));
        addButton(buttonPanel, "*", (e -> calc.addAction(new MUL())));
        addButton(buttonPanel, "1/x", (e -> calc.numericInput("")));
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "Lsh", (e -> calc.addAction(new LSH())));
        addButton(buttonPanel, "Rsh", (e -> calc.addAction(new RSH())));
        addButton(buttonPanel, "E", (e -> calc.numericInput("E")));
        addButton(buttonPanel, "1", (e -> calc.numericInput("1")));
        addButton(buttonPanel, "2", (e -> calc.numericInput("2")));
        addButton(buttonPanel, "3", (e -> calc.numericInput("3")));
        addButton(buttonPanel, "-", (e -> calc.addAction(new Sub())));
        addButton(buttonPanel, "=", (e -> calc.wykonaj()));
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "Not", (e -> calc.addAction(new NOT())));
        addButton(buttonPanel, "And", (e -> calc.addAction(new AND())));
        addButton(buttonPanel, "F", (e -> calc.numericInput("F")));
        addButton(buttonPanel, "0", (e -> calc.numericInput("0")));
        addButton(buttonPanel, "0", (e -> calc.numericInput("0")));
        addButton(buttonPanel, ",", (e -> calc.numericInput("")));
        addButton(buttonPanel, "+", (e -> calc.addAction(new Add())));
        addButton(buttonPanel, "=", (e -> calc.wykonaj()));


        // Panele przełączników
        switchPanel1 = createSwitchPanelNumSystem( "Hex", "Dec", "Oct", "Bin");
        switchPanel2 = createSwitchPanelMemSize( "Qword", "Dword", "Word", "Byte");
        // Główna ramka
        frame.setLayout(new BorderLayout());

        label = new Label("0", Label.RIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBackground(Color.LIGHT_GRAY);

        labelBit1 = new Label("00000", Label.RIGHT);
        labelBit1.setFont(new Font("Arial", Font.BOLD, 20));
        labelBit1.setBackground(Color.LIGHT_GRAY);

        labelBit2 = new Label("00000", Label.RIGHT);
        labelBit2.setFont(new Font("Arial", Font.BOLD, 20));
        labelBit2.setBackground(Color.LIGHT_GRAY);

//        labelBit3 = new Label("00000", Label.CENTER);
//        labelBit3.setFont(new Font("Arial", Font.BOLD, 20));
//        labelBit3.setBackground(Color.LIGHT_GRAY);
//
//        labelBit2 = new Label("00000", Label.CENTER);
//        labelBit2.setFont(new Font("Arial", Font.BOLD, 20));
//        labelBit2.setBackground(Color.LIGHT_GRAY);

        Panel topPanel = new Panel(new GridLayout(3, 1));
        topPanel.add(label);
        topPanel.add(labelBit1);
        topPanel.add(labelBit2);
        frame.add(topPanel, BorderLayout.NORTH);
//        Panel topPanel2 = new Panel(new GridLayout(2, 2));

//        frame.add(topPanel2, BorderLayout.NORTH);
//        frame.add(label, BorderLayout.NORTH);
//        frame.add(labelBit, BorderLayout.NORTH);

        frame.add(buttonPanel, BorderLayout.CENTER);
        // Panel boczny
        // Panel boczny
        Panel sidePanel = new Panel(new GridLayout(2, 1)); // GridLayout z 2 wierszami i 1 kolumną
        //Panel emptypanel = new Panel();
        //sidePanel.add(emptypanel);
        //sidePanel.add(emptypanel);
        sidePanel.add(switchPanel1);
        sidePanel.add(switchPanel2);
// Dodanie panelu bocznego do lewej strony ramki
        frame.add(sidePanel, BorderLayout.WEST);
// Rozmiar i zamykanie
        frame.setSize(560, 410);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
    //todo dla przyciskó specjalnych przypisaćich role itd
    private void addButton(Panel panel, String label, ActionListener t) {
        Button button = new Button(label);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBounds(startX, startY, buttonWidth, buttonHeight);
        startX += buttonWidth+gapX;
        button.addActionListener(t);
        panel.add(button);
    }
    private Panel createSwitchPanelNumSystem(String... options) {
        Panel panel = new Panel(new GridLayout(options.length, 1, 0, 10)); // GridLayout dla przełączników
        panel.setBackground(Color.LIGHT_GRAY);
        CheckboxGroup group = new CheckboxGroup();
        for (String option : options) {
            Checkbox checkbox;
            if (option =="Dec"){
                checkbox = new Checkbox(option, group, true);
            }else {
                checkbox = new Checkbox(option, group, false);
            }
            checkbox.addItemListener(e -> calc.setSystem(NumSystem.stringToNumSystem(option)));
            panel.add(checkbox);
        }
        return panel;
    }
    private Panel createSwitchPanelMemSize(String... options) {
        Panel panel = new Panel(new GridLayout(options.length, 1, 0, 10)); // GridLayout dla przełączników
        panel.setBackground(Color.LIGHT_GRAY);
        CheckboxGroup group = new CheckboxGroup();
        for (String option : options) {
            Checkbox checkbox;
            if (option =="Qword"){
                checkbox = new Checkbox(option, group, true);
            }else {
                checkbox = new Checkbox(option, group, false);
            }

            checkbox.addItemListener(e -> calc.setMemorySize(MemSize.stringToMemSize(option)));
            panel.add(checkbox);
        }
        return panel;
    }

    public void display() {
        frame.setVisible(true);
    }
    public void update(){
//        System.out.println(calc.getValue());
//        System.out.println(calc.getValueString());
        label.setText(calc.getValueString());

//        System.out.println(calc.getMemorySize().toInt());
        String s = Long.toBinaryString(calc.getValue());

        int desiredLength = calc.getMemorySize().toInt();
        if (s.length() > desiredLength) {
            s = s.substring(s.length() - desiredLength);
        }
        String paddedString = String.format("%" + desiredLength + "s", s).replace(' ', '0');
        System.out.println(paddedString);
        String s1, s2;

        if (paddedString.length() <= 32) {
            s1 = "";
            s2 = paddedString;
        }
        else {
            s1 = paddedString.substring(0, paddedString.length() - 32); // Wszystko przed ostatnimi 32 znakami
            s2 = paddedString.substring(paddedString.length() - 32);
        }
//        System.out.println(s1);
//        System.out.println(s2);

        StringBuilder sb = new StringBuilder();
        for (int j = 0;j < s1.length(); j++) {
            sb.append(s1.charAt(j));
            // Po każdych czterech znakach dodaj dwie spacje, jeśli nie jest to koniec stringa
            if ((j + 1) % 4 == 0 && j + 1 < s1.length()) {
                sb.append("  ");
            }
        }
        s1 = sb.toString();

        sb = new StringBuilder();
        for (int j = 0;j < s2.length(); j++) {
            sb.append(s2.charAt(j));
            // Po każdych czterech znakach dodaj dwie spacje, jeśli nie jest to koniec stringa
            if ((j + 1) % 4 == 0 && j + 1 < s2.length()) {
                sb.append("  ");
            }
        }
        s2 = sb.toString();
        labelBit1.setText(s1);
        labelBit2.setText(s2);
//        System.out.println(s1);
//        System.out.println(s2);
    }
    public void setDisplayLabel(String text) {
        label.setText(text);

//        System.out.println(calc.getMemorySize().toInt());
        String s = Long.toBinaryString(calc.getValue());
        int desiredLength = calc.getMemorySize().toInt();
        if (s.length() > desiredLength) {
            s = s.substring(s.length() - desiredLength);
        }
        String paddedString = String.format("%" + desiredLength + "s", s).replace(' ', '0');
//        System.out.println(paddedString);
        String s1, s2;

        if (paddedString.length() <= 32) {
            s1 = "";
            s2 = paddedString;
        }
        else {
            s1 = paddedString.substring(0, paddedString.length() - 32); // Wszystko przed ostatnimi 32 znakami
            s2 = paddedString.substring(paddedString.length() - 32);
        }
//        System.out.println(s1);
//        System.out.println(s2);

        StringBuilder sb = new StringBuilder();
        for (int j = 0;j < s1.length(); j++) {
            sb.append(s1.charAt(j));
            // Po każdych czterech znakach dodaj dwie spacje, jeśli nie jest to koniec stringa
            if ((j + 1) % 4 == 0 && j + 1 < s1.length()) {
                sb.append("  ");
            }
        }
        s1 = sb.toString();

        sb = new StringBuilder();
        for (int j = 0;j < s2.length(); j++) {
            sb.append(s2.charAt(j));
            // Po każdych czterech znakach dodaj dwie spacje, jeśli nie jest to koniec stringa
            if ((j + 1) % 4 == 0 && j + 1 < s2.length()) {
                sb.append("  ");
            }
        }
        s2 = sb.toString();
        labelBit1.setText(s1);
        labelBit2.setText(s2);
//        System.out.println(s1);
//        System.out.println(s2);

    }

}