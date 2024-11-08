package com.itrainu.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itrainu.Bean.TableBean;
import com.itrainu.Bean.UserBean;
import com.itrainu.util.JDBCDataSource;

public class UserModule {
    @SuppressWarnings("unused")
    private Object bean;

    public Integer nextpk() throws Exception {
        int pk = 0;
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM employee");
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                pk = rs.getInt(1);
                System.out.println("Max ID: " + pk);
            }
        }
        return pk + 1;
    }

    public void add(UserBean bean) throws Exception {
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employee (id, Name, department, PASSWORD, MOBILE_NO, DOB, GENDER, LASTNAME, USER_LOCK, UNSUCCESSFULL_LOGIN, ROLE_ID, CREATED_BY, CONFIRM_PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            int pk = nextpk();
            System.out.println("Name = " + bean.getName());
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getName());
            pstmt.setString(3, bean.getDepartment());
            pstmt.setString(4, bean.getPassword());
            pstmt.setString(5, bean.getMobileNo());
            pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
            pstmt.setString(7, bean.getGender());
            pstmt.setString(8, bean.getLastName());
            pstmt.setString(9, bean.getLock());
            pstmt.setInt(10, bean.getUnsuccessfulLogin());
            pstmt.setLong(11, bean.getRoleId());
            pstmt.setString(12, bean.getCreatedBy());
            pstmt.setString(13, bean.getConfirmPassword());
            int i = pstmt.executeUpdate();
            System.out.println("Data added successfully: " + i);
        }
    }

    public UserBean findbypk(int id) throws Exception {
        UserBean bean = null;
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee WHERE id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bean = new UserBean();
                    bean.setId(rs.getInt("id"));
                    bean.setName(rs.getString("Name"));
                    bean.setDepartment(rs.getString("department"));
                    bean.setPassword(rs.getString("PASSWORD"));
                    bean.setMobileNo(rs.getString("MOBILE_NO"));
                    bean.setDob(rs.getDate("DOB"));
                    bean.setGender(rs.getString("GENDER"));
                    bean.setLastName(rs.getString("LASTNAME"));
                    bean.setLock(rs.getString("USER_LOCK"));
                    bean.setUnsuccessfulLogin(rs.getInt("UNSUCCESSFULL_LOGIN"));
                    bean.setRoleId(rs.getLong("ROLE_ID"));
                    bean.setCreatedBy(rs.getString("CREATED_BY"));
                    bean.setConfirmPassword(rs.getString("CONFIRM_PASSWORD"));
                }
            }
        }
        return bean;
    }

    public void delete(int id) throws Exception {
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            pstmt.setInt(1, id);
            int i = pstmt.executeUpdate();
            System.out.println(i != 0 ? "Data deleted successfully: " + i : "Data delete unsuccessful: " + i);
        }
    }

    public List<UserBean> search(UserBean bean) throws Exception {
        List<UserBean> list = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE Name LIKE ?";
        try (Connection conn = JDBCDataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + bean.getName() + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    UserBean userBean = new UserBean();
                    userBean.setId(rs.getInt("id"));
                    userBean.setName(rs.getString("Name"));
                    userBean.setDepartment(rs.getString("department"));
                    userBean.setPassword(rs.getString("PASSWORD"));
                    userBean.setMobileNo(rs.getString("MOBILE_NO"));
                    userBean.setDob(rs.getDate("DOB"));
                    userBean.setGender(rs.getString("GENDER"));
                    userBean.setLastName(rs.getString("LASTNAME"));
                    userBean.setLock(rs.getString("USER_LOCK"));
                    userBean.setUnsuccessfulLogin(rs.getInt("UNSUCCESSFULL_LOGIN"));
                    userBean.setRoleId(rs.getLong("ROLE_ID"));
                    userBean.setCreatedBy(rs.getString("CREATED_BY"));
                    userBean.setConfirmPassword(rs.getString("CONFIRM_PASSWORD"));
                    list.add(userBean);
                }
            }
        }
        System.out.println("User list size: " + list.size());
        return list;
    }
}
