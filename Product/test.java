package Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Input GUI Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel textLabel = new JLabel("Enter text:");
        JTextField textField = new JTextField();

        JLabel intLabel = new JLabel("Enter integer:");
        JTextField intField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                String intText = intField.getText();
                try {
                    int number = Integer.parseInt(intText);
                    resultLabel.setText("Text: " + text + ", Integer: " + number);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid integer.");
                }
            }
        });

        frame.add(textLabel);
        frame.add(textField);
        frame.add(intLabel);
        frame.add(intField);
        frame.add(new JLabel()); // empty cell
        frame.add(submitButton);
        frame.add(new JLabel()); // empty cell
        frame.add(resultLabel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}