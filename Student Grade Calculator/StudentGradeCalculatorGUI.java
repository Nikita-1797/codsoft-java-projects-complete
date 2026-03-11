import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculatorGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setSize(500,400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240,248,255)); // AliceBlue background

        JLabel title = new JLabel("Student Grade Calculator");
        title.setBounds(120,20,300,30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(25,25,112)); // Midnight Blue
        frame.add(title);

        JLabel subjectLabel = new JLabel("Enter number of subjects:");
        subjectLabel.setBounds(50,70,200,25);
        subjectLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(250,70,80,25);
        frame.add(subjectField);

        JButton submitSubjects = new JButton("Next");
        submitSubjects.setBounds(180,110,120,35);
        submitSubjects.setBackground(new Color(144,238,144)); // Light green
        submitSubjects.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(submitSubjects);

        JLabel marksLabel = new JLabel("");
        marksLabel.setBounds(50,160,400,25);
        marksLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(marksLabel);

        JTextField marksField = new JTextField();
        marksField.setBounds(250,160,80,25);
        frame.add(marksField);
        marksField.setVisible(false);

        JButton submitMarks = new JButton("Submit");
        submitMarks.setBounds(180,200,120,35);
        submitMarks.setBackground(new Color(173,216,230)); // Light blue
        submitMarks.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(submitMarks);
        submitMarks.setVisible(false);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(50,260,400,100);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(resultLabel);

        // Variables for calculation
        final int[] subjects = {0};
        final int[] totalMarks = {0};
        final int[] currentSubject = {1};

        // Button action to enter number of subjects
        submitSubjects.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    subjects[0] = Integer.parseInt(subjectField.getText());
                    if(subjects[0] <= 0){
                        JOptionPane.showMessageDialog(frame, "Enter valid number of subjects!");
                        return;
                    }
                    subjectField.setEnabled(false);
                    submitSubjects.setEnabled(false);
                    marksLabel.setText("Enter marks for subject " + currentSubject[0] + " (out of 100):");
                    marksField.setVisible(true);
                    submitMarks.setVisible(true);
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(frame, "Enter a valid number!");
                }
            }
        });

        // Button action to enter marks for each subject
        submitMarks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int marks = Integer.parseInt(marksField.getText());
                    if(marks < 0 || marks > 100){
                        JOptionPane.showMessageDialog(frame, "Marks should be between 0 and 100!");
                        return;
                    }
                    totalMarks[0] += marks;
                    currentSubject[0]++;
                    marksField.setText("");

                    if(currentSubject[0] <= subjects[0]){
                        marksLabel.setText("Enter marks for subject " + currentSubject[0] + " (out of 100):");
                    } else {
                        // Calculate average and grade
                        double average = (double) totalMarks[0] / subjects[0];
                        String grade;
                        if (average >= 90) grade = "A+";
                        else if (average >= 80) grade = "A";
                        else if (average >= 70) grade = "B";
                        else if (average >= 60) grade = "C";
                        else if (average >= 50) grade = "D";
                        else grade = "F";

                        resultLabel.setText("<html>Total Marks: " + totalMarks[0] + 
                                            "<br>Average: " + String.format("%.2f",average) + "%" +
                                            "<br>Grade: " + grade + "</html>");
                        resultLabel.setForeground(new Color(0,128,0)); // Green for result

                        marksLabel.setVisible(false);
                        marksField.setVisible(false);
                        submitMarks.setVisible(false);
                    }

                } catch(Exception ex){
                    JOptionPane.showMessageDialog(frame, "Enter a valid number!");
                }
            }
        });

        frame.setVisible(true);
    }
}