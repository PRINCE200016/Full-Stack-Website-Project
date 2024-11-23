package com.itrainu.Module;

import com.itrainu.Bean.UserBean;

public class UserModuletest {
    public static void main(String[] args) throws Exception {
        // Uncomment the methods to test them
        // testAdd();
        // testDelete();
        findByPk();
        // testUpdate();
        // testAuth();
    }

    // Method to test adding a user
    private static void testAdd() throws Exception {
        UserBean bean = new UserBean();
        bean.setName("Kirti");
        bean.setDepartment("HR");
        bean.setPassword("password123");
        bean.setMobileNo("9876543210");
        bean.setDob(new java.util.Date());
        bean.setGender("Female");
        bean.setLastName("Kumar");
        bean.setLock("No");
        bean.setUnsuccessfulLogin(0);
        bean.setRoleId(2L);
        bean.setCreatedBy("Admin");
        bean.setConfirmPassword("password123");

        UserModule module = new UserModule();
        module.add(bean);
        System.out.println("Data added successfully!");
    }

    // Method to test deleting a user by ID
    private static void testDelete() throws Exception {
        UserModule module = new UserModule();
        module.delete(23);
        System.out.println("Data deleted successfully!");
    }

    // Method to test finding user by primary key
    private static void findByPk() throws Exception {
        UserModule module = new UserModule();
        UserBean bean = module.findByPk(3);
        if (bean != null) {
            System.out.println("ID: " + bean.getId());
            System.out.println("Name: " + bean.getName());
            System.out.println("Department: " + bean.getDepartment());
            System.out.println("Mobile No: " + bean.getMobileNo());
        } else {
            System.out.println("ID not available!");
        }
    }

    // Method to test updating a user's information
    private static void testUpdate() throws Exception {
        UserBean bean = new UserBean();
        bean.setId(3);
        bean.setName("Shagun");
        bean.setDepartment("Finance"); // Example of changing department
        // Add more fields as needed, e.g., bean.setSalary(9999); if salary is part of UserBean
        UserModule module = new UserModule();
        module.add(bean); // Use add method or create update method in UserModule if required
        System.out.println("Data updated successfully!");
    }

    // Method to test user authentication
    private static void testAuth() throws Exception {
        UserModule module = new UserModule();
        UserBean user = module.authenticate("testUser", "password123");
        if (user != null) {
            System.out.println("Authentication successful!");
            System.out.println("User ID: " + user.getId());
            System.out.println("User Name: " + user.getName());
        } else {
            System.out.println("Authentication failed!");
        }
    }
}
