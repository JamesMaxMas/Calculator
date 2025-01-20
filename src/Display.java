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
        label = new Label("0", Label.RIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBackground(Color.LIGHT_GRAY);

        labelBit1 = new Label("00000", Label.CENTER);
        labelBit1.setFont(new Font("Arial", Font.BOLD, 20));
        labelBit1.setBackground(Color.LIGHT_GRAY);

        labelBit2 = new Label("00000", Label.CENTER);
        labelBit2.setFont(new Font("Arial", Font.BOLD, 20));
        labelBit2.setBackground(Color.LIGHT_GRAY);
        // Panel na przyciski
        buttonPanel = new Panel();
        buttonPanel.setLayout(null);
        // Dodanie przycisków do panelu
        addButton(buttonPanel, "", (e -> calc.numericInput("")));
        addButton(buttonPanel, "Mod", (e -> calc.numericInput("")));
        addButton(buttonPanel, "A", (e -> calc.numericInput("")));
        addButton(buttonPanel, "MC", (e -> calc.numericInput("")));
        addButton(buttonPanel, "MR", (e -> calc.numericInput("")));
        addButton(buttonPanel, "MS", (e -> calc.numericInput("")));
        addButton(buttonPanel, "M+", (e -> calc.numericInput("")));
        addButton(buttonPanel, "M-", (e -> calc.numericInput("")));
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "(", (e -> calc.numericInput("")));
        addButton(buttonPanel, ")", (e -> calc.numericInput("")));
        addButton(buttonPanel, "B", (e -> calc.numericInput("B")));
        addButton(buttonPanel, "<--", (e -> calc.numericInput("")));
        addButton(buttonPanel, "CE", (e -> calc.numericInput("")));
        addButton(buttonPanel, "C", (e -> calc.clear()));
        addButton(buttonPanel, "+/-", (e -> this.setDisplayLabel(Long.toString(Calculator.Operations.changeSign(calc.getValue()), calc.getSystem().toInt()))));
//        Long.toString(value, system.toInt()).toUpperCase();
        addButton(buttonPanel, "v--", (e -> calc.numericInput("")));
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "RoL", (e -> calc.numericInput("")));
        addButton(buttonPanel, "RoR", (e -> calc.numericInput("")));
        addButton(buttonPanel, "C", (e -> calc.numericInput("C")));
        addButton(buttonPanel, "7", (e -> calc.numericInput("7")));
        addButton(buttonPanel, "8", (e -> calc.numericInput("8")));
        addButton(buttonPanel, "9", (e -> calc.numericInput("9")));
        addButton(buttonPanel, "/", (e -> calc.numericInput("9")));
        addButton(buttonPanel, "%", (e -> calc.numericInput("9")));;
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "Or", (e -> calc.numericInput("")));
        addButton(buttonPanel, "Xor", (e -> calc.numericInput("")));
        addButton(buttonPanel, "D", (e -> calc.numericInput("D")));
        addButton(buttonPanel, "4", (e -> calc.numericInput("4")));
        addButton(buttonPanel, "5", (e -> calc.numericInput("5")));
        addButton(buttonPanel, "6", (e -> calc.numericInput("6")));
        addButton(buttonPanel, "*", (e -> calc.numericInput("")));
        addButton(buttonPanel, "1/x", (e -> calc.numericInput("")));
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "Lsh", (e -> calc.numericInput("")));
        addButton(buttonPanel, "Rsh", (e -> calc.numericInput("")));
        addButton(buttonPanel, "E", (e -> calc.numericInput("")));
        addButton(buttonPanel, "1", (e -> calc.numericInput("1")));
        addButton(buttonPanel, "2", (e -> calc.numericInput("")));
        addButton(buttonPanel, "3", (e -> calc.numericInput("")));
        addButton(buttonPanel, "-", (e -> calc.numericInput("")));
        addButton(buttonPanel, "=", (e -> calc.numericInput("")));
        startX = 10;
        startY += buttonHeight+gapY;
        addButton(buttonPanel, "Not", (e -> calc.numericInput("")));
        addButton(buttonPanel, "And", (e -> calc.numericInput("")));
        addButton(buttonPanel, "F", (e -> calc.numericInput("")));
        addButton(buttonPanel, "0", (e -> calc.numericInput("")));
        addButton(buttonPanel, "0", (e -> calc.numericInput("")));
        addButton(buttonPanel, ",", (e -> calc.numericInput("")));
        addButton(buttonPanel, "+", (e -> calc.numericInput("")));
        addButton(buttonPanel, "=", (e -> calc.numericInput("")));
        // Panele przełączników
        switchPanel1 = createSwitchPanelNumSystem( "Hex", "Dec", "Oct", "Bin");
        switchPanel2 = createSwitchPanelMemSize( "Qword", "Dword", "Word", "Byte");
        // Główna ramka
        frame.setLayout(new BorderLayout());
        Panel topPanel = new Panel(new GridLayout(3, 1));
        topPanel.add(label);
        topPanel.add(labelBit1);
        topPanel.add(labelBit2);
        frame.add(topPanel, BorderLayout.NORTH);
//        frame.add(label, BorderLayout.NORTH);
//        frame.add(labelBit, BorderLayout.NORTH);

        frame.add(buttonPanel, BorderLayout.CENTER);
        // Panel boczny
        // Panel boczny
        Panel sidePanel = new Panel(new GridLayout(3, 1)); // GridLayout z 2 wierszami i 1 kolumną
        Panel emptypanel = new Panel();
        sidePanel.add(emptypanel);
        //sidePanel.add(emptypanel);
        sidePanel.add(switchPanel1);
        sidePanel.add(switchPanel2);
// Dodanie panelu bocznego do lewej strony ramki
        frame.add(sidePanel, BorderLayout.WEST);
// Rozmiar i zamykanie
        frame.setSize(550, 410);
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
    public void setDisplayLabel(String text) {
        label.setText(text);
    }
    public void setDisplayLabelBit(String text) {
        labelBit1.setText(text);
        labelBit2.setText(text);
    }
}