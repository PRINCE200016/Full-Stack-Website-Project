<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, mypackage.DatabaseConnection" %>
<html>
<head><title>Database Connection Test</title></head>
<body>
    <%
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn != null) {
                out.println("Connected to the database successfully!");
            } else {
                out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    %>
</body>
</html>
