package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SinhCalculatorGui {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SinhCalculatorGui::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("sinh(x) Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("sinh(x) Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Input Panel (No dropdown)
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel inputLabel = new JLabel("Enter x: ");
        JTextField inputField = new JTextField(15);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        // Compute Button
        JButton computeButton = new JButton("Compute");

        // Result
        JLabel resultLabel = new JLabel("Result will be displayed here", JLabel.CENTER);

        // Bottom Button Panel
        JPanel buttonPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        // Center panel to hold input, compute, result
        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.add(inputPanel);
        centerPanel.add(computeButton);
        centerPanel.add(resultLabel);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Action Listeners
        computeButton.addActionListener(e -> {
            try {
                double x = Double.parseDouble(inputField.getText());
                double result = computeSinh(x);
                resultLabel.setText(String.format("sinh(%.4f) = %.6f", x, result));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a real number.");
            }
        });

        resetButton.addActionListener(e -> {
            inputField.setText("");
            resultLabel.setText("Result will be displayed here");
        });

        exitButton.addActionListener(e -> System.exit(0));
    }

    // Manual implementation using Taylor series expansion
    private static double computeSinh(double x) {
        double sum = 0;
        int terms = 10; // More terms = higher accuracy

        for (int n = 0; n < terms; n++) {
            double numerator = power(x, 2 * n + 1);
            double denominator = factorial(2 * n + 1);
            sum += numerator / denominator;
        }

        return sum;
    }

    private static double factorial(int n) {
        double result = 1.0;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static double power(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}