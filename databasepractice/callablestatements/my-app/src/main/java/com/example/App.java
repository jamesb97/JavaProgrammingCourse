package com.example;

import java.util.Properties;
import java.sql.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // System.out.println( "Hello World!" );
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

        // try {
        // Connection conn = DriverManager.getConnection(url, user, password);
        // Statement statement = conn.createStatement();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // }
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");

            insertStudent(conn);
            updateStudent(conn);
            getStudentsByMajor(conn);
            callGetStudentsByMajor(conn);
            callGetAverageGPA(conn);
            insertStudentWithGeneratedKey(conn);
            simulateSQLError(conn);

            System.out.println("------------------------");
            System.out.println("Finish!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudent(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Insert data into the Students table");

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO Students (Name, Major, GPA) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, "Alice Johnson");
            preparedStatement.setString(2, "Computer Science");
            preparedStatement.setDouble(3, 3.8);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);
        }
    }

    public static void updateStudent(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Update Students with GPA of 3.9 and name of Alice");

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE Students SET GPA = ? WHERE Name = ?")) {
            preparedStatement.setDouble(1, 3.9);
            preparedStatement.setString(2, "Alice Johnson");

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsAffected);
        }
    }

    public static void getStudentsByMajor(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Get all students with major of Computer Science");

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM Students WHERE Major = ?")) {
            preparedStatement.setString(1, "Computer Science");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("GPA: " + resultSet.getDouble("GPA"));
            }
        }
    }

    public static void callGetStudentsByMajor(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure GetStudentsByMajor");

        try (CallableStatement callableStatement = conn.prepareCall("{CALL GetStudentsByMajor(?)}")) {
            callableStatement.setString(1, "Computer Science");

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("GPA: " + resultSet.getDouble("GPA"));
            }
        }
    }

    public static void callGetAverageGPA(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure GetAverageGPA");

        try (CallableStatement callableStatement = conn.prepareCall("{CALL GetAverageGPA(?)}")) {
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.execute();

            double avgGPA = callableStatement.getDouble(1);
            System.out.println("Average GPA: " + avgGPA);
        }
    }

    public static void insertStudentWithGeneratedKey(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Insert student and retrieve generated key");

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO Students (Name, Major, GPA) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, "John Doe");
            preparedStatement.setString(2, "Mathematics");
            preparedStatement.setDouble(3, 3.6);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newStudentID = generatedKeys.getInt(1);
                System.out.println("Generated Student ID: " + newStudentID);
            }
        }
    }

    public static void simulateSQLError(Connection conn) {
        System.out.println("------------------------");
        System.out.println("Simulating SQL Error");

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM NonExistentTable")) {
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
