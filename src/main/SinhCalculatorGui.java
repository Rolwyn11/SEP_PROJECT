package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * A GUI-based calculator to compute sinh(x) using Taylor series.
 */
public class SinhCalculatorGui {

  /**
   * Main entry point.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(SinhCalculatorGui::createAndShowGui);
  }

  /**
   * Creates and displays the GUI.
   */
  private static void createAndShowGui() {
    JFrame frame = new JFrame("sinh(x) Calculator");
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(450, 250);
    frame.setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("sinh(x) Calculator", javax.swing.SwingConstants.CENTER);
    titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
    frame.add(titleLabel, BorderLayout.NORTH);

    JPanel inputPanel = new JPanel(new FlowLayout());
    JLabel inputLabel = new JLabel("Enter x: ");
    JTextField inputField = new JTextField(15);
    inputPanel.add(inputLabel);
    inputPanel.add(inputField);

    // Compute Button + Result (moved closer to usage)
    final JButton computeButton = new JButton("Compute");
    final JLabel resultLabel = new JLabel("Result will be displayed here", javax.swing.SwingConstants.CENTER);

    JPanel buttonPanel = new JPanel();
    JButton resetButton = new JButton("Reset");
    JButton exitButton = new JButton("Exit");
    buttonPanel.add(resetButton);
    buttonPanel.add(exitButton);

    JPanel centerPanel = new JPanel(new GridLayout(3, 1));
    centerPanel.add(inputPanel);
    centerPanel.add(computeButton);
    centerPanel.add(resultLabel);

    frame.add(centerPanel, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.setVisible(true);

    computeButton.addActionListener(e -> {
      try {
        double x = Double.parseDouble(inputField.getText());
        double result = computeSinh(x);
        resultLabel.setText(String.format("sinh(%.4f) = %.6f", x, result));
      } catch (NumberFormatException _) {
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