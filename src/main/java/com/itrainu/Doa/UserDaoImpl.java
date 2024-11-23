package com.itrainu.Doa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itrainu.Bean.UserBean;
import com.itrainu.Module.User;

import mypackage.DatabaseConnection;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean isValidUser(String username, String password) {
        String query = "SELECT * FROM employee WHERE Name = ? AND PASSWORD = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            System.out.println("Connection opened for isValidUser method.");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isValid = resultSet.next();
            System.out.println("Connection closed for isValidUser method.");
            return isValid;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(UserBean user) {
        String query = "INSERT INTO employee (id, Name, department, PASSWORD, MOBILE_NO, DOB, GENDER, LASTNAME, USER_LOCK, UNSUCCESFULL_LOGIN, ROLE_ID, CREATED_BY, CONFIRM_PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            System.out.println("Connection opened for addUser method.");
            // Assign a unique id value manually
            preparedStatement.setInt(1, getNextId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getDepartment());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getMobileNo());
            preparedStatement.setDate(6, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setString(7, user.getGender());
            preparedStatement.setString(8, user.getLastName());
            preparedStatement.setString(9, user.getLock());
            preparedStatement.setInt(10, user.getUnsuccessfulLogin());
            preparedStatement.setLong(11, user.getRoleId());
            preparedStatement.setString(12, user.getCreatedBy());
            preparedStatement.setString(13, user.getConfirmPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Connection closed for addUser method.");

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO employee (id, Name, department, PASSWORD, MOBILE_NO, DOB, GENDER, LASTNAME, USER_LOCK, UNSUCCESFULL_LOGIN, ROLE_ID, CREATED_BY, CONFIRM_PASSWORD, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("Connection opened for addUser method.");
            // Assign a unique id value manually
            preparedStatement.setInt(1, getNextId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getDepartment());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getMobileNo());
            preparedStatement.setDate(6, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setString(7, user.getGender());
            preparedStatement.setString(8, user.getLastName());
            preparedStatement.setString(9, user.getLock());
            preparedStatement.setInt(10, user.getUnsuccessfulLogin());
            preparedStatement.setLong(11, user.getRoleId());
            preparedStatement.setString(12, user.getCreatedBy());
            preparedStatement.setString(13, user.getConfirmPassword());
            preparedStatement.setString(14, user.getEmail());  // Added Email parameter

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Connection closed for addUser method.");

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    	private int getNextId() {
    	    String query = "SELECT MAX(id) FROM employee";
    	    try (Connection connection = DatabaseConnection.getConnection();
    	         PreparedStatement preparedStatement = connection.prepareStatement(query);
    	         ResultSet resultSet = preparedStatement.executeQuery()) {

    	        if (resultSet.next()) {
    	            return resultSet.getInt(1) + 1;
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return 1; // Default to 1 if no records found
    	}
 // Replace this with actual id retrieval logic
    
}
