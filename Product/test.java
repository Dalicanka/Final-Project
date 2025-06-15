package Product;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class test {
    // Store user profile info
    static String userName = "";
    static String userAge = "";
    static String userEmail = "";

    public static void main(String[] args) {
        // Main window with options
        JFrame mainFrame = new JFrame("Money Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(350, 200);
        mainFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton addProfileBtn = new JButton("Add User Profile");
        JButton addFinancialBtn = new JButton("Add User Financial Info");
        JButton helpBtn = new JButton("Help");

        mainFrame.add(addProfileBtn);
        mainFrame.add(addFinancialBtn);
        mainFrame.add(helpBtn);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        // Add User Profile
        addProfileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame profileFrame = new JFrame("User Profile");
                profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                profileFrame.setSize(400, 250);
                profileFrame.setLayout(new GridLayout(4, 2, 10, 10));

                JLabel nameLabel = new JLabel("Enter Name:");
                JTextField nameField = new JTextField();

                JLabel ageLabel = new JLabel("Enter Age:");
                JTextField ageField = new JTextField();

                JLabel emailLabel = new JLabel("Enter Email:");
                JTextField emailField = new JTextField();

                JButton saveBtn = new JButton("Save");

                profileFrame.add(nameLabel);
                profileFrame.add(nameField);
                profileFrame.add(ageLabel);
                profileFrame.add(ageField);
                profileFrame.add(emailLabel);
                profileFrame.add(emailField);
                profileFrame.add(new JLabel());
                profileFrame.add(saveBtn);

                profileFrame.setLocationRelativeTo(mainFrame);
                profileFrame.setVisible(true);

                saveBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText().trim();
                        String age = ageField.getText().trim();
                        String email = emailField.getText().trim();

                        if (name.isEmpty() || age.isEmpty() || email.isEmpty()) {
                            JOptionPane.showMessageDialog(profileFrame,
                                    "Please enter all fields (Name, Age, Email).",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        userName = name;
                        userAge = age;
                        userEmail = email;
                        JOptionPane.showMessageDialog(profileFrame, "Profile Saved!");
                        profileFrame.dispose();
                    }
                });
            }
        });

        // Add User Financial Info
        addFinancialBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Financial Statement Calculator");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(500, 500);
                frame.setLayout(new GridLayout(6, 2, 10, 10));

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
                            sb.append("<b>User Profile</b><br>");
                            sb.append("Name: ").append(userName).append("<br>");
                            sb.append("Age: ").append(userAge).append("<br>");
                            sb.append("Email: ").append(userEmail).append("<br><br>");
                            sb.append("<b>Financial Statement</b><br>");
                            sb.append("Net Worth: ").append(netWorth).append("<br>");
                            sb.append("Income Statement:<br>");
                            sb.append("  Income: ").append(income).append("<br>");
                            sb.append("  Expenses: ").append(expenses).append("<br>");
                            sb.append("  Net Income: ").append(netIncome).append("<br>");
                            sb.append("Assets: ").append(assets).append("<br>");
                            sb.append("Liabilities: ").append(liabilities);
                            sb.append("</html>");

                            // Show result and pie chart in a new window
                            JFrame resultFrame = new JFrame("Calculation Result");
                            resultFrame.setLayout(new BorderLayout());

                            JLabel resultContent = new JLabel(sb.toString(), SwingConstants.CENTER);
                            resultFrame.add(resultContent, BorderLayout.NORTH);

                            // Pie chart panel
                            JPanel piePanel = new PieChartPanel(assets, liabilities, income, expenses);
                            resultFrame.add(piePanel, BorderLayout.CENTER);

                            resultFrame.setSize(450, 450);
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

                frame.setLocationRelativeTo(mainFrame);
                frame.setVisible(true);
            }
        });

        // Help button
        helpBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame,
                        "1. Add User Profile: Enter and save your name, age, and email.\n"
                                + "2. Add User Financial Info: Enter your assets, liabilities, income, and expenses to calculate your net worth and view a pie chart.\n"
                                + "3. Help: Show this help message.",
                        "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        });
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