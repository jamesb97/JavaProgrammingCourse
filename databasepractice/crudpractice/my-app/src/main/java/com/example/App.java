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

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");

            insertBook(conn);
            readAllBooks(conn);
            readBooksByGenre(conn);
            paginateBooks(conn);
            updateBookPrice(conn);
            deleteBooksByGenre(conn);
            callAddBookProcedure(conn);
            simulateSQLError(conn);

            System.out.println("------------------------");
            System.out.println("Finish!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBook(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Insert data into the books table");

        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO books (Title, Author, Price, Genre, PublicationDate, PublisherID) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, "Advanced Java");
            ps.setString(2, "Jane Doe");
            ps.setDouble(3, 45.99);
            ps.setString(4, "Programming");
            ps.setDate(5, java.sql.Date.valueOf("2023-01-10"));
            ps.setInt(6, 1);

            int rows = ps.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        }
    }

    public static void readAllBooks(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Read all books");

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("Title"));
                System.out.println("Author: " + rs.getString("Author"));
                System.out.println("Price: " + rs.getDouble("Price"));
            }
        }
    }

    public static void readBooksByGenre(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Read books by genre");

        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM books WHERE Genre = ?")) {
            ps.setString(1, "Programming");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("Title"));
            }
        }
    }

    public static void paginateBooks(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Paginate books by price");

        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM books ORDER BY Price DESC LIMIT 5 OFFSET 0")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("Title"));
                System.out.println("Price: " + rs.getDouble("Price"));
            }
        }
    }

    public static void updateBookPrice(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Update book price");

        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE books SET Price = ? WHERE Title = ?")) {
            ps.setDouble(1, 49.99);
            ps.setString(2, "Advanced Java");

            int rows = ps.executeUpdate();
            System.out.println("Rows updated: " + rows);
        }
    }

    public static void deleteBooksByGenre(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Delete books by genre");

        try (PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM books WHERE Genre = ?")) {
            ps.setString(1, "Programming");

            int rows = ps.executeUpdate();
            System.out.println("Rows deleted: " + rows);
        }
    }

    public static void callAddBookProcedure(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure AddBook");

        try (CallableStatement cs = conn.prepareCall("{CALL AddBook(?, ?, ?, ?, ?, ?)}")) {
            cs.setString(1, "Advanced Java");
            cs.setString(2, "Jane Doe");
            cs.setDouble(3, 45.99);
            cs.setString(4, "Programming");
            cs.setDate(5, java.sql.Date.valueOf("2023-01-10"));
            cs.setInt(6, 1);

            cs.execute();
            System.out.println("Book added successfully using stored procedure.");
        }
    }

    public static void simulateSQLError(Connection conn) {
        System.out.println("------------------------");
        System.out.println("Simulating SQL Error");

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM NonExistentTable")) {
            ps.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
