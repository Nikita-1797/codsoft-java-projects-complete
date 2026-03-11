import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter {
    public static void main(String[] args) {

        // Create frame
        JFrame f = new JFrame("Currency Converter");
        f.setSize(400, 320);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(new Color(230, 240, 255)); // light blue

        // Title label
        JLabel title = new JLabel("Currency Converter");
        title.setBounds(100, 20, 200, 30);
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        f.add(title);

        // Amount label and text field
        JLabel l1 = new JLabel("Amount:");
        l1.setBounds(60, 70, 100, 30);
        f.add(l1);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 70, 150, 30);
        f.add(amountField);

        // Currency selection
        String[] currencies = {"INR", "USD", "EUR"};

        JLabel l2 = new JLabel("From:");
        l2.setBounds(60, 110, 100, 30);
        f.add(l2);

        JComboBox<String> fromBox = new JComboBox<>(currencies);
        fromBox.setBounds(150, 110, 150, 30);
        f.add(fromBox);

        JLabel l3 = new JLabel("To:");
        l3.setBounds(60, 150, 100, 30);
        f.add(l3);

        JComboBox<String> toBox = new JComboBox<>(currencies);
        toBox.setBounds(150, 150, 150, 30);
        f.add(toBox);

        // Convert button
        JButton convertBtn = new JButton("Convert");
        convertBtn.setBounds(130, 200, 120, 35);
        convertBtn.setBackground(new Color(144, 238, 144)); // light green
        f.add(convertBtn);

        // Result label
        JLabel result = new JLabel("Result:");
        result.setBounds(130, 250, 200, 30);
        result.setForeground(Color.MAGENTA);
        result.setFont(new Font("Arial", Font.BOLD, 14));
        f.add(result);

        // Action listener
        convertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String from = fromBox.getSelectedItem().toString();
                    String to = toBox.getSelectedItem().toString();

                    double rate = 1;

                    // Fixed currency rates
                    if (from.equals("USD") && to.equals("INR"))
                        rate = 83;
                    else if (from.equals("INR") && to.equals("USD"))
                        rate = 0.012;
                    else if (from.equals("EUR") && to.equals("INR"))
                        rate = 90;
                    else if (from.equals("INR") && to.equals("EUR"))
                        rate = 0.011;
                    else if (from.equals("USD") && to.equals("EUR"))
                        rate = 0.92;
                    else if (from.equals("EUR") && to.equals("USD"))
                        rate = 1.08;

                    double converted = amount * rate;
                    result.setText(String.format("Result: %.2f %s", converted, to));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(f, "Please enter a valid number");
                }
            }
        });

        f.setVisible(true);
    }
}