package com.itrainu.Module;

import com.itrainu.Bean.UserBean;
import com.itrainu.Exception.ApplicationException;
import com.itrainu.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModule {
    private static Logger log = Logger.getLogger(UserModule.class.getName());

    // Method to add a new user
    public void add(UserBean bean) throws ApplicationException {
        log.info("Adding user: " + bean.getName());
        String sql = "INSERT INTO employee (Name, department, PASSWORD, MOBILE_NO, DOB, GENDER, LASTNAME, USER_LOCK, UNSUCCESSFULL_LOGIN, ROLE_ID, CREATED_BY, CONFIRM_PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, bean.getName());
            pstmt.setString(2, bean.getDepartment());
            pstmt.setString(3, bean.getPassword());
            pstmt.setString(4, bean.getMobileNo());
            pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
            pstmt.setString(6, bean.getGender());
            pstmt.setString(7, bean.getLastName());
            pstmt.setString(8, bean.getLock());
            pstmt.setInt(9, bean.getUnsuccessfulLogin());
            pstmt.setLong(10, bean.getRoleId());
            pstmt.setString(11, bean.getCreatedBy());
            pstmt.setString(12, bean.getConfirmPassword());
            
            pstmt.executeUpdate();
            log.info("User added successfully!");
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database error occurred while adding user.", e);
            throw new ApplicationException("Database error occurred while adding user.", e);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unexpected error occurred while adding user.", e);
            throw new ApplicationException(e);
        }
    }

    // Method to authenticate user
    public UserBean authenticate(String login, String password) throws ApplicationException {
        log.info("Authenticating user: " + login);
        String sql = "SELECT * FROM employee WHERE NAME = ? AND PASSWORD = ?";
        UserBean user = null;
        
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("Name"));
                user.setDepartment(rs.getString("department"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setMobileNo(rs.getString("MOBILE_NO"));
                user.setDob(rs.getDate("DOB"));
                user.setGender(rs.getString("GENDER"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setLock(rs.getString("USER_LOCK"));
                user.setUnsuccessfulLogin(rs.getInt("UNSUCCESSFULL_LOGIN"));
                user.setRoleId(rs.getLong("ROLE_ID"));
                user.setCreatedBy(rs.getString("CREATED_BY"));
                user.setConfirmPassword(rs.getString("CONFIRM_PASSWORD"));
            } else {
                throw new ApplicationException("Invalid username or password");
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database error occurred during authentication.", e);
            throw new ApplicationException("Database error occurred during authentication.", e);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unexpected error occurred during authentication.", e);
            throw new ApplicationException(e);
        }
        
        return user;
    }

    // Method to find user by primary key (ID)
    public UserBean findByPk(int id) throws ApplicationException {
        log.info("Finding user by ID: " + id);
        String sql = "SELECT * FROM employee WHERE id = ?";
        UserBean user = null;
        
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("Name"));
                user.setDepartment(rs.getString("department"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setMobileNo(rs.getString("MOBILE_NO"));
                user.setDob(rs.getDate("DOB"));
                user.setGender(rs.getString("GENDER"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setLock(rs.getString("USER_LOCK"));
                user.setUnsuccessfulLogin(rs.getInt("UNSUCCESSFULL_LOGIN"));
                user.setRoleId(rs.getLong("ROLE_ID"));
                user.setCreatedBy(rs.getString("CREATED_BY"));
                user.setConfirmPassword(rs.getString("CONFIRM_PASSWORD"));
            } else {
                throw new ApplicationException("User not found with ID: " + id);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database error occurred while finding user by ID.", e);
            throw new ApplicationException("Database error occurred while finding user by ID.", e);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unexpected error occurred while finding user by ID.", e);
            throw new ApplicationException(e);
        }
        
        return user;
    }

    // Method to delete user by primary key (ID)
    public void delete(int id) throws ApplicationException {
        log.info("Deleting user with ID: " + id);
        String sql = "DELETE FROM employee WHERE id = ?";
        
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new ApplicationException("User with ID " + id + " not found.");
            }
            log.info("User deleted successfully!");
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database error occurred while deleting user.", e);
            throw new ApplicationException("Database error occurred while deleting user.", e);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unexpected error occurred while deleting user.", e);
            throw new ApplicationException(e);
        }
    }

    // Method to search users (overloaded)
    public List<UserBean> search(UserBean bean) throws ApplicationException {
        log.info("Searching for user with details: " + bean);
        List<UserBean> users = new ArrayList<>();
        
        String sql = "SELECT * FROM employee WHERE 1=1";
        
        if (bean.getName() != null && !bean.getName().isEmpty()) {
            sql += " AND name LIKE ?";
        }
        if (bean.getDepartment() != null && !bean.getDepartment().isEmpty()) {
            sql += " AND department LIKE ?";
        }
        
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            int paramIndex = 1;
            if (bean.getName() != null && !bean.getName().isEmpty()) {
                pstmt.setString(paramIndex++, "%" + bean.getName() + "%");
            }
            if (bean.getDepartment() != null && !bean.getDepartment().isEmpty()) {
                pstmt.setString(paramIndex++, "%" + bean.getDepartment() + "%");
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("Name"));
                user.setDepartment(rs.getString("department"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setMobileNo(rs.getString("MOBILE_NO"));
                user.setDob(rs.getDate("DOB"));
                user.setGender(rs.getString("GENDER"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setLock(rs.getString("USER_LOCK"));
                user.setUnsuccessfulLogin(rs.getInt("UNSUCCESSFULL_LOGIN"));
                user.setRoleId(rs.getLong("ROLE_ID"));
                user.setCreatedBy(rs.getString("CREATED_BY"));
                user.setConfirmPassword(rs.getString("CONFIRM_PASSWORD"));
                users.add(user);
            }
            if (users.isEmpty()) {
                throw new ApplicationException("No users found with the given search criteria.");
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Database error occurred while searching for users.", e);
            throw new ApplicationException("Database error occurred while searching for users.", e);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unexpected error occurred while searching for users.", e);
            throw new ApplicationException(e);
        }
        
        return users;
    }
}
