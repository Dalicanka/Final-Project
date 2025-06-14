package Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

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
                    sb.append("Net Worth: ").append(netWorth).append("\n");
                    sb.append("Income Statement:\n");
                    sb.append("  Income: ").append(income).append("\n");
                    sb.append("  Expenses: ").append(expenses).append("\n");
                    sb.append("  Net Income: ").append(netIncome).append("\n");
                    sb.append("Assets: ").append(assets).append("\n");
                    sb.append("Liabilities: ").append(liabilities);

                    // Show result and pie chart in a new window
                    JFrame resultFrame = new JFrame("Calculation Result");
                    resultFrame.setLayout(new BorderLayout());

                    JLabel resultContent = new JLabel("<html>" + sb.toString().replace("\n", "<br>") + "</html>", SwingConstants.CENTER);
                    resultFrame.add(resultContent, BorderLayout.NORTH);

                    JPanel piePanel = new PieChartPanel(assets, liabilities, income, expenses);
                    resultFrame.add(piePanel, BorderLayout.CENTER);

                    resultFrame.setSize(400, 450);
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

// PieChartPanel class to draw a simple pie chart
class PieChartPanel extends JPanel {
    private double assets, liabilities, income, expenses;

    public PieChartPanel(double assets, double liabilities, double income, double expenses) {
        this.assets = assets;
        this.liabilities = liabilities;
        this.income = income;
        this.expenses = expenses;
        setPreferredSize(new Dimension(300, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double total = assets + liabilities + income + expenses;
        if (total == 0) return;

        int width = 200;
        int height = 200;
        int x = 50;
        int y = 10;

        int startAngle = 0;

        // Assets slice
        int arcAngle = (int) Math.round((assets / total) * 360);
        g.setColor(Color.GREEN);
        g.fillArc(x, y, width, height, startAngle, arcAngle);
        startAngle += arcAngle;

        // Liabilities slice
        arcAngle = (int) Math.round((liabilities / total) * 360);
        g.setColor(Color.RED);
        g.fillArc(x, y, width, height, startAngle, arcAngle);
        startAngle += arcAngle;

        // Income slice
        arcAngle = (int) Math.round((income / total) * 360);
        g.setColor(Color.BLUE);
        g.fillArc(x, y, width, height, startAngle, arcAngle);
        startAngle += arcAngle;

        // Expenses slice
        arcAngle = 360 - startAngle; // Ensure full circle
        g.setColor(Color.ORANGE);
        g.fillArc(x, y, width, height, startAngle, arcAngle);

        // Legend
        g.setColor(Color.BLACK);
        g.drawString("Assets", x + width + 10, y + 20);
        g.setColor(Color.GREEN);
        g.fillRect(x + width + 60, y + 10, 15, 15);

        g.setColor(Color.BLACK);
        g.drawString("Liabilities", x + width + 10, y + 40);
        g.setColor(Color.RED);
        g.fillRect(x + width + 60, y + 30, 15, 15);

        g.setColor(Color.BLACK);
        g.drawString("Income", x + width + 10, y + 60);
        g.setColor(Color.BLUE);
        g.fillRect(x + width + 60, y + 50, 15, 15);

        g.setColor(Color.BLACK);
        g.drawString("Expenses", x + width + 10, y + 80);
        g.setColor(Color.ORANGE);
        g.fillRect(x + width + 60, y + 70, 15, 15);
    }
}