package com.example;

import java.sql.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input = App.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println(
                        "db.properties not found. Copy db.properties.example to db.properties and fill in your credentials.");
                return;
            }
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();

            System.out.println("connection successful!");

            // Your JDBC code here
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Students");
            while (resultSet.next()) {
                System.out.println("Student ID: " + resultSet.getInt("StudentID"));
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("Major: " + resultSet.getString("Major"));
            }
            resultSet = statement.executeQuery(
                    "SELECT * FROM Students WHERE Major = 'Computer Science'");
            System.out.println("Students with major of Computer Science");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }
            resultSet = statement.executeQuery(
                    "SELECT * FROM Students ORDER BY GPA DESC");
            System.out.println("Printing students with descending GPAs");

            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("GPA: " + resultSet.getDouble("GPA"));
            }
            resultSet = statement.executeQuery(
                    "SELECT * FROM Students ORDER BY GPA DESC LIMIT 5");
            System.out.println("Limiting resultset to 5");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }
            resultSet = statement.executeQuery(
                    "SELECT Major, COUNT(*) AS StudentCount FROM Students GROUP BY Major");

            System.out.println("Count students by major");

            while (resultSet.next()) {
                System.out.println("Major: " + resultSet.getString("Major"));
                System.out.println("Number of Students: " + resultSet.getInt("StudentCount"));
            }

            resultSet = statement.executeQuery(
                    "SELECT Students.Name, Courses.CourseName " +
                            "FROM Students " +
                            "INNER JOIN Enrollments ON Students.StudentID = Enrollments.StudentID " +
                            "INNER JOIN Courses ON Enrollments.CourseID = Courses.CourseID");

            System.out.println("Retrieve Student Names and Their Courses");

            while (resultSet.next()) {
                System.out.println("Student: " + resultSet.getString("Name"));
                System.out.println("Course: " + resultSet.getString("CourseName"));
            }

            resultSet = statement.executeQuery(
                    "SELECT * FROM Students WHERE Major IS NULL");

            System.out.println("Retrieve Students Without a Declared Major");

            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
