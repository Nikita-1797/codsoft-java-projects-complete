import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Bank Account Class
class BankAccount {

    double balance = 0; // starting balance

    void deposit(double amount){
        balance = balance + amount;
    }

    boolean withdraw(double amount){
        if(amount <= balance){
            balance = balance - amount;
            return true;
        }
        else{
            return false;
        }
    }

    double checkBalance(){
        return balance;
    }
}


// ATM Interface Class
public class ATMInterface {

    public static void main(String[] args) {

        BankAccount acc = new BankAccount();

        JFrame f = new JFrame("ATM Machine");
        f.setSize(400,350);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(new Color(230,240,255)); // light blue background

        JLabel title = new JLabel("ATM INTERFACE");
        title.setBounds(140,20,200,30);
        title.setForeground(Color.BLUE);
        f.add(title);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(120,70,150,30);
        withdrawBtn.setBackground(new Color(255,200,120)); // light orange
        f.add(withdrawBtn);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(120,120,150,30);
        depositBtn.setBackground(new Color(144,238,144)); // light green
        f.add(depositBtn);

        JButton balanceBtn = new JButton("Check Balance");
        balanceBtn.setBounds(120,170,150,30);
        balanceBtn.setBackground(new Color(173,216,230)); // light blue
        f.add(balanceBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(120,220,150,30);
        exitBtn.setBackground(new Color(255,182,193)); // light pink
        f.add(exitBtn);


        // Withdraw
        withdrawBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String input = JOptionPane.showInputDialog("Enter amount to withdraw");

                double amount = Double.parseDouble(input);

                if(acc.withdraw(amount)){
                    JOptionPane.showMessageDialog(f,"Withdraw Successful");
                }
                else{
                    JOptionPane.showMessageDialog(f,"Insufficient Balance");
                }

            }
        });


        // Deposit
        depositBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String input = JOptionPane.showInputDialog("Enter amount to deposit");

                double amount = Double.parseDouble(input);

                acc.deposit(amount);

                JOptionPane.showMessageDialog(f,"Deposit Successful");

            }
        });


        // Check Balance
        balanceBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                double bal = acc.checkBalance();

                JOptionPane.showMessageDialog(f,"Your Balance: " + bal);

            }
        });


        // Exit
        exitBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                System.exit(0);

            }
        });

        f.setVisible(true);
    }
}