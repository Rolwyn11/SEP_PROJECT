/**
 * A GUI-based calculator to compute sinh(x) using Taylor series.
 * Version: 1.0.0
 * Author: Rolwyn Raju
 * Date: August 5, 2025
 * Course: SOEN 6011 - Software Engineering Processes
 */

package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * A GUI-based calculator to compute sinh(x) using Taylor series.
 */
public class SinhCalculatorGuiV1 {

  /**
   * Main entry point.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(SinhCalculatorGuiV1::createAndShowGui);
  }

  /**
   * Creates and displays the GUI.
   */
  private static void createAndShowGui() {
    JFrame frame = new JFrame("sinh(x) Calculator");
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(500, 300);
    frame.setLayout(new BorderLayout(10, 10));
    frame.setLocationRelativeTo(null); // Center the frame on screen

    Font titleFont = new Font("SansSerif", Font.BOLD, 20);
    final Font standardFont = new Font("SansSerif", Font.PLAIN, 14);

    // Title
    JLabel titleLabel = new JLabel("sinh(x) Calculator", javax.swing.SwingConstants.CENTER);
    titleLabel.setFont(titleFont);
    titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
    frame.add(titleLabel, BorderLayout.NORTH);

    // Input Panel
    final JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    JLabel inputLabel = new JLabel("Enter x: ");
    inputLabel.setFont(standardFont);
    JTextField inputField = new JTextField(15);
    inputField.setFont(standardFont);
    inputField.setToolTipText("Enter a real number for x");

    inputPanel.add(inputLabel);
    inputPanel.add(inputField);

    // Accessibility for input
    inputLabel.setLabelFor(inputField);
    inputField.getAccessibleContext().setAccessibleName("X Input Field");
    inputField.getAccessibleContext().setAccessibleDescription("Enter a real number for x");

    inputPanel.setBorder(new EmptyBorder(5, 20, 5, 20));

    // Result Label
    JLabel resultLabel =
            new JLabel("Result will be displayed here", javax.swing.SwingConstants.CENTER);
    resultLabel.setFont(standardFont);
    resultLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

    // Buttons Panel (Compute, Reset, Exit)
    JButton computeButton = new JButton("Compute");
    JButton resetButton = new JButton("Reset");
    JButton exitButton = new JButton("Exit");

    computeButton.setFont(standardFont);
    resetButton.setFont(standardFont);
    exitButton.setFont(standardFont);

    // Accessibility for buttons
    computeButton.getAccessibleContext().setAccessibleName("Compute Button");
    computeButton.getAccessibleContext().setAccessibleDescription("Computes sinh(x) when clicked");

    resetButton.getAccessibleContext().setAccessibleName("Reset Button");
    resetButton.getAccessibleContext().setAccessibleDescription("Clears input and result");

    exitButton.getAccessibleContext().setAccessibleName("Exit Button");
    exitButton.getAccessibleContext().setAccessibleDescription("Closes the application");

    computeButton.setToolTipText("Click to compute sinh(x)");
    resetButton.setToolTipText("Clear input and result");
    exitButton.setToolTipText("Exit the application");

    // Set background and foreground for buttons
    computeButton.setBackground(new Color(100, 180, 255));
    resetButton.setBackground(new Color(200, 230, 201));
    exitButton.setBackground(new Color(255, 204, 188));

    computeButton.setForeground(Color.BLACK);
    resetButton.setForeground(Color.BLACK);
    exitButton.setForeground(Color.BLACK);

    // Set size
    computeButton.setPreferredSize(new Dimension(100, 30));
    resetButton.setPreferredSize(new Dimension(100, 30));
    exitButton.setPreferredSize(new Dimension(100, 30));

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    buttonPanel.add(computeButton);
    buttonPanel.add(resetButton);
    buttonPanel.add(exitButton);
    buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    // Center Panel = input + result
    JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
    centerPanel.add(inputPanel);
    centerPanel.add(resultLabel);
    centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    frame.add(centerPanel, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    frame.setVisible(true);
    frame.getRootPane().setDefaultButton(computeButton);

    // Action Listeners
    computeButton.addActionListener(e -> {
      try {
        double x = Double.parseDouble(inputField.getText());
        double result = computeSinh(x);
        resultLabel.setText(String.format("sinh(%.4f) = %.6f", x, result));
      } catch (NumberFormatException ex) {
        ex.getMessage();
        resultLabel.setText("Invalid input. Please enter a real number.");
      }
    });

    resetButton.addActionListener(e -> {
      inputField.setText("");
      resultLabel.setText("Result will be displayed here");
    });

    exitButton.addActionListener(e -> System.exit(0));
  }

  /**
   * Computes sinh(x) using Taylor series expansion.
   *
   * @param x the input value
   * @return approximation of sinh(x)
   */
  private static double computeSinh(double x) {
    double sum = 0;
    int terms = 10;
    for (int n = 0; n < terms; n++) {
      double numerator = power(x, 2 * n + 1);
      double denominator = factorial(2 * n + 1);
      sum += numerator / denominator;
    }
    return sum;
  }

  /**
   * Computes factorial of a number.
   *
   * @param n input integer
   * @return factorial as double
   */
  private static double factorial(int n) {
    double result = 1.0;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * Computes power using multiplication.
   *
   * @param base     base value
   * @param exponent exponent
   * @return base raised to exponent
   */
  private static double power(double base, int exponent) {
    double result = 1.0;
    for (int i = 0; i < exponent; i++) {
      result *= base;
    }
    return result;
  }
}