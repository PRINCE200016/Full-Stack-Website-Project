package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/itrainu";
    private static final String userName = "root";
    private static final String password = "Rajawat2000";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected successfully");

            Statement stm = connection.createStatement();
//            String query = "CREATE TABLE IF NOT EXISTS employee (id INT PRIMARY KEY, name VARCHAR(50), salary INT)";
//            stm.execute(query);
//            System.out.println("Table created successfully");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        // Test the connection
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
