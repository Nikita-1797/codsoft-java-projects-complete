import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGame {

    static int number;
    static int attempt = 0;
    static int max = 5;
    static int score = 0;

    public static void main(String[] args) {

        Random r = new Random();
        number = r.nextInt(100) + 1;

        JFrame f = new JFrame("Number Guess Game");
        f.setSize(400,300);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(new Color(230,240,255)); // light blue background

        JLabel l1 = new JLabel("Guess Number (1-100)");
        l1.setBounds(120,20,200,30);
        l1.setForeground(Color.BLUE);
        f.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(140,60,120,30);
        f.add(t1);

        JButton b1 = new JButton("Guess");
        b1.setBounds(80,110,100,30);
        b1.setBackground(new Color(144,238,144)); // light green
        f.add(b1);

        JButton b2 = new JButton("Restart");
        b2.setBounds(200,110,100,30);
        b2.setBackground(new Color(255,182,193)); // light pink
        f.add(b2);

        JLabel l2 = new JLabel("Attempts left: " + max);
        l2.setBounds(120,160,200,30);
        l2.setForeground(Color.BLACK);
        f.add(l2);

        JLabel l3 = new JLabel("Score: " + score);
        l3.setBounds(150,200,200,30);
        l3.setForeground(Color.MAGENTA);
        f.add(l3);

        // Guess Button
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                int guess = Integer.parseInt(t1.getText());
                attempt++;

                if(guess == number){
                    score++;
                    l2.setText("Correct! Attempts: " + attempt);
                    l2.setForeground(new Color(0,128,0)); // green
                    l3.setText("Score: " + score);
                    JOptionPane.showMessageDialog(f,"You Win!");
                }
                else if(attempt >= max){
                    l2.setText("Game Over! Number was " + number);
                    l2.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(f,"You Lose!");
                }
                else if(guess > number){
                    l2.setText("Too High! Attempts left: " + (max-attempt));
                    l2.setForeground(Color.ORANGE);
                }
                else{
                    l2.setText("Too Low! Attempts left: " + (max-attempt));
                    l2.setForeground(Color.ORANGE);
                }
            }
        });

        // Restart Button
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                number = r.nextInt(100) + 1;
                attempt = 0;
                t1.setText("");
                l2.setText("Game Restarted! Attempts left: " + max);
                l2.setForeground(Color.BLUE);
            }
        });

        f.setVisible(true);
    }
}