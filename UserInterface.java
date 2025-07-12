import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserInterface extends JFrame implements ActionListener {

    private final JTextField display;
    private Operations operations;
    private String firstOperand = "";
    private String operator = "";
    private String secondOperand = "";
    private boolean isResultDisplayed = false;

    public UserInterface() {
        operations = new Operations();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        add(mainPanel);

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createCompoundBorder(
            display.getBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        mainPanel.add(display, BorderLayout.NORTH);

        // A container for all button panels
        JPanel allButtonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.add(allButtonsPanel, BorderLayout.CENTER);

        // Scientific buttons panel
        JPanel scientificButtonsPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        scientificButtonsPanel.setBorder(BorderFactory.createTitledBorder("Scientific"));
        allButtonsPanel.add(scientificButtonsPanel);

        String[] scientificButtonLabels = {
            "sin", "cos", "tan", "log",
            "sqrt", "pow", "exp", "!",
            "deg", "rad", "1/x", "π",
            "x^2", "x^3", "AC", "C"
        };

        for (String label : scientificButtonLabels) {
            JButton button = createStyledButton(label, new Color(200, 220, 255), new Font("Arial", Font.PLAIN, 16));
            scientificButtonsPanel.add(button);
        }

        // Numeric and basic operations panel
        JPanel numericButtonsPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        numericButtonsPanel.setBorder(BorderFactory.createTitledBorder("Basic"));
        allButtonsPanel.add(numericButtonsPanel);

        String[] numericButtonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String label : numericButtonLabels) {
            JButton button;
            if (label.matches("[0-9]")) {
                 button = createStyledButton(label, Color.WHITE, new Font("Arial", Font.BOLD, 18));
            } else if (label.equals("=")) {
                 button = createStyledButton(label, new Color(255, 150, 150), new Font("Arial", Font.PLAIN, 18));
            }
            else {
                 button = createStyledButton(label, new Color(220, 220, 220), new Font("Arial", Font.PLAIN, 18));
            }
            numericButtonsPanel.add(button);
        }
    }

    private JButton createStyledButton(String label, Color bgColor, Font font) {
        JButton button = new JButton(label);
        button.setFont(font);
        button.setBackground(bgColor);
        button.addActionListener(this);
        button.setFocusPainted(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (isResultDisplayed) {
            display.setText("");
            isResultDisplayed = false;
        }

        if (command.matches("[0-9]")) {
            if (!operator.isEmpty()) {
                secondOperand += command;
            } else {
                firstOperand += command;
            }
            display.setText(firstOperand + operator + secondOperand);
        } else if (command.equals(".")) {
            if (!operator.isEmpty()) {
                if (!secondOperand.contains(".")) {
                    secondOperand += ".";
                }
            } else {
                if (!firstOperand.contains(".")) {
                    firstOperand += ".";
                }
            }
            display.setText(firstOperand + operator + secondOperand);
        } else if (command.equals("C")) {
            if (!secondOperand.isEmpty()) {
                secondOperand = secondOperand.substring(0, secondOperand.length() - 1);
            } else if (!operator.isEmpty()) {
                operator = "";
            } else if (!firstOperand.isEmpty()) {
                firstOperand = firstOperand.substring(0, firstOperand.length() - 1);
            }
            display.setText(firstOperand + operator + secondOperand);
        } else if (command.equals("AC")) {
            firstOperand = "";
            operator = "";
            secondOperand = "";
            display.setText("");
        } else if (command.equals("=")) {
            if (!firstOperand.isEmpty() && !operator.isEmpty() && !secondOperand.isEmpty()) {
                calculate();
            }
        } else if (isOperator(command)) {
            if (!firstOperand.isEmpty()) {
                operator = command;
                display.setText(firstOperand + operator + secondOperand);
            }
        } else {
            handleScientificFunction(command);
        }
    }

    private boolean isOperator(String command) {
        return command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/") || command.equals("pow");
    }

    private void calculate() {
        try {
            double num1 = Double.parseDouble(firstOperand);
            double num2 = Double.parseDouble(secondOperand);
            double result = 0;

            switch (operator) {
                case "+":
                    result = operations.add(num1, num2);
                    break;
                case "-":
                    result = operations.subtract(num1, num2);
                    break;
                case "*":
                    result = operations.multiply(num1, num2);
                    break;
                case "/":
                    result = operations.divide(num1, num2);
                    break;
                case "pow":
                    result = operations.pow(num1, num2);
                    break;
            }
            display.setText(String.valueOf(result));
            firstOperand = String.valueOf(result);
            operator = "";
            secondOperand = "";
            isResultDisplayed = true;
        } catch (IllegalArgumentException ex) {
            display.setText("Error");
            firstOperand = "";
            operator = "";
            secondOperand = "";
            isResultDisplayed = true;
        }
    }

    private void handleScientificFunction(String command) {
        if (!firstOperand.isEmpty() && operator.isEmpty()) {
            try {
                double num = Double.parseDouble(firstOperand);
                double result = 0;

                switch (command) {
                    case "sin" -> result = operations.sin(Math.toRadians(num));
                    case "cos" -> result = operations.cos(Math.toRadians(num));
                    case "tan" -> result = operations.tan(Math.toRadians(num));
                    case "log" -> result = operations.log(num);
                    case "sqrt" -> result = operations.sqrt(num);
                    case "exp" -> result = operations.exp(num);
                    case "!" -> result = operations.factorial((int) num);
                    case "deg" -> result = operations.radiansToDegrees(num);
                    case "rad" -> result = operations.degreesToRadians(num);
                    case "x^2" -> result = operations.pow(num, 2);
                    case "x^3" -> result = operations.pow(num, 3);
                    case "1/x" -> result = operations.divide(1, num);
                    case "π" -> {
                        result = Math.PI;
                        firstOperand = ""; 
                    }
                }
                if (command.equals("π")) {
                    display.setText(String.valueOf(result));
                    firstOperand = String.valueOf(result);
                } else {
                    display.setText(String.valueOf(result));
                    firstOperand = String.valueOf(result);
                }
                isResultDisplayed = true;
            } catch (IllegalArgumentException ex) {
                display.setText("Error");
                firstOperand = "";
                isResultDisplayed = true;
            }
        } else if (command.equals("π")) {
             display.setText(String.valueOf(Math.PI));
             firstOperand = String.valueOf(Math.PI);
             isResultDisplayed = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserInterface::new);
    }
}



