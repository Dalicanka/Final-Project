package Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Financial Statement Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(7, 2, 10, 10));

        // Labels and text fields for input
        JLabel assetsLabel = new JLabel("Enter Assets:");
        JTextField assetsField = new JTextField();

        JLabel liabilitiesLabel = new JLabel("Enter Liabilities:");
        JTextField liabilitiesField = new JTextField();

        JLabel incomeLabel = new JLabel("Enter Income:");
        JTextField incomeField = new JTextField();

        JLabel expensesLabel = new JLabel("Enter Expenses:");
        JTextField expensesField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double assets = Double.parseDouble(assetsField.getText());
                    double liabilities = Double.parseDouble(liabilitiesField.getText());
                    double income = Double.parseDouble(incomeField.getText());
                    double expenses = Double.parseDouble(expensesField.getText());

                    double netWorth = assets - liabilities;
                    double netIncome = income - expenses;

                    StringBuilder sb = new StringBuilder();
                    sb.append("<html>");
                    sb.append("Net Worth: ").append(netWorth).append("<br>");
                    sb.append("Income Statement:<br>");
                    sb.append("  Income: ").append(income).append("<br>");
                    sb.append("  Expenses: ").append(expenses).append("<br>");
                    sb.append("  Net Income: ").append(netIncome).append("<br>");
                    sb.append("Assets: ").append(assets).append("<br>");
                    sb.append("Liabilities: ").append(liabilities);
                    sb.append("</html>");

                    // Show result in a new window
                    JFrame resultFrame = new JFrame("Calculation Result");
                    JLabel resultContent = new JLabel(sb.toString(), SwingConstants.CENTER);
                    resultFrame.add(resultContent);
                    resultFrame.setSize(350, 250);
                    resultFrame.setLocationRelativeTo(frame);
                    resultFrame.setVisible(true);

                } catch (NumberFormatException ex) {
                    // Show error in a new window
                    JFrame errorFrame = new JFrame("Input Error");
                    JLabel errorLabel = new JLabel("Please enter valid numbers for all fields.", SwingConstants.CENTER);
                    errorFrame.add(errorLabel);
                    errorFrame.setSize(300, 100);
                    errorFrame.setLocationRelativeTo(frame);
                    errorFrame.setVisible(true);
                }
            }
        });

        // Add components to frame
        frame.add(assetsLabel);
        frame.add(assetsField);
        frame.add(liabilitiesLabel);
        frame.add(liabilitiesField);
        frame.add(incomeLabel);
        frame.add(incomeField);
        frame.add(expensesLabel);
        frame.add(expensesField);
        frame.add(new JLabel()); // empty cell
        frame.add(calculateButton);
        frame.add(new JLabel()); // empty cell
        frame.add(resultLabel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}