import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Student {
    String name;
    String rollNumber;
    String grade;

    public Student(String name, String rollNumber, String grade){
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String toString(){
        return "Roll No: " + rollNumber + ", \nName: " + name + ", \nGrade: " + grade;
    }
}

class StudentManagementSystem {

    private ArrayList<Student> students = new ArrayList<>();
    private final String filename = "students.txt";

    public StudentManagementSystem(){
        loadFromFile();
    }

    public boolean addStudent(Student s){
        // Check if rollNumber already exists
        for(Student st : students){
            if(st.rollNumber.equals(s.rollNumber)){
                return false;
            }
        }
        students.add(s);
        saveToFile();
        return true;
    }

    public boolean removeStudent(String rollNumber){
        Iterator<Student> it = students.iterator();
        while(it.hasNext()){
            Student s = it.next();
            if(s.rollNumber.equals(rollNumber)){
                it.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(String rollNumber){
        for(Student s : students){
            if(s.rollNumber.equals(rollNumber)){
                return s;
            }
        }
        return null;
    }

    public ArrayList<Student> getAllStudents(){
        return students;
    }

    // File storage - Save
    public void saveToFile(){
        try(PrintWriter pw = new PrintWriter(new FileWriter(filename))){
            for(Student s : students){
                // Save as: rollNumber|name|grade
                pw.println(s.rollNumber + "|" + s.name + "|" + s.grade);
            }
        }catch(IOException e){
            System.out.println("Error saving students data.");
        }
    }

    // File storage - Load
    public void loadFromFile(){
        students.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("\\|");
                if(parts.length == 3){
                    students.add(new Student(parts[1], parts[0], parts[2]));
                }
            }
        }catch(IOException e){
            // File may not exist initially, ignore
        }
    }
}

// GUI Class
public class StudentManagementGUI {

    static StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args){

        JFrame frame = new JFrame("Student Management System");
        frame.setSize(500,450);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // light color

        JLabel title = new JLabel("Student Management System");
        title.setBounds(120, 10, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(0, 70, 140));
        frame.add(title);

        // Labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 60, 250, 25);
        frame.add(nameField);

        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setBounds(50, 100, 100, 25);
        frame.add(rollLabel);

        JTextField rollField = new JTextField();
        rollField.setBounds(150, 100, 250, 25);
        frame.add(rollField);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setBounds(50, 140, 100, 25);
        frame.add(gradeLabel);

        JTextField gradeField = new JTextField();
        gradeField.setBounds(150, 140, 250, 25);
        frame.add(gradeField);

        // Buttons
        JButton addBtn = new JButton("Add Student");
        addBtn.setBounds(50, 190, 150, 30);
        addBtn.setBackground(new Color(144, 238, 144)); // light green
        frame.add(addBtn);

        JButton removeBtn = new JButton("Remove Student");
        removeBtn.setBounds(250, 190, 150, 30);
        removeBtn.setBackground(new Color(255, 160, 122)); // light salmon
        frame.add(removeBtn);

        JButton searchBtn = new JButton("Search Student");
        searchBtn.setBounds(50, 230, 150, 30);
        searchBtn.setBackground(new Color(135, 206, 250)); // light sky blue
        frame.add(searchBtn);

        JButton showAllBtn = new JButton("Show All Students");
        showAllBtn.setBounds(250, 230, 150, 30);
        showAllBtn.setBackground(new Color(255, 228, 181)); // light moccasin
        frame.add(showAllBtn);

        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(50, 280, 350, 120);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        frame.add(outputArea);

        // Add student
        addBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = nameField.getText().trim();
                String roll = rollField.getText().trim();
                String grade = gradeField.getText().trim();

                if(name.isEmpty() || roll.isEmpty() || grade.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                    return;
                }

                // Basic roll number validation (alphanumeric only)
                if(!roll.matches("[a-zA-Z0-9]+")){
                    JOptionPane.showMessageDialog(frame, "Roll number should be alphanumeric without spaces.");
                    return;
                }

                Student s = new Student(name, roll, grade);
                boolean added = sms.addStudent(s);
                if(added){
                    JOptionPane.showMessageDialog(frame, "Student added successfully.");
                    nameField.setText("");
                    rollField.setText("");
                    gradeField.setText("");
                    outputArea.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Roll Number already exists!");
                }
            }
        });

        // Remove student
        removeBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String roll = rollField.getText().trim();
                if(roll.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Please enter Roll Number to remove.");
                    return;
                }
                boolean removed = sms.removeStudent(roll);
                if(removed){
                    JOptionPane.showMessageDialog(frame, "Student removed successfully.");
                    outputArea.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Student not found.");
                }
            }
        });

        // Search student
        searchBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String roll = rollField.getText().trim();
                if(roll.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Please enter Roll Number to search.");
                    return;
                }
                Student s = sms.searchStudent(roll);
                if(s != null){
                    outputArea.setText(s.toString());
                }
                else{
                    outputArea.setText("Student not found.");
                }
            }
        });

        // Show all students
        showAllBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                StringBuilder sb = new StringBuilder();
                ArrayList<Student> all = sms.getAllStudents();
                if(all.isEmpty()){
                    outputArea.setText("No students to display.");
                    return;
                }
                for(Student s : all){
                    sb.append(s.toString()).append("\n");
                }
                outputArea.setText(sb.toString());
            }
        });

        frame.setVisible(true);
    }
}