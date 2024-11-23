package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/itrainu";
    private static final String USER = "root";
    private static final String PASSWORD = "Rajawat2000";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            throw e; // re-throw the exception to let the caller handle it
        }
        return connection;
    }

    public static void main(String[] args) {
        // Test the connection
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Database connection test successful.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
