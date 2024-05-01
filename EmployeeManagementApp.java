package code_clause;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeManagementApp {
    private static final String DB_URL = "jdbc:mysql://localhost/EmployeeDB";
    private static final String DB_USER = "username";
    private static final String DB_PASS = "password";

    private JFrame frame;
    private JTextField idField, nameField, positionField, salaryField, attendanceField;
    private JButton addButton, updateButton, deleteButton, viewButton;
    private JTextArea outputArea;

    public EmployeeManagementApp() {
        frame = new JFrame("Employee Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        idField = new JTextField();
        nameField = new JTextField();
        positionField = new JTextField();
        salaryField = new JTextField();
        attendanceField = new JTextField();
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Position:"));
        inputPanel.add(positionField);
        inputPanel.add(new JLabel("Salary:"));
        inputPanel.add(salaryField);
        inputPanel.add(new JLabel("Attendance:"));
        inputPanel.add(attendanceField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(viewButton);
        frame.add(inputPanel);

        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane);

        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        viewButton.addActionListener(e -> viewEmployees());

        frame.setVisible(true);
    }

    private void addEmployee() {
        String name = nameField.getText();
        String position = positionField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        int attendance = Integer.parseInt(attendanceField.getText());

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO Employees (name, position, salary, attendance) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, position);
            statement.setDouble(3, salary);
            statement.setInt(4, attendance);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                outputArea.setText("Employee added successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateEmployee() {
        // Similar to addEmployee, but updating an existing employee
    }

    private void deleteEmployee() {
        // Similar to addEmployee, but deleting an existing employee
    }

    private void viewEmployees() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM Employees";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            StringBuilder sb = new StringBuilder();
            while (resultSet.next()) {
                sb.append("ID: ").append(resultSet.getInt("id"))
                        .append(", Name: ").append(resultSet.getString("name"))
                        .append(", Position: ").append(resultSet.getString("position"))
                        .append(", Salary: ").append(resultSet.getDouble("salary"))
                        .append(", Attendance: ").append(resultSet.getInt("attendance"))
                        .append("\n");
            }
            outputArea.setText(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeManagementApp::new);
    }
}
